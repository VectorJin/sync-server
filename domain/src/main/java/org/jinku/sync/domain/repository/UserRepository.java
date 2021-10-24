package org.jinku.sync.domain.repository;

import org.jinku.sync.domain.entity.UserEntity;

import java.util.List;

public interface UserRepository {

    void userRegister(UserEntity user);

    List<UserEntity> getUsers(List<String> userIds);
}
