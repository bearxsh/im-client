package com.bearxsh.imspring.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author bearx
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    public static Channel channel = null;

    private final ByteBuf message;

    public ClientHandler(String name) {
        this.message = Unpooled.buffer(name.length());
        byte[] bytes = name.getBytes();
        for (byte aByte : bytes) {
            this.message.writeByte(aByte);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(this.message);
        channel = ctx.channel();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        System.out.println("receive a message: " + buffer.toString(CharsetUtil.UTF_8));
    }
}
