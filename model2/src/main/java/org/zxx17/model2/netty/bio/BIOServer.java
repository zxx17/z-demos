package org.zxx17.model2.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/6/25
 */
public class BIOServer {

    private static final HashMap<Long, String> threadMap = new HashMap<>();

    /**
     * 程序的入口点。
     * 创建一个服务器，监听指定端口，接收并处理客户端连接请求。
     * 使用线程池来异步处理客户端请求。
     *
     * @param args 命令行参数，本程序中未使用。
     * @throws IOException 如果服务器端口绑定或监听操作失败。
     */
    public static void main(String[] args) throws IOException {
        // 创建一个线程池，用于异步处理客户端请求。
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 创建服务器，监听6666端口。
        ServerSocket socket = new ServerSocket(6666);
        System.out.println("server is running...");
        // 无限循环，等待并处理客户端连接。
        while(true){
            System.out.println("client is coming...");
            // 接受客户端连接，返回一个Socket对象。
            final Socket accept = socket.accept();
            System.out.println("client is connected...");
            // 使用线程池执行任务，处理客户端连接。
            executorService.execute(()->{
                handler(accept);
            });
        }
    }


    private static void handler(Socket accept) {
        try {
            threadMap.put(Thread.currentThread().getId(), Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = accept.getInputStream();
            //循环的读取客户端发送的数据
            while (true) {
                OutputStream outputStream = accept.getOutputStream();
                outputStream.write("Connected success~!".getBytes());
                outputStream.flush();
                System.out.println("read....");
                int read = inputStream.read(bytes);
                System.out.println("cur Thread---->"+threadMap.get(Thread.currentThread().getId()));
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("close client....");
            try {
                accept.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
