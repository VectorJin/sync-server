package org.jinku.sync.server.comet;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import org.jinku.sync.server.AbstractServer;

public class CometServer extends AbstractServer {
    @Override
    public String getDesc() {
        return "长连接服务";
    }

    @Override
    public int getPort() {
        return 80;
    }

    @Override
    public void addHandler(ChannelPipeline pipeline) {
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new WebSocketServerCompressionHandler());
        pipeline.addLast(new WebSocketServerProtocolHandler("websocket", null, true));
    }

    @Override
    public void onClose() {

    }
}
