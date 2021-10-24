package org.jinku.sync.domain.service.impl;

import org.jinku.sync.domain.entity.SyncEntity;
import org.jinku.sync.domain.service.SyncQueueService;
import org.jinku.sync.domain.types.BizType;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocalSyncQueueServiceImpl implements SyncQueueService {
    private final Map<String, Vector<SyncEntity>> queueMap = new ConcurrentHashMap<>();

    @Override
    public long addSync(SyncEntity syncEntity) {
        String queueKey = buildQueueKey(syncEntity.getBizType(), syncEntity.getBizUuid());
        syncEntity.setSyncId(syncId());
        Vector<SyncEntity> queue = queueMap.get(queueKey);
        if (queue == null) {
            queue = new Vector<>();
            Vector<SyncEntity> existOrNewQueue = queueMap.putIfAbsent(queueKey, queue);
            if (existOrNewQueue != null) {//并发情况别的线程已经写入，使用对方写入的队列
                queue = existOrNewQueue;
            }
        }
        queue.add(syncEntity);
        return syncEntity.getSyncId();
    }

    @Override
    public List<SyncEntity> pullSync(BizType bizType, String bizUuid, long lastSyncId, int maxSize) {
        String queueKey = buildQueueKey(bizType, bizUuid);
        Vector<SyncEntity> queue = queueMap.get(queueKey);
        if (queue == null) {
            return Collections.emptyList();
        }
        List<SyncEntity> copiedList = new ArrayList<>(queue);
        int index = Collections.binarySearch(copiedList.stream().map(SyncEntity::getSyncId).collect(Collectors.toList()), lastSyncId);
        if (index == -copiedList.size()) {//比当前所有的都大
            return Collections.emptyList();
        }

        int fromIndex = index >= 0 ? index + 1 : -index - 1;
        int toIndex = Math.min(fromIndex + maxSize, copiedList.size());
        return copiedList.subList(fromIndex, toIndex);
    }

    private String buildQueueKey(BizType bizType, String bizUuid) {
        return bizType.getValue() + "_" + bizUuid;
    }

    private final AtomicLong atomicLong = new AtomicLong();

    private long syncId() {
        return atomicLong.incrementAndGet();
    }
}
