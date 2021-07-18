package org.jinku.sync.application.server;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.jinku.sync.application.ao.ResultAo;

import java.util.Map;

/**
 * Created by jinku on 2018/4/14.
 */
public abstract class AbstractHttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequest) throws Exception {
        if (!isSupport(httpRequest.uri())) {
            sendHttpResponse(ctx, httpRequest, new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND));
            return;
        }

        String contentType = httpRequest.headers().get("Content-Type");
        if (!"application/x-www-form-urlencoded".equals(contentType)) {
            sendHttpResponse(ctx, httpRequest, new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN));
            return;
        }
        Map<String, String> paramsMap = RequestParser.parseParam(httpRequest);
        ResultAo resultAo = doService(httpRequest.uri(), paramsMap);
        String dataJson = JSONObject.toJSONString(resultAo);

        ByteBuf contentByteBuf = ctx.alloc().buffer(dataJson.getBytes().length);
        contentByteBuf.writeCharSequence(dataJson, CharsetUtil.UTF_8);
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, contentByteBuf);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, String.valueOf(contentByteBuf.readableBytes()));
        sendHttpResponse(ctx, httpRequest, response);
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, HttpRequest req, HttpResponse res) {
        // Send the response and close the connection if necessary.
        ChannelFuture f = ctx.channel().write(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    public abstract boolean isSupport(String uri);

    public abstract ResultAo doService(String uri, Map<String, String> paramsMap);
}
