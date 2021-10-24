package org.jinku.sync.application.server.http;

import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.convert.UserEntityConverter;
import org.jinku.sync.application.param.UserRegisterParam;
import org.jinku.sync.domain.entity.UserEntity;
import org.jinku.sync.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserRegisterReqHandler extends AbstractHttReqHandler<UserRegisterParam> {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserEntityConverter userEntityConverter;

    @Override
    protected ResultAo handReqObj(UserRegisterParam userRegisterParam) {
        UserEntity user = userEntityConverter.convert(userRegisterParam);
        userRepository.userRegister(user);
        return ResultAo.success(user.getUserId());
    }

    @Override
    public String supportUrl() {
        return "/user/register";
    }
}
