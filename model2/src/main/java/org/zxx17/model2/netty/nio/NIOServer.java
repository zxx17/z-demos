package org.zxx17.model2.netty.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用Java NIO的API实现一个简单的TCP服务器.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/6/26
 */
public class NIOServer {

    /**
     * 主函数，用于启动基于Selector的非阻塞式TCP服务器。
     * @param args 命令行参数，本程序中未使用。
     */
    public static void main(String[] args) {
        try (Selector selector = Selector.open()) {
            // 打开服务端Socket通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 绑定端口，监听客户端连接
            serverSocketChannel.socket().bind(new InetSocketAddress(6666));
            // 配置为非阻塞模式
            serverSocketChannel.configureBlocking(false);

            // 将ServerSocketChannel注册到Selector上，监听接受连接操作
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            // 无限循环，等待客户端连接和数据读取
            while (true) {
                // 监听Selector上的事件，超时时间为1000毫秒
                if (selector.select(1000) == 0) {
                    // 若无事件发生，则继续循环等待
                    // 服务器等待中 TODO
                    continue;
                }
                // 获取发生事件的SelectionKey集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                // 遍历发生事件的SelectionKey
                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();
                    // 判断是否可以接受客户端连接
                    if (selectionKey.isAcceptable()) {
                        // 接受客户端连接
                        SocketChannel ssc = serverSocketChannel.accept();
                        // 输出连接成功的提示信息
                        System.out.println(Thread.currentThread().getName()
                                + "==>客户端连接成功==>"
                                + ssc.hashCode());
                        // 配置为非阻塞模式
                        ssc.configureBlocking(false);
                        // 注册读事件，使用ByteBuffer缓存读取的数据
                        ssc.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }
                    // 判断是否可以读取数据
                    if (selectionKey.isReadable()) {
                        try  {
                            // 获取Socket通道，并读取数据
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                            channel.read(byteBuffer);
                            // 翻转ByteBuffer，准备解析数据
                            byteBuffer.flip();
                            // 将读取的数据转换为字符串
                            byte[] bytes = new byte[byteBuffer.remaining()];
                            byteBuffer.get(bytes);
                            String msg = new String(bytes, StandardCharsets.UTF_8);
                            // 输出接收到的消息
                            System.out.println(Thread.currentThread().getName()
                                    + "==>客户端发送消息==>"
                                    + msg);
                            // 若接收到的消息为"exit"，则关闭通道
                            if ("exit".equals(msg)){
                                channel.close();
                            }
                            // 清空ByteBuffer，为下一次读取做准备
                            byteBuffer.clear();
                        } catch (Exception e) {
                            // 输出异常信息
                            e.printStackTrace();
                        }
                    }
                    // 移除已处理的SelectionKey
                    keyIterator.remove();
                }
            }
        } catch (Exception e) {
            // 输出异常信息
            e.printStackTrace();
        }
    }


}
