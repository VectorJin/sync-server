package org.jinku.sync.application.server;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServerStarter implements InitializingBean {

    @Autowired
    private List<Server> serverList;

    @Override
    public void afterPropertiesSet() throws Exception {
        serverList.forEach(Server::open);
    }
}
