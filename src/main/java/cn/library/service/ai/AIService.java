package cn.library.service.ai;

import cn.library.pojo.api.Result;

/**
 * AI服务接口
 */
public interface AIService {
    /**
     * AI聊天
     *
     */
    Result<String> chat(String prompt);

    /**
     * AI摘要生成
     *
     */
    Result<String> summary(String text);

    /**
     * AI主题分析
     *
     */
    Result<String> themes(String text);
} 