package cn.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CozeService {
    private final RestTemplate restTemplate;
    private final String botId;
    private final ObjectMapper objectMapper;
    private static final String API_ENDPOINT = "https://api.coze.cn/v3/chat";

    public CozeService(@Value("${coze.bot-id}") String botId, @Value("${coze.access-token}") String token) {
        this.botId = botId;
        this.objectMapper = new ObjectMapper();
        this.restTemplate = new RestTemplate();
    }

    public String streamChatAndGetFinalReply(String query) {
        try {
            // 构建请求体
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("bot_id", botId);
            requestBody.put("user", "user_" + System.currentTimeMillis());
            requestBody.put("query", query);
            requestBody.put("stream", false);

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + botId);
            headers.set("Accept", "*/*");
            headers.set("Host", "api.coze.cn");
            headers.set("Connection", "keep-alive");

            // 创建请求实体
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            // 发送请求
            return restTemplate.postForObject(API_ENDPOINT, requestEntity, String.class);

        } catch (Exception e) {
            throw new RuntimeException("Error in Coze API call: " + e.getMessage());
        }
    }
} 