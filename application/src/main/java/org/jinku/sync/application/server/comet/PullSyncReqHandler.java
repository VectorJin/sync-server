package org.jinku.sync.application.server.comet;

import io.netty.channel.ChannelHandlerContext;
import org.jinku.sync.application.ao.ResultAo;

public class PullSyncReqHandler implements ReqHandler {
    @Override
    public ReqType getReqType() {
        return ReqType.PULL_SYNC;
    }

    @Override
    public ResultAo handleReq(String reqData, ChannelHandlerContext ctx) {
        // TODO 从Sync队列获取
        return null;
    }
}
