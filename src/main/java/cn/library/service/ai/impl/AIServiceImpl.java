package cn.library.service.ai.impl;

import cn.library.pojo.api.Result;
import cn.library.service.ai.AIService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AI服务实现类
 */
@Service
public class AIServiceImpl implements AIService {

    private final WebClient webClient;
    private final String botId;
    private final ObjectMapper objectMapper;

    private static final String SYSTEM_MESSAGE_ASSISTANT = "你是一个专业的图书管理系统AI助手，负责处理与图书相关的问题，如图书信息查询、内容分析、推荐，以及回答用户关于图书馆的常见问题。请以简洁、专业、友好的态度回应用户。如果问题超出你的能力范围，请告知用户并建议联系图书馆工作人员。";
    private static final String SYSTEM_MESSAGE_SUMMARY = SYSTEM_MESSAGE_ASSISTANT + " 你的当前任务是为用户提供的图书内容生成一个简洁准确的摘要。";
    private static final String SYSTEM_MESSAGE_THEMES = SYSTEM_MESSAGE_ASSISTANT + " 你的当前任务是分析用户提供的图书内容，并列出其关键主题。";

    public AIServiceImpl(
            @Value("${coze.bot-id}") String botId,
            @Value("${coze.access-token}") String token) {
        this.botId = botId;
        this.objectMapper = new ObjectMapper();
        
        this.webClient = WebClient.builder()
                .baseUrl("https://api.coze.cn/v3/chat")
                .defaultHeader(HttpHeaders.AUTHORIZATION, token)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Result<String> chat(String prompt) {
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("bot_id", botId);
            
            List<Map<String, String>> messages = new ArrayList<>();
            
            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", SYSTEM_MESSAGE_ASSISTANT);
            messages.add(systemMessage);
            
            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.add(userMessage);
            
            requestBody.put("messages", messages);
            requestBody.put("stream", false);

            String response = webClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(node -> {
                        if (node.has("choices") && node.get("choices").isArray() 
                            && node.get("choices").size() > 0) {
                            return node.get("choices").get(0)
                                    .get("message")
                                    .get("content")
                                    .asText();
                        }
                        throw new RuntimeException("无法从Coze服务获取有效响应或响应格式不正确");
                    })
                    .onErrorResume(e -> Mono.error(new RuntimeException("AI聊天失败：" + e.getMessage())))
                    .block();

            return new Result<>(200, response);
        } catch (Exception e) {
            return new Result<>(400, "AI聊天失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> summary(String text) {
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("bot_id", botId);
            
            List<Map<String, String>> messages = new ArrayList<>();
            
            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", SYSTEM_MESSAGE_SUMMARY);
            messages.add(systemMessage);
            
            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", "请为以下内容生成摘要：" + text);
            messages.add(userMessage);
            
            requestBody.put("messages", messages);
            requestBody.put("stream", false);

            String response = webClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(node -> {
                        if (node.has("choices") && node.get("choices").isArray() 
                            && node.get("choices").size() > 0) {
                            return node.get("choices").get(0)
                                    .get("message")
                                    .get("content")
                                    .asText();
                        }
                        throw new RuntimeException("无法从Coze服务获取有效响应或响应格式不正确");
                    })
                    .onErrorResume(e -> Mono.error(new RuntimeException("AI摘要生成失败：" + e.getMessage())))
                    .block();

            return new Result<>(200, response);
        } catch (Exception e) {
            return new Result<>(400, "AI摘要生成失败：" + e.getMessage());
        }
    }

    @Override
    public Result<String> themes(String text) {
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("bot_id", botId);
            
            List<Map<String, String>> messages = new ArrayList<>();
            
            Map<String, String> systemMessage = new HashMap<>();
            systemMessage.put("role", "system");
            systemMessage.put("content", SYSTEM_MESSAGE_THEMES);
            messages.add(systemMessage);
            
            Map<String, String> userMessage = new HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", "请分析以下内容的关键主题：" + text);
            messages.add(userMessage);
            
            requestBody.put("messages", messages);
            requestBody.put("stream", false);

            String response = webClient.post()
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .map(node -> {
                        if (node.has("choices") && node.get("choices").isArray() 
                            && node.get("choices").size() > 0) {
                            return node.get("choices").get(0)
                                    .get("message")
                                    .get("content")
                                    .asText();
                        }
                        throw new RuntimeException("无法从Coze服务获取有效响应或响应格式不正确");
                    })
                    .onErrorResume(e -> Mono.error(new RuntimeException("AI主题分析失败：" + e.getMessage())))
                    .block();

            return new Result<>(200, response);
        } catch (Exception e) {
            return new Result<>(400, "AI主题分析失败：" + e.getMessage());
        }
    }
} 