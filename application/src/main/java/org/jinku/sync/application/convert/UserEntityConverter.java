package org.jinku.sync.application.convert;

import org.jinku.sync.application.param.UserRegisterParam;
import org.jinku.sync.domain.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityConverter {
    public UserEntity convert(UserRegisterParam param) {
        return new UserEntity(param.getUserName(), param.getNickName(), param.getEmail());
    }
}
