package org.jinku.sync.application.param;

import lombok.Data;

@Data
public class PushSyncParam {
    private String userId;
    private int bizType;
    private String bizUuid;
    private String dataJson;
}
