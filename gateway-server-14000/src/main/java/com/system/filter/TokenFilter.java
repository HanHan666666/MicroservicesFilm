package com.system.filter;

import com.alibaba.fastjson.JSON;
import com.system.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenFilter implements GlobalFilter {

    private Mono<Void> responseWrite(ServerHttpResponse response, Integer code, String message) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);// 401状态码没有访问权限
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);// 相当于 ContentTyep ="application/json"
        Map<String, Object> map = new HashMap<>();
        map.put("code", 2000);
        map.put("message", message);
        byte[] bytes = JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8);
        DataBuffer bfffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(bfffer));// 将封装错误r对象，响应客户端
    }
    // //基于Netty  和 Servlet对象不一样的，ServletConetxt application级别 思路基本一致。
    //    //参数1：ServerWebExchange 请求上下文对象。  获得 reuqest对象和
    // response响应对象
    //    //参数2：GatewayFilterChain 跳转到过滤器之后访问资源   类似Filter中filterChain


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        // ServerWebExchange请求上下文request response   因为网关不是基于servlet的不能用web  servletContext
        // chain过滤链  跳转到过滤器之后的访问资源  token存在request中
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        JwtUtils jwtUtils = new JwtUtils();
        String path = request.getPath().toString();

        log.info("网关过滤请求路径是{}", path);// 如果是登录/login和注册/register页面还有/getCode/*验证码页面，不需要token
        if (path.contains("/login") || path.contains("/register") || path.contains("/getCode/")) {
            log.info("不需要token，直接放行");
            return chain.filter(exchange);
        }

        String token = request.getHeaders().getFirst("token");
        if (StringUtils.isBlank(token)) {
            log.info("token是空------");
            // 前端请求，如果token是空，返回错误 R 封装对象//将封装错误R对象，响应客户端
            // return this.responseWrite(response, 20001, "token为空，没有权限访问");

            // 测试需要，暂时不验证token，放行所有
            return chain.filter(exchange);

        } else {
            Claims claims = jwtUtils.getClaimByToken(token);// 验证
            if (claims == null) {
                log.info("token验证失败,错误的token信息");
                return this.responseWrite(response, 20001, "token验证失败，没有权限访问");
            } else if (jwtUtils.isTokenExpired(claims)) {
                log.info("token过期");
                return this.responseWrite(response, 20001, "token过期，没有权限访问");
            }
        }
        return chain.filter(exchange);
    }
}
