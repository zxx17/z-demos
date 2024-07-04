package org.zxx17.model2.netty.core.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * .
 *
 * @author Xinxuan Zhuo
 * @version 1.0.0
 * @since 2024/7/4
 **/
@Slf4j
public class NettyHttpServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyHttpServerInitializer());
            ChannelFuture future = bootstrap.bind(8080).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            log.error("NettyHttpServer error", e);
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
