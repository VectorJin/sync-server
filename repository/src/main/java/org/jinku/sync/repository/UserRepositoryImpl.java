package org.jinku.sync.repository;

import com.google.common.base.Preconditions;
import org.jinku.sync.converter.UserPoConverter;
import org.jinku.sync.domain.entity.UserEntity;
import org.jinku.sync.domain.repository.UserRepository;
import org.jinku.sync.repository.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserDao userDao;
    @Resource
    private UserPoConverter userPoConverter;

    @Override
    public void userRegister(UserEntity user) {
        int result = userDao.save(userPoConverter.convert(user));
        Preconditions.checkArgument(result == 1);
    }

    @Override
    public List<UserEntity> getUsers(List<String> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        return userPoConverter.revertList(userDao.getByUserIds(userIds));
    }
}
