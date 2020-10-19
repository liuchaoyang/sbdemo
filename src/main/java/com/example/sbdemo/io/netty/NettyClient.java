package com.example.sbdemo.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyClient {
    public void start(int port) throws Exception {
        EventLoopGroup group = new OioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();

            b.group(group)
                    .channel(OioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        private ByteBuf message = null;
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            for (int i = 0; i < 100; i++) {
                                                byte[] req = ("QUERY TIME ORDER-" + i + System.getProperty("line.separator")).getBytes(CharsetUtil.UTF_8);
                                                message = Unpooled.buffer(req.length);
                                                message.writeBytes(req);
                                                ctx.writeAndFlush(message);
                                            }
                                        }

                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            System.out.println(((ByteBuf) msg).toString(CharsetUtil.UTF_8));
//                                            ctx.write(msg);
//                                            try {
//                                                Thread.sleep(1000);
//                                            } catch (InterruptedException e) {
//                                                e.printStackTrace();
//                                            }
                                        }

                                        @Override
                                        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                            ctx.flush();
                                        }

                                        @Override
                                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                            cause.printStackTrace();
                                            ctx.close();
                                        }
                                    });
                        }
                    });
            // Start the client.
            ChannelFuture f = b.connect("localhost", port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        //https://www.cnblogs.com/luoxn28/p/11810710.html
        NettyClient nettyClient = new NettyClient();
        nettyClient.start(8080);
    }
}
