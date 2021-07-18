package org.jinku.sync.application.server.http;

import com.alibaba.fastjson.JSON;
import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.param.PushSyncParam;
import org.jinku.sync.domain.entity.SyncEntity;
import org.jinku.sync.domain.repository.SyncQueueRepository;
import org.jinku.sync.domain.types.BizType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PushSyncHttpReqHandler implements HttpReqHandler {

    private final SyncQueueRepository syncQueueRepository;

    public PushSyncHttpReqHandler(SyncQueueRepository syncQueueRepository) {
        this.syncQueueRepository = syncQueueRepository;
    }

    @Override
    public String supportUrl() {
        return "/pushSync";
    }

    @Override
    public ResultAo handleReq(Map<String, String> reqParam) {
        PushSyncParam pushSyncParam = JSON.parseObject(JSON.toJSONString(reqParam), PushSyncParam.class);
        SyncEntity syncEntity = new SyncEntity(pushSyncParam.getUserId(), BizType.get(pushSyncParam.getBizType()),
                pushSyncParam.getBizUuid(), pushSyncParam.getDataJson());
        return ResultAo.success(syncQueueRepository.addSync(syncEntity));
    }
}
