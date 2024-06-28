package org.zxx17.model2.netty.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * 群聊服务器.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/6/28
 */
public class GroupChatServer {


    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }

    private Selector selector;
    private ServerSocketChannel listenChannel;

    private static final int PORT = 6667;


    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    if (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            // 有客户端连接
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.err.println(socketChannel.getRemoteAddress() + " 上线了");
                        }
                        if (key.isReadable()) {
                            // 读取数据
                            readData(key);
                        }
                        iterator.remove();
                    }
                }
                // count < 0 表示没有事件发生
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void readData(SelectionKey key) throws IOException {
        SocketChannel channel = null;
        try {
            //得到 channel
            channel = (SocketChannel) key.channel();
            //创建 buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            channel.read(byteBuffer);
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes);
            String msg = new String(bytes, StandardCharsets.UTF_8);
            if ("quit".equals(msg)) {
                System.err.println(channel.getRemoteAddress() + "离线了..");
                key.cancel();
                channel.close();
                msg = channel.getRemoteAddress() + "离开了聊天室..";
                sendInfoToOtherClients(msg, channel);
            } else {
                System.out.println("from 客户端 " + msg);
                sendInfoToOtherClients(msg, channel);
            }
        } catch (IOException e) {
            key.cancel();
            channel.close();
            e.printStackTrace();
        }
    }

    private void sendInfoToOtherClients(String msg, SocketChannel self) {
        for (SelectionKey key : selector.keys()) {
            try {
                //通过 key 取出对应的 SocketChannel
                Channel targetChannel = key.channel();
                //排除自己
                if (targetChannel instanceof SocketChannel dest && targetChannel != self) {
                    //转型
                    //将 msg 存储到 buffer
                    ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                    //将 buffer 的数据写入通道
                    dest.write(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
