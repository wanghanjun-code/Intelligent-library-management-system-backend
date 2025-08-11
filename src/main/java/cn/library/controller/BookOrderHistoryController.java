package cn.library.controller;

import cn.library.aop.Log;
import cn.library.aop.Pager;
import cn.library.context.LocalThreadHolder;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookOrderHistoryQueryDto;
import cn.library.pojo.entity.BookOrderHistory;
import cn.library.pojo.vo.BookOrderHistoryVO;
import cn.library.service.BookOrderHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 图书借阅历史数据的 Controller
 */
@RestController
@RequestMapping(value = "/bookOrderHistory")
public class BookOrderHistoryController {

    @Resource
    private BookOrderHistoryService bookOrderHistoryService;

    /**
     * 图书借阅历史数据新增
     *
     */
    @Log(content = "借阅书籍")
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody BookOrderHistory bookOrderHistory) {
        return bookOrderHistoryService.save(bookOrderHistory);
    }

    /**
     * 图书借阅历史数据删除
     *
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return bookOrderHistoryService.batchDelete(ids);
    }

    /**
     * 图书借阅历史数据修改
     *
     */
    @Log(content = "归还书籍")
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody BookOrderHistory bookOrderHistory) {
        return bookOrderHistoryService.update(bookOrderHistory);
    }

    /**
     *
     */
    @Pager
    @PostMapping(value = "/queryUser")
    public Result<List<BookOrderHistoryVO>> queryUser(@RequestBody BookOrderHistoryQueryDto dto) {
        dto.setUserId(LocalThreadHolder.getUserId());
        return bookOrderHistoryService.query(dto);
    }

    /**
     * 图书借阅历史数据查询
     *
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<BookOrderHistoryVO>> query(@RequestBody BookOrderHistoryQueryDto dto) {
        return bookOrderHistoryService.query(dto);
    }

}
