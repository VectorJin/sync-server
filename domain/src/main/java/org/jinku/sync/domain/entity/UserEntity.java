package org.jinku.sync.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class UserEntity {
    private String userId;
    private final String userName;
    private final String nickName;
    private final String email;

    public UserEntity(String userName, String nickName, String email) {
        this.userName = userName;
        this.nickName = nickName;
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
