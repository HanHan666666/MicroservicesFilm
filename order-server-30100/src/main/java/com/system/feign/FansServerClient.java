package com.system.feign;

import com.system.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fans-server")
public interface FansServerClient {
    @GetMapping("/fans/port")
    String port();

    // Feign调用服务时，通过地址栏传递参数。
    @GetMapping("/fans/check/{id}")
    R check(@PathVariable Long id);
}
