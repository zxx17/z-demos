package org.zxx17.model2.netty.core.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/7/8
 **/
public class NettyGPServer {

    private final int PORT;

    public NettyGPServer(int port) {
        this.PORT = port;
    }

    public void startServer(){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new NettyGPServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
            System.out.println("服务器启动成功");
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            System.err.println("服务器启动失败");
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        new NettyGPServer(8080).startServer();
    }

}
