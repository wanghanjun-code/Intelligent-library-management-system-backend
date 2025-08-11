package cn.library.controller;

import cn.library.service.CozeAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/coze")
public class CozeAIController {
    private static final Logger logger = LoggerFactory.getLogger(CozeAIController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CozeAIService cozeAIService;

    /**
     * 聊天接口
     * {
     *     "query": "用户输入的消息",
     *     "conversation_id": "会话ID",
     *     "user": "用户ID"
     * }
     * 
     */
    @PostMapping("/chat")
    public String chat(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        String conversationId = request.get("conversation_id");
        String userId = request.get("user");

        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("query cannot be empty");
        }

        if (conversationId == null || conversationId.trim().isEmpty()) {
            conversationId = String.valueOf(System.currentTimeMillis());
        }

        if (userId == null || userId.trim().isEmpty()) {
            userId = "user_" + System.currentTimeMillis();
        }

        return cozeAIService.callCozeAPI(query, conversationId, userId);
    }

    /**
     * 智能推荐接口（新botid）
     * {
     *     "query": "用户输入的消息",
     *     "conversation_id": "会话ID",
     *     "user": "用户ID"
     * }
     */
    @PostMapping("/recommend")
    public String recommend(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        String conversationId = request.get("conversation_id");
        String userId = request.get("user");
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("query cannot be empty");
        }
        if (conversationId == null || conversationId.trim().isEmpty()) {
            conversationId = String.valueOf(System.currentTimeMillis());
        }
        if (userId == null || userId.trim().isEmpty()) {
            userId = "user_" + System.currentTimeMillis();
        }
        // 直接调用Coze API，但用新botid
        return callCozeAPIWithBotId(query, conversationId, userId, "7519378977639874600");
    }

    // 新增一个方法，允许指定botid
    private String callCozeAPIWithBotId(String query, String conversationId, String userId, String botId) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
            com.fasterxml.jackson.databind.node.ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("conversation_id", conversationId);
            requestBody.put("bot_id", botId);
            requestBody.put("user", userId);
            requestBody.put("query", query);
            requestBody.put("stream", false);
            String requestBodyJson = objectMapper.writeValueAsString(requestBody);
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + cozeAIService.getApiKey());
            headers.set("Accept", "*/*");
            headers.set("Host", "api.coze.cn");
            headers.set("Connection", "keep-alive");
            org.springframework.http.HttpEntity<String> requestEntity = new org.springframework.http.HttpEntity<>(requestBodyJson, headers);
            org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
            return restTemplate.postForObject("https://api.coze.cn/open_api/v2/chat", requestEntity, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Error calling Coze API: " + e.getMessage());
        }
    }
} 