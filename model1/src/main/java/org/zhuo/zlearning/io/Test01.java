package org.zhuo.zlearning.io;

import java.io.*;

/**
 * 测试输入流
 */
public class Test01 {

    public static void main(String[] args) {
//        testFileInputStream();
//        testBufferedInputStream();
        testDataInputStream();
    }


    public static void testFileInputStream() {
        try (InputStream fis = new FileInputStream("D:\\code\\JavaProject\\z-demos\\model1\\src\\main\\java\\org\\zhuo\\zlearning\\io\\javaguidetext.txt")) {
            System.out.println("Number of remaining bytes:"
                    + fis.available());
            int content;
            long skip = fis.skip(2);
            System.out.println("The actual number of bytes skipped:" + skip);
            System.out.println();
            System.out.print("The content read from file:  ");
            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一般我们是不会直接单独使用 FileInputStream ，通常会配合 BufferedInputStream
     */
    public static void testBufferedInputStream() {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:\\code\\JavaProject\\z-demos\\model1\\src\\main\\java\\org\\zhuo\\zlearning\\io\\javaguidetext.txt"))) {
            int available = bufferedInputStream.available();
            System.out.println("Number of remaining bytes:" + available);
            byte[] bytes = bufferedInputStream.readAllBytes();
            String str = new String(bytes);
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void testDataInputStream() {
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream("D:\\code\\JavaProject\\z-demos\\model1\\src\\main\\java\\org\\zhuo\\zlearning\\io\\javaguidetext.txt"))) {
            while (true) { // 修改了while循环条件
                try {
                    boolean b = dataInputStream.readBoolean();
                    String s = dataInputStream.readUTF();
                    int i = dataInputStream.readInt();
                    System.out.println(b + "   " + s + "   " + i);
                } catch (EOFException e) {
                    System.out.println("文件读取完毕。");
                    break; // 遇到EOF时退出循环
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
