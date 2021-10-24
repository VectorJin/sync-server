package org.jinku.sync.domain.service;

import org.jinku.sync.domain.entity.SyncEntity;
import org.jinku.sync.domain.types.BizType;

import java.util.List;

public interface SyncQueueService {

    long addSync(SyncEntity syncEntity);

    List<SyncEntity> pullSync(BizType bizType, String bizUuid,
                              long lastSyncId, int maxSize);
}
