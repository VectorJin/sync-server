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
    private final BizType bizType;
    private final String bizUuid;
    private final String dataJson;

    public SyncEntity(BizType bizType, String bizUuid, String dataJson) {
        Preconditions.checkNotNull(bizType);
        Preconditions.checkNotNull(bizUuid);
        Preconditions.checkNotNull(dataJson);
        this.bizType = bizType;
        this.bizUuid = bizUuid;
        this.dataJson = dataJson;
    }

    public void setSyncId(long syncId) {
        this.syncId = syncId;
    }
}
