package org.jinku.sync.application.server.comet;

import io.netty.channel.ChannelHandlerContext;
import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.domain.types.ReqType;

public interface ReqHandler {

    ReqType getReqType();

    ResultAo handleReq(String reqData, ChannelHandlerContext ctx);
}
