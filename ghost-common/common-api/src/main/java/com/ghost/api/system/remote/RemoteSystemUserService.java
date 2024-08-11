package com.ghost.api.system.remote;

import com.ghost.model.GhostResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yaolsœ
 * @version 1.0
 * @since 2024/8/11 02:22
 */

@FeignClient(contextId = "remote-system-user-feign", name = "services-system", path = "")
public interface RemoteSystemUserService {

    @GetMapping(value = "findUser")
    GhostResult<?> findUser(String userName);



}
