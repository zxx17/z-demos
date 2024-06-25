package org.zxx17.model2.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/6/25
 */
public class FileChannel01 {

    /**
     * 程序入口主方法。
     * 本方法演示了如何将字符串写入到一个文件中。
     * 使用了Java NIO中的FileOutputStream和FileChannel来实现文件的写入操作。
     * 字符串首先被转换为字节数组，然后通过ByteBuffer写入到文件中。
     * 使用了try-with-resources语句确保资源（如文件流）在使用后能被正确关闭。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 定义待写入文件的字符串
        // 12 字节
        String str = "你好,Netty";
        try (FileOutputStream fileOutputStream = new FileOutputStream("./FileChannel01.txt");) {
            // 获取文件输出流对应的文件通道
            // 实际上是FileChannelImpl
            FileChannel channel = fileOutputStream.getChannel();
            // 分配一个1024字节的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 将字符串转换为字节数组并放入缓冲区
            buffer.put(str.getBytes());

            // 翻转缓冲区，使其从写模式切换到读模式
            buffer.flip();
            // 将缓冲区中的数据写入文件通道
            channel.write(buffer);
        } catch (Exception e) {
            // 打印异常堆栈跟踪信息
            e.printStackTrace();
        }
    }


}
