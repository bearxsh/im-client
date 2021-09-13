package com.bearxsh.imspring.client;

import com.bearxsh.imspring.client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author bearx
 */
@Component
public class ClientApplication {

    private static final String HOST = "localhost";
    private static final int PORT = 8900;

    @PostConstruct
    public void start() throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                            pipeline.addLast(new ClientHandler("{\"type\":1,\"name\":\"zhangsan\"}"));
                        }
                    });
            ChannelFuture future = bootstrap.connect(HOST, PORT).sync();
            // 必须异步处理，不然SpringBoot不能完全启动
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    future.channel().closeFuture().sync();
                }
            }).start();
        } finally {
            //group.shutdownGracefully();
        }
    }

}
