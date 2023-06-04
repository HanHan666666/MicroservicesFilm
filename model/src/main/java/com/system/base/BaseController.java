package com.system.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    HttpServletRequest request;

    //获得分页数据 -前端请求传递参数：current、size。 得到参数 封装为Page对象(MP分页使用)。
    public Page getPage() {
        //得到分页数据的当前页码
        int current = ServletRequestUtils.getIntParameter(request, "current", 1);
        //得到一页显示的记录数
        int size = ServletRequestUtils.getIntParameter(request, "size", 5);
        return new Page(current, size);
    }
}
