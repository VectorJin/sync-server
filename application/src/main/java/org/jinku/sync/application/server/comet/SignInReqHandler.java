package org.jinku.sync.application.server.comet;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.StringUtils;
import org.jinku.sync.domain.repository.UserSessionRepository;
import org.jinku.sync.application.ao.ResultAo;
import org.jinku.sync.application.param.UserDeviceParam;
import org.jinku.sync.domain.types.ReqType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SignInReqHandler implements ReqHandler {

    @Resource
    private UserSessionRepository userSessionRepository;

    @Override
    public ReqType getReqType() {
        return ReqType.SIGN_IN;
    }

    @Override
    public ResultAo handleReq(String reqData, ChannelHandlerContext ctx) {
        UserDeviceParam userDevice = JSON.parseObject(reqData, UserDeviceParam.class);
        Preconditions.checkNotNull(userDevice, "用户设备签入信息非法: userDevice:{%s}", userDevice);
        Preconditions.checkArgument(StringUtils.isNotBlank(userDevice.getUserId()) && StringUtils.isNotBlank(userDevice.getDeviceId()),
                "用户设备签入信息非法: userDevice:{%s}", userDevice);
        // 用户设备签入
        userSessionRepository.userSignIn(userDevice.getUserId(), ctx.channel());
        // 返回签入成功
        return ResultAo.success();
    }
}
