package com.meals.data.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * 统一数据返回结构
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-27
 */
@Setter
@Getter
@ToString
@Accessors(chain = true)
public class R {
    private static final long serialVersionUID = 1L;
    private int code = 200;
    private String msg;
    private Object data;

    public static R ok() {
        R r = new R();
        r.setMsg("操作成功");
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setMsg("操作成功");
        r.setData(data);
        return r;
    }

    public static R error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
