package org.jinku.sync.application.server.http;

import org.jinku.sync.application.ao.ResultAo;

import java.util.Map;

public interface HttpReqHandler {

    String supportUrl();

    ResultAo handleReq(Map<String, String> reqParam);
}
