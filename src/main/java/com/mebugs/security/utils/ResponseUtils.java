package com.mebugs.security.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 通用的特殊处理返回
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-11-25
 */
public class ResponseUtils {

    /**
     * 需要登录的返回码
     */
    public static final int NEED_LOGIN = 50008;
    /**
     * 需要权限的返回码
     */
    public static final int NEED_AUTH = 50009;
    /**
     * 特殊的错误返回处理
     * @param code
     */
    public static void errorReturn(int code, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //通知前端 特定返回码
        JSONObject obj = new JSONObject();
        obj.put("code",code);
        PrintWriter out = response.getWriter();
        out.write(obj.toString());
        out.flush();
        out.close();
    }
}
