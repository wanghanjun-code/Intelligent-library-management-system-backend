package cn.library.Interceptor;

import cn.library.context.LocalThreadHolder;
import cn.library.pojo.api.ApiResult;
import cn.library.pojo.api.Result;
import cn.library.utils.JwtUtil;
import com.alibaba.fastjson2.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * token拦截器，做请求拦截
 * 思路：用户登录成功后，会得到后端生成的 token，前端会将token存储于本地
 * 随后的接口请求，都会在协议头带上token
 * 所有请求执行之前，都会被该拦截器拦截：token校验通过则正常放行请求，否则直接返回
 */
public class JwtInterceptor implements HandlerInterceptor {

    /**
     * 前置拦截
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return boolean true ： 放行；false拦截
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        System.out.println("Request URI: " + requestURI);
        // 放行预检请求
        if ("OPTIONS".equals(requestMethod)) {
            return true;
        }
        // 登录及错误等请求不做拦截
        if (requestURI.contains("/login") || 
            requestURI.contains("/error") || 
            requestURI.contains("/file") || 
            requestURI.contains("/register") ||
            requestURI.contains("/ai/chat") ||
            requestURI.contains("/api/coze/chat") ||
            requestURI.contains("/chat") ||
            requestURI.contains("/api/book-manage-sys-api/v1.0/chat")) {
            System.out.println("Bypass condition met for URI: " + requestURI);
            return true;
//            System.out.println("This should not be printed if return true works as expected."); // Unreachable code?
        }
        String token = request.getHeader("token");
        Claims claims = JwtUtil.fromToken(token);
        if (claims == null) {
            System.out.println("--- Generating Authentication Error in JwtInterceptor ---"); // Debug print
            Result<String> error = ApiResult.error("身份认证异常，请先登录");
            response.setContentType("application/json;charset=UTF-8");
            Writer stream = response.getWriter();
            stream.write(JSONObject.toJSONString(error));
            stream.flush();
            stream.close();
            return false;
        }
        Integer userId = claims.get("id", Integer.class);
        Integer roleId = claims.get("role", Integer.class);
        LocalThreadHolder.setUserId(userId, roleId);
        return true;
    }
}
