package org.jinku.sync.application.server.http;

import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.domain.repository.UserRepository;
import org.jinku.sync.domain.repository.UserSessionRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserOnlineListReqHandler extends AbstractHttReqHandler<Void> {

    @Resource
    private UserRepository userRepository;
    @Resource
    private UserSessionRepository userSessionRepository;

    @Override
    protected ResultAo handReqObj(Void nonParam) {
        List<String> userIds = userSessionRepository.latestOnlineList(20);
        return ResultAo.success(userRepository.getUsers(userIds));
    }

    @Override
    public String supportUrl() {
        return "/user/onlineList";
    }
}
