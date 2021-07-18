package org.jinku.sync.application.convert;

import org.jinku.sync.application.param.PushSyncParam;
import org.jinku.sync.domain.entity.SyncEntity;
import org.jinku.sync.domain.types.BizType;
import org.springframework.stereotype.Component;

@Component
public class SyncEntityConvert {

    public SyncEntity convert(PushSyncParam pushSyncParam) {
        return new SyncEntity(pushSyncParam.getUserId(), BizType.get(pushSyncParam.getBizType()),
                pushSyncParam.getBizUuid(), pushSyncParam.getDataJson());
    }
}
