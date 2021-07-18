package org.jinku.sync.application.server.comet;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.param.PullSyncParam;
import org.jinku.sync.domain.entity.SyncEntity;
import org.jinku.sync.domain.repository.SyncQueueRepository;
import org.jinku.sync.domain.types.BizType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PullSyncReqHandler implements ReqHandler {

    private final SyncQueueRepository syncQueueRepository;

    public PullSyncReqHandler(SyncQueueRepository syncQueueRepository) {
        this.syncQueueRepository = syncQueueRepository;
    }

    @Override
    public ReqType getReqType() {
        return ReqType.PULL_SYNC;
    }

    @Override
    public ResultAo handleReq(String reqData, ChannelHandlerContext ctx) {
        // 从Sync队列获取
        PullSyncParam param = JSON.parseObject(reqData, PullSyncParam.class);
        List<SyncEntity> syncEntities = syncQueueRepository.pullSync(BizType.get(param.getBizType()),
                param.getBizUuid(), param.getLastSyncId(), param.getMaxSize());
        return ResultAo.success(syncEntities);
    }
}
