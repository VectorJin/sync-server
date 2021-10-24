package org.jinku.sync.converter;

import org.jinku.sync.domain.entity.UserEntity;
import org.jinku.sync.repository.dao.po.UserPo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserPoConverter {

    public UserPo convert(UserEntity user) {
        UserPo userPo = new UserPo();
        userPo.setUserId(user.getUserId());
        userPo.setUserName(user.getUserName());
        userPo.setNickName(user.getNickName());
        userPo.setEmail(user.getEmail());
        return userPo;
    }

    public List<UserEntity> revertList(List<UserPo> userPos) {
       return userPos.stream().map(userPo -> {
            UserEntity userEntity = new UserEntity(userPo.getUserId(), userPo.getNickName(), userPo.getEmail());
            userEntity.setUserId(userPo.getUserId());
            return userEntity;
        }).collect(Collectors.toList());
    }
}
