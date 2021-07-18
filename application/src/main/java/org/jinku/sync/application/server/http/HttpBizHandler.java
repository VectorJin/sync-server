package org.jinku.sync.application.server.http;

import com.google.common.base.Preconditions;
import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.server.AbstractHttpHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
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
        Preconditions.checkNotNull(reqHandler);
        return reqHandler.handleReq(paramsMap);
    }
}
