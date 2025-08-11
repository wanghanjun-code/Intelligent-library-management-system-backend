package cn.library.controller;

import cn.library.aop.Pager;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookQueryDto;
import cn.library.pojo.entity.Book;
import cn.library.pojo.vo.BookVO;
import cn.library.pojo.vo.ChartVO;
import cn.library.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍的 Controller
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Resource
    private BookService bookService;

    /**
     * 书籍新增
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody Book book) {
        return bookService.save(book);
    }

    /**
     * 书籍删除
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return bookService.batchDelete(ids);
    }

    /**
     * 书籍修改
     *
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody Book book) {
        return bookService.update(book);
    }

    /**
     * 书籍查询
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<BookVO>> query(@RequestBody BookQueryDto bookQueryDto) {
        return bookService.query(bookQueryDto);
    }

    /**
     * 统计图书存量数据
     */
    @GetMapping(value = "/daysQuery/{day}")
    @ResponseBody
    public Result<List<ChartVO>> query(@PathVariable Integer day) {
        return bookService.daysQuery(day);
    }


}
