package org.jinku.sync.application.server.http;

import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.server.AbstractHttpHandler;

import java.util.Map;

public class HttpBizHandler extends AbstractHttpHandler {

    @Override
    public String getUrl() {
        return "/pushSync";
    }

    @Override
    public ResultAo doService(Map<String, String> paramsMap) {
        // TODO 处理 sync 保存逻辑
        return null;
    }
}
