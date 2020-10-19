package com.example.sbdemo.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class NettyServer {

    public void start(int port) throws Exception {
        final ByteBuf buf = Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer("Hi!\r\n", Charset.forName("UTF-8")));
        EventLoopGroup group = new OioEventLoopGroup(); //线程组，包含了一组NIO线程，专门用于网络事件的处理
        try {
            ServerBootstrap b = new ServerBootstrap();        //1.netty用于启动NIO服务端的辅助启动类，目的是降低服务端开发复杂度

            b.group(group)                                    //2
                    .channel(OioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .option(ChannelOption.SO_BACKLOG, 1024) //determining the number of connections queued
                    .childHandler(new ChannelInitializer<SocketChannel>() {//3.类似于Reactor模式中的handler类，用来处理网络IO事件，例如日志记录、对消息进行编解码等
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
//                                    .addLast("decoder", new HttpRequestDecoder()) //用于解码request
//                                    .addLast("encoder", new HttpResponseEncoder()) //用于编码response
//                                    .addLast("aggregator", new HttpObjectAggregator(512 * 1024)) //消息聚合器，参数含义是消息合并的数据大小，如此代表聚合的消息内容长度不超过512kb
                                    //LineBasedFrameDecoder工作原理：依次遍历ByteBuf中的可读字节，判断是否有“\n”或者“\r\n”，如果有，就以此位置为结束位置，从可读索引到结束位置就组成了一行
                                    //它是以换行符为结束标志的解码器，支持携带结束符或者不携带结束符两种解码方式。
                                    //同时支持配置单行最大长度，如果连续读取到最大长度后仍然没有发现换行符，就会抛出异常，同时忽略掉之前读到的异常码流
                                    .addLast(new LineBasedFrameDecoder(1024))
                                    //StringDecoder将接收到的对象转换成字符串
                                    .addLast(new StringDecoder())
                                    .addLast(new ChannelInboundHandlerAdapter() {
                                        private int counter = 0;
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                            super.channelRead(ctx, msg);
//                                            ByteBuf in = (ByteBuf) msg;
                                            System.out.println(msg + ", received counter is " + ++counter);
//                                            in.toString(CharsetUtil.UTF_8).substring(0, )
//                                            ctx.write(msg);
                                        }

                                        @Override
                                        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//                                            super.channelReadComplete(ctx);
                                            ctx.flush();
                                        }

                                        @Override
                                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//                                            super.exceptionCaught(ctx, cause);
                                            cause.printStackTrace();
                                            ctx.close();
                                        }
                                    });
                        }
                    });
            ChannelFuture f = b.bind().sync();  //6.bind方法绑定监听端口，随后调用同步阻塞方法sync等待绑定操作完成
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();        //7.调动NIO线程组的方法优雅退出，释放相关资源
        }
    }

    public static void main(String[] args) throws Exception {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(8080);
    }
}
