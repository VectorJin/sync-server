package org.jinku.sync.domain.entity;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.jinku.sync.domain.types.BizType;

@Getter
@EqualsAndHashCode
@ToString
public class SyncEntity {
    private long syncId;
    private final String userId;
    private final BizType bizType;
    private final String bizUuid;
    private final String dataJson;

    public SyncEntity(String userId, BizType bizType, String bizUuid, String dataJson) {
        this.userId = userId;
        this.bizType = bizType;
        this.bizUuid = bizUuid;
        this.dataJson = dataJson;
    }

    public void setSyncId(long syncId) {
        this.syncId = syncId;
    }
}
