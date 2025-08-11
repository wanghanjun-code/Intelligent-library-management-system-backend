package cn.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CozeAIServiceTest {

    @Autowired
    private CozeAIService cozeAIService;

    @Test
    public void testCallCozeAPI() {
        System.out.println("Test running");
        
        // 测试参数
        String query = "红楼梦";
        String conversationId = "234";
        String userId = "user_123";

        // 调用API并获取响应
        String response = cozeAIService.callCozeAPI(query, conversationId, userId);
        
        // 打印响应结果
        System.out.println("API响应结果：");
        System.out.println(response);
    }
} 