package org.jinku.sync.repository.dao;

import org.apache.ibatis.annotations.Param;
import org.jinku.sync.repository.dao.po.UserPo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    int save(UserPo user);

    List<UserPo> getByUserIds(@Param("userIds") List<String> userIds);
}
