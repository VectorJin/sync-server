package org.jinku.sync.application.server;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinku on 2018/4/14.
 */
public class RequestParser {

    public static Map<String, String> parseParam(FullHttpRequest httpRequest) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        if (httpRequest == null) {
            return paramsMap;
        }
        String uri = httpRequest.uri();
        String content = httpRequest.content().toString(CharsetUtil.UTF_8);
        if (uri.contains("?")) {
            content = uri.substring(uri.lastIndexOf("?") + 1) + "&" + content;
        }
        String[] paramPairArray = content.split("&");
        if (paramPairArray.length == 0) {
            return paramsMap;
        }
        for (String paramPair : paramPairArray) {
            if (paramPair == null || "".equals(paramPair.replaceAll(" ", ""))) {
                continue;
            }
            if (!paramPair.contains("=")) {
                continue;
            }
            String[] keyValue = paramPair.split("=");
            if (keyValue.length != 2) {
                continue;
            }
            if (keyValue[0] == null || "".equals(keyValue[0].replaceAll(" ", ""))) {
                continue;
            }
            if (keyValue[1] == null || "".equals(keyValue[1].replaceAll(" ", ""))) {
                continue;
            }
            paramsMap.put(keyValue[0], keyValue[1]);
        }
        return paramsMap;
    }
}
