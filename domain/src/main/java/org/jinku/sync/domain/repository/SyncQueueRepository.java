package org.jinku.sync.domain.repository;

import org.jinku.sync.domain.entity.SyncEntity;
import org.jinku.sync.domain.types.BizType;

import java.util.List;

public interface SyncQueueRepository {

    long addSync(SyncEntity syncEntity);

    List<SyncEntity> pullSync(BizType bizType, String bizUuid,
                              long lastSyncId, int maxSize);
}