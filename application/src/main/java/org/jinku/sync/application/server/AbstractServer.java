package org.jinku.sync.application.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.NettyRuntime;
import io.netty.util.internal.SystemPropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractServer implements Server {

    protected Logger logger;
    private volatile boolean isStarted;
    private Channel channel;

    public AbstractServer() {
        logger = LoggerFactory.getLogger(getClass());
    }

    public void open() {
        if (isStarted) {
            return;
        }
        isStarted = true;

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        addHandler(ch.pipeline());
                    }
                });
        try {
            ChannelFuture channelFuture = bootstrap.bind(getPort()).sync();
            channel = channelFuture.channel();
            channelFuture.channel().closeFuture().syncUninterruptibly();
        } catch (Exception e) {
            logger.error(getDesc() + " server error !!!", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            close();
        }
        logger.info(getDesc() + " server start !!!");
    }

    public void close() {
        if (channel != null) {
            // unbind.
            channel.close();
        }
        // channels关闭
        onClose();
    }

    public int getWorkThreadNum() {
        return Math.max(1, NettyRuntime.availableProcessors() * 2);
    }

    public abstract String getDesc();

    public abstract int getPort();

    public abstract void addHandler(ChannelPipeline pipeline);

    public abstract void onClose();
}
