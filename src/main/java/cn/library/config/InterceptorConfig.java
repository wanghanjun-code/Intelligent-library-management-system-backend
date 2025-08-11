package cn.library.config;

import cn.library.Interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * API拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${my-server.api-context-path}")
    private String API;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器注册
        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                // 放行登录、注册、AI聊天请求
                .excludePathPatterns(
                        API + "/user/login",
                        API + "/user/register",
                        API + "/file/upload",
                        API + "/file/getFile",
                        API + "/ai/chat",
                        "/api/coze/chat",
                        API + "/chat"
                );
    }
}
