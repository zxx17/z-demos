package org.zxx17.model2.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/6/26
 */
public class FileChannel03 {

    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("FileChannel01.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("FileChannel04.txt")) {

            ByteBuffer buffer = ByteBuffer.allocate(512);

            FileChannel fileInputStreamChannel = fileInputStream.getChannel();

            //使用transferFrom
            fileOutputStream.getChannel().transferFrom(fileInputStreamChannel, 0, fileInputStreamChannel.size());
            // 使用transferTo
            //fileInputStreamChannel.transferTo(0, fileInputStreamChannel.size(), fileOutputStream.getChannel());

            // 使用ByteBuffer
            while (true) {
                /*
                   public Buffer clear() {
                        position = 0;
                        limit = capacity;
                        mark = -1;
                        return this;
                    }
                 */
                buffer.clear();
                int read = fileInputStreamChannel.read(buffer);
                System.out.println("read = " + read);
                if (read == -1) {
                    break;
                }

                buffer.flip();
                fileOutputStream.getChannel().write(buffer);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
