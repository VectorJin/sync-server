package org.jinku.sync.repository;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.jinku.sync.domain.repository.UserSessionRepository;

import io.netty.channel.Channel;
import org.jinku.sync.domain.types.CommandType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserSessionRepositoryImpl implements UserSessionRepository {
    /**
     * 内存中维护用户ID与长连接的映射
     */
    private final Map<String, Channel> userChannelMap = new ConcurrentHashMap<>();

    @Override
    public void userSignIn(String userId, Object channel) {
        userChannelMap.put(userId, (Channel) channel);
    }

    @Override
    public void notifyUserNewSync(String userId) {
        Channel channel = userChannelMap.get(userId);
        if (channel == null) {
            return;
        }
        JSONObject obj = new JSONObject();
        obj.put("command", CommandType.PULL_SYNC.getValue());
        channel.writeAndFlush(new TextWebSocketFrame(obj.toJSONString()));
    }

    @Override
    public List<String> latestOnlineList(int maxSize) {
        return Lists.newArrayList(userChannelMap.keySet()).subList(0, maxSize);
    }
}
