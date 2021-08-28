package org.jinku.sync.application.server.comet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.jinku.sync.application.ao.ResultAo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
public class ReqHandlerFactory {

    private final List<ReqHandler> reqHandlerList;

    public ReqHandlerFactory(List<ReqHandler> reqHandlerList) {
        Preconditions.checkArgument(!CollectionUtils.isEmpty(reqHandlerList));
        this.reqHandlerList = reqHandlerList;
    }

    public ResultAo handleReq(String req, ChannelHandlerContext ctx) {
        JSONObject jsonObject;
        try {
            jsonObject = JSON.parseObject(req);
        } catch (Exception e) {
            log.error("解析请求参数失败, req:{}", req, e);
            return ResultAo.failed("[参数非法]必须为Json格式");
        }
        ReqType reqType =  ReqType.get(jsonObject.getIntValue("reqType"));

        ReqHandler reqHandler = reqHandlerList.stream()
                .filter(handler -> handler.getReqType() == reqType)
                .findFirst().orElse(null);

        if (reqHandler == null) {
            return ResultAo.failed("[参数非法]无法识别的请求");
        }
        try {
            return reqHandler.handleReq(jsonObject.getString("reqData"), ctx);
        } catch (Exception e) {
            log.error("请求处理异常, req:{}", req, e);
            return ResultAo.failed("请求处理异常");
        }
    }
}
