package org.zxx17.model2.netty.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 使用Java NIO API实现的简易TCP客户端.
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/6/26
 */
public class NIOClient {


    /**
     * 程序入口，用于建立一个非阻塞模式的SocketChannel连接到本地服务器，并通过该连接发送用户输入的消息。
     * 使用try-with-resources语句确保资源的正确关闭。
     *
     * @param args 命令行参数，未使用
     * @throws Exception 如果发生I/O错误或其他异常
     */
    public static void main(String[] args) throws Exception {
        // 打开一个SocketChannel，并设置为非阻塞模式
        try (SocketChannel socketChannel = SocketChannel.open()){
            socketChannel.configureBlocking(false);

            // 初始化服务器地址和端口
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

            // 尝试连接服务器，如果非阻塞模式下连接不能立即完成，则循环检查连接状态
            if (!socketChannel.connect(inetSocketAddress)){
                while (!socketChannel.finishConnect()){
                    // 在连接完成之前，打印提示信息，说明程序不会阻塞，可以进行其他操作
                    System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作");
                }
            }

            // 使用Scanner从标准输入读取用户消息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                // 读取用户输入的消息，并通过SocketChannel写入到服务器
                String str = scanner.nextLine();
                socketChannel.write(java.nio.ByteBuffer.wrap(str.getBytes()));
                // 打印消息发送的确认信息
                System.out.println("客户端发送消息：" + str);
            }

            // 捕获并处理任何异常，避免程序因异常终止
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
