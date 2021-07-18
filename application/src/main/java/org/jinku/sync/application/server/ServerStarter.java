package org.jinku.sync.application.server;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServerStarter implements InitializingBean {

    private final List<Server> serverList;

    public ServerStarter(List<Server> serverList) {
        this.serverList = serverList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        serverList.forEach(Server::open);
    }
}
