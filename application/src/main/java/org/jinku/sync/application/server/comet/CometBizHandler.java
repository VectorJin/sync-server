package org.jinku.sync.application.server.comet;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.bootstrap.ApplicationContextUtil;
import org.springframework.stereotype.Component;

@Component
public class CometBizHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        // ping and pong frames already handled
        if (!(msg instanceof TextWebSocketFrame)) {
            String message = "unsupported frame type: " + msg.getClass().getName();
            throw new UnsupportedOperationException(message);
        }
        String req = ((TextWebSocketFrame) msg).text();

        ResultAo resultAo = ApplicationContextUtil.getBean(ReqHandlerFactory.class).handleReq(req, ctx);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(resultAo)));
    }
}
