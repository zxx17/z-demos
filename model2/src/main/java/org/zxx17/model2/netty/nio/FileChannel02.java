package org.zxx17.model2.netty.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/6/25
 */
public class FileChannel02 {

    /**
     * 程序的入口点。
     * 该方法尝试从指定的文件读取所有内容并打印到控制台。
     * 使用了try-with-resources语句确保资源（如文件输入流）在使用后能被正确关闭。
     *
     * @param args 命令行参数，未使用
     */
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("FileChannel01.txt");) {
            // 获取文件输入流对应的文件通道
            FileChannel channel = fileInputStream.getChannel();
            // 根据文件通道的大小分配一个ByteBuffer，用于存储文件内容
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            // 从文件通道读取数据到ByteBuffer
            channel.read(byteBuffer);
            // 将ByteBuffer中的数据转换为字符串并打印
            System.out.println(new String(byteBuffer.array()));
        } catch (Exception e) {
            // 捕获并打印任何在读取文件过程中发生的异常
            e.printStackTrace();
        }
    }


}
