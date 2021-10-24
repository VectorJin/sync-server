package org.jinku.sync.application.server.comet;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.param.PullSyncParam;
import org.jinku.sync.domain.entity.SyncEntity;
import org.jinku.sync.domain.service.SyncQueueService;
import org.jinku.sync.domain.types.BizType;
import org.jinku.sync.domain.types.ReqType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PullSyncReqHandler implements ReqHandler {

    private final SyncQueueService syncQueueService;

    public PullSyncReqHandler(SyncQueueService syncQueueService) {
        this.syncQueueService = syncQueueService;
    }

    @Override
    public ReqType getReqType() {
        return ReqType.PULL_SYNC;
    }

    @Override
    public ResultAo handleReq(String reqData, ChannelHandlerContext ctx) {
        // 从Sync队列获取
        PullSyncParam param = JSON.parseObject(reqData, PullSyncParam.class);
        List<SyncEntity> syncEntities = syncQueueService.pullSync(BizType.get(param.getBizType()),
                param.getBizUuid(), param.getLastSyncId(), param.getMaxSize());
        return ResultAo.success(syncEntities);
    }
}
