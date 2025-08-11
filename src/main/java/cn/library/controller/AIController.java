package cn.library.controller;

import cn.library.pojo.api.ApiResult;
import cn.library.pojo.api.Result;
import cn.library.service.ai.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    // Existing methods might be added later if needed, but for now, just the chat endpoint


    @PostMapping("/chat")
    public Result<String> chat(@RequestBody Map<String, String> requestBody) {
        String prompt = requestBody.get("prompt");
        if (prompt == null || prompt.trim().isEmpty()) {
            return ApiResult.error("聊天内容不能为空");
        }
        return aiService.chat(prompt);
    }


    @PostMapping("/recommend")
    public Result<String> recommend(@RequestBody Map<String, String> requestBody) {
        String prompt = requestBody.get("prompt");
        if (prompt == null || prompt.trim().isEmpty()) {
            return ApiResult.error("推荐内容不能为空");
        }
        try {
            String botId = "7519378977639874600";
            String token = "pat_di5r33qNJSBk3H5LDft4gqGzrx5FPHSI3VIRWiKgGRFAfrC6t1ljJ2CCpgu6nWIN";
            org.springframework.web.reactive.function.client.WebClient webClient = org.springframework.web.reactive.function.client.WebClient.builder()
                    .baseUrl("https://api.coze.cn/v3/chat")
                    .defaultHeader(org.springframework.http.HttpHeaders.AUTHORIZATION, token)
                    .defaultHeader(org.springframework.http.HttpHeaders.CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
                    .build();
            java.util.Map<String, Object> request = new java.util.HashMap<>();
            request.put("bot_id", botId);
            java.util.List<java.util.Map<String, String>> messages = new java.util.ArrayList<>();
            java.util.Map<String, String> userMessage = new java.util.HashMap<>();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.add(userMessage);
            request.put("messages", messages);
            request.put("stream", false);
            com.fasterxml.jackson.databind.JsonNode node = webClient.post()
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(com.fasterxml.jackson.databind.JsonNode.class)
                    .block();
            if (node != null && node.has("choices") && node.get("choices").isArray() && node.get("choices").size() > 0) {
                String reply = node.get("choices").get(0).get("message").get("content").asText();
                return new Result<>(200, reply);
            } else {
                return ApiResult.error("智能推荐服务无有效响应");
            }
        } catch (Exception e) {
            return ApiResult.error("智能推荐失败：" + e.getMessage());
        }
    }
} 