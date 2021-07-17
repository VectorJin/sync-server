package org.jinku.sync.application.server.comet;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import org.jinku.ddd.repository.session.SessionManager;
import org.jinku.sync.application.server.AbstractServer;
import org.springframework.stereotype.Component;

@Component("CometServer")
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
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new WebSocketServerCompressionHandler());
        pipeline.addLast(new WebSocketServerProtocolHandler("websocket"));
        pipeline.addLast(new CometBizHandler());
    }

    @Override
    public void onClose() {
        SessionManager.getInstance().clear();
    }
}
