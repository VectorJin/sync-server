package org.jinku.sync.application.server.http;

import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.param.PushSyncParam;
import org.jinku.sync.domain.entity.SyncEntity;
import org.jinku.sync.domain.repository.UserSessionRepository;
import org.jinku.sync.domain.service.SyncQueueService;
import org.jinku.sync.domain.types.BizType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PushSyncReqHandler extends AbstractHttReqHandler<PushSyncParam> {

    @Resource
    private SyncQueueService syncQueueService;
    @Resource
    private UserSessionRepository userSessionRepository;

    @Override
    public String supportUrl() {
        return "/sync/pushSync";
    }

    @Override
    public ResultAo handReqObj(PushSyncParam param) {
        SyncEntity syncEntity = new SyncEntity(param.getUserId(), BizType.get(param.getBizType()),
                param.getBizUuid(), param.getDataJson());
        long syncId = syncQueueService.addSync(syncEntity);
        userSessionRepository.notifyUserNewSync(param.getUserId());
        return ResultAo.success(syncId);
    }
}
