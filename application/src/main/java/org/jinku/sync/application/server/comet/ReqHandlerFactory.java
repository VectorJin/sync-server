package org.jinku.sync.application.server.comet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.netty.channel.ChannelHandlerContext;
import org.jinku.sync.application.ao.ResultAo;

import java.util.HashMap;
import java.util.Map;

public class ReqHandlerFactory {

    private final static ReqHandlerFactory INSTANCE = new ReqHandlerFactory();
    private final Map<ReqType, ReqHandler> reqHandlerMap = new HashMap<>() ;

    private ReqHandlerFactory() {
        ReqHandler reqHandler = new SignInReqHandler();
        reqHandlerMap.put(reqHandler.getReqType(), reqHandler);
        reqHandler = new SignInReqHandler();
        reqHandlerMap.put(reqHandler.getReqType(), reqHandler);
    }

    public static ReqHandlerFactory getInstance() {
        return INSTANCE;
    }

    public ResultAo handleReq(String req, ChannelHandlerContext ctx) {
        JSONObject jsonObject = JSON.parseObject(req);
        ReqType reqType =  ReqType.get(jsonObject.getIntValue("reqType"));
        ReqHandler reqHandler = reqHandlerMap.get(reqType);
        Preconditions.checkNotNull(reqType, "请求参数不合法");
        return reqHandler.handleReq(jsonObject.getString("reqData"), ctx);
    }

}
