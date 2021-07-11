package org.jinku.sync.application.server.http;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import org.jinku.sync.application.server.AbstractServer;
import org.jinku.sync.application.server.comet.CometBizHandler;

public class HttpServer extends AbstractServer {

    @Override
    public String getDesc() {
        return "http服务";
    }

    @Override
    public int getPort() {
        return 8081;
    }

    @Override
    public int getWorkThreadNum() {
        return Math.max(1, super.getWorkThreadNum() / 2);
    }

    @Override
    public void addHandler(ChannelPipeline pipeline) {
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new HttpBizHandler());
    }

    @Override
    public void onClose() {

    }
}
