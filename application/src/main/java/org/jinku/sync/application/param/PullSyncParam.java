package org.jinku.sync.application.param;

import lombok.Data;

@Data
public class PullSyncParam {
    private String userId;
    private String deviceId;
    private Integer bizType;
    private String bizUuid;
    private String lastSyncId;
}
