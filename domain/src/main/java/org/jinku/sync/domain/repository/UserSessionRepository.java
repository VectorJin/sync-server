package org.jinku.sync.domain.repository;

import java.util.List;

public interface UserSessionRepository {

    void userSignIn(String userId, Object channel);

    void notifyUserNewSync(String userId);

    List<String> latestOnlineList(int maxSize);
}
