package org.jinku.sync.repository.session;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {

    private final Map<String, Channel> USER_CHANNEL_MAP = new ConcurrentHashMap<>();
    private final static SessionManager INSTANCE = new SessionManager();

    private SessionManager() {}

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public void userDeviceSignIn(String userId, Channel channel) {
        USER_CHANNEL_MAP.put(userId, channel);
        // TODO 缓存存储userId -> 机器IP映射
    }

    public void clear() {
        USER_CHANNEL_MAP.clear();
    }
}
