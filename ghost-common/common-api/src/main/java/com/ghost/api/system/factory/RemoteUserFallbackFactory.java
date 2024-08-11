package com.ghost.api.system.factory;

import com.ghost.api.system.remote.RemoteSystemUserService;
import com.ghost.model.GhostResult;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 *
 * @author yaols
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteSystemUserService> {


    @Override
    public RemoteSystemUserService create(Throwable cause) {
        return userName -> GhostResult.error("用户不存在");
    }
}
