package cn.library.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Service
public class CozeAIService {
    private static final Logger logger = LoggerFactory.getLogger(CozeAIService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String botId;
    private final String apiKey;
    private static final String API_ENDPOINT = "https://api.coze.cn/open_api/v2/chat";
    private final RestTemplate restTemplate;

    public CozeAIService(@Value("${coze.bot.id}") String botId, @Value("${coze.api.key}") String apiKey) {
        this.botId = botId;
        this.apiKey = apiKey;
        this.restTemplate = new RestTemplate();
    }
    public String callCozeAPI(String query, String conversationId, String userId) {
        // 验证 bot_id
        if (botId == null || botId.trim().isEmpty() || "0".equals(botId)) {
            logger.error("Invalid bot_id: {}", botId);
            throw new RuntimeException("Invalid bot_id configuration. Please check your application.properties file.");
        }
        logger.info("Using bot_id: {}", botId);

        try {
            // Constructing the request body
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("conversation_id", conversationId);
            requestBody.put("bot_id", botId.trim());
            requestBody.put("user", userId);
            requestBody.put("query", query);
            requestBody.put("stream", false);

            String requestBodyJson = objectMapper.writeValueAsString(requestBody);
            logger.info("Request body: {}", requestBodyJson);

            // Set request headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Accept", "*/*");
            headers.set("Host", "api.coze.cn");
            headers.set("Connection", "keep-alive");

            // Create a request entity
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBodyJson, headers);

            // Send Request
            return restTemplate.postForObject(API_ENDPOINT, requestEntity, String.class);

        } catch (Exception e) {
            logger.error("Error in Coze API call: ", e);
            throw new RuntimeException("Error calling Coze API: " + e.getMessage());
        }
    }

    public String getApiKey() {
        return this.apiKey;
    }
} 