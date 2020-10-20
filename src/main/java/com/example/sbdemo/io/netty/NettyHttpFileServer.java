package com.example.sbdemo.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class NettyHttpFileServer
{
    //文件服务器的根目录
    private static final String DEFAULT_URL="/src/main/resources/";

    public void run(final int port,final String url)throws Exception{
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workergGroup=new NioEventLoopGroup();
        try
        {
            ServerBootstrap b=new ServerBootstrap();
            b.group(bossGroup,workergGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    {

                        @Override
                        protected void initChannel(SocketChannel ch)
                                throws Exception
                        {
                            //HTTP请求消息解码器
                            ch.pipeline().addLast("http-decoder",
                                    new HttpRequestDecoder());
                            //将多个消息转换为单一的FullHttpRequest或者FullHttpResponse
                            ch.pipeline().addLast("http-aggregator",
                                    new HttpObjectAggregator(65536));
                            //HTTP响应消息编码器
                            ch.pipeline().addLast("http-encoder",
                                    new HttpResponseEncoder());
                            //支持异步发送大的码流，但不占用过多内存
                            ch.pipeline().addLast("http-chunked",
                                    new ChunkedWriteHandler());
                            ch.pipeline().addLast(
                                    new HttpFileServerHandler(url));
                        }

                    });
            ChannelFuture f=b.bind("127.0.0.1",port).sync();
            System.out.println("HTTP文件服务器启动，网址是："+"http://127.0.0.1:"+port+url);
            f.channel().closeFuture().sync();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            bossGroup.shutdownGracefully();
            workergGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception
    {
        int port =8888;
        if (args!=null&&args.length>0)
        {
            port=Integer.valueOf(args[0]);
        }
        new NettyHttpFileServer().run(port, DEFAULT_URL);
    }
}
