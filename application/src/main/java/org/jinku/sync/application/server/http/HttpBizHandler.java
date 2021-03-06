package org.jinku.sync.application.server.http;

import com.google.common.base.Preconditions;
import io.netty.channel.ChannelHandler;
import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.server.AbstractHttpHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@ChannelHandler.Sharable
public class HttpBizHandler extends AbstractHttpHandler {

    private final List<HttpReqHandler> handlerList;

    public HttpBizHandler(List<HttpReqHandler> handlerList) {
        this.handlerList = handlerList;
    }

    @Override
    public boolean isSupport(String uri) {
        return handlerList.stream().anyMatch(handler -> uri.endsWith(handler.supportUrl()));
    }

    @Override
    public ResultAo doService(String uri, Map<String, String> paramsMap) {
        HttpReqHandler reqHandler = handlerList.stream()
                .filter(handler -> uri.endsWith(handler.supportUrl()))
                .findFirst().orElse(null);
        Objects.requireNonNull(reqHandler);
        return reqHandler.handleReq(paramsMap);
    }
}
