package cn.library;

import cn.library.service.CozeService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目启动类
 */
@MapperScan("cn.library.mapper")
@SpringBootApplication
@RestController
public class BookManageSysApplication {

    @Autowired
    private CozeService cozeService;

    public static void main(String[] args) {
        SpringApplication.run(BookManageSysApplication.class, args);
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String query) {
        return cozeService.streamChatAndGetFinalReply(query);
    }
}
