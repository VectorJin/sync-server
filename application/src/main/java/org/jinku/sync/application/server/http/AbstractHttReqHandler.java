package org.jinku.sync.application.server.http;

import com.alibaba.fastjson.JSON;
import org.jinku.sync.application.ao.ResultAo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public abstract class AbstractHttReqHandler<T> implements HttpReqHandler{

    @Override
    public ResultAo handleReq(Map<String, String> params) {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return handReqObj(JSON.parseObject(JSON.toJSONString(params), type));
    }

    protected abstract ResultAo handReqObj(T t);
}
