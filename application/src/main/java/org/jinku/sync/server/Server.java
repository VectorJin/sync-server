package org.jinku.sync.server;

/**
 * Created by jinku on 2018/3/27.
 */
public interface Server {

    /**
     * 启动
     */
    void open();

    /**
     * 关闭
     */
    void close();
}
