package org.jinku.sync.application.param;

import lombok.Data;

@Data
public class PullSyncParam {
    private String userId;
    private String deviceId;
    private int bizType;
    private String bizUuid;
    private long lastSyncId;
    private int maxSize;
}
