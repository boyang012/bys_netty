package com.byss.http.channel.initializer;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;

import com.byss.http.handler.HttpHandler;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;

public class SSLChannelInitializer extends ChannelInitializer<SocketChannel>{
	private final SslContext sslContext;

    public SSLChannelInitializer() {
        String keyStoreFilePath = "C:\\work\\boyang\\sts\\bys_netty\\.ssl\\test.pkcs12";
        String keyStorePassword = "Password@123";

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream(keyStoreFilePath), keyStorePassword.toCharArray());

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());

            sslContext = SslContextBuilder.forServer(keyManagerFactory).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } 
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerAdded(ctx);
	}

	@Override
	protected void initChannel(SocketChannel socketChannel) throws Exception {
		// TODO Auto-generated method stub
		 System.out.println("initChannel ch:" + socketChannel);
		 /*socketChannel.pipeline()
                 .addLast("decoder", new HttpRequestDecoder())   // 1
                 .addLast("encoder", new HttpResponseEncoder())  // 2
                 .addLast("aggregator", new HttpObjectAggregator(512 * 1024))    // 3
                 .addLast("handler", new HttpHandler());    */    // 4
		 
		 
		 ChannelPipeline pipeline = socketChannel.pipeline();
		 ByteBufAllocator byteBufAlloc =  socketChannel.alloc();
//	     SSLEngine sslEngine = sslContext.newEngine(byteBufAlloc);
	     pipeline/*.addLast(new SslHandler(sslEngine))*/
	     		 .addLast("decoder", new HttpRequestDecoder())
	             .addLast("encoder", new HttpResponseEncoder())
	             .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
	             .addLast("handler", new HttpHandler());
	}

}
