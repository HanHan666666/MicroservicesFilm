package com.system.result;

import lombok.Data;
import lombok.ToString;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class R {
     private Integer code; // 响应状态码
    private String message;  // 返回状态信息
    private Map<String, Object> data = new HashMap<>();

    // 请求成功，返回结果
    public static R ok() {
        R r = new R();
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("请求成功");
        return r;
    }

    // 请求失败，返回结果
    public static R error(Integer code, String message) {
        R r = new R();
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    // 设置data数据
    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}