package com.example.demo.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @Author zl
 * @CreateTime 2022-09-16 10:06:56
 * @Description
 */
public class ResponseUtil {

    public static void rendString(HttpServletResponse response,String context) throws IOException {
        response.setStatus(500);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(context);
        writer.flush();
        writer.close();
    }

    public static void rendSuccessString(HttpServletResponse response,String context) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(context);
        writer.flush();
        writer.close();
    }
}
