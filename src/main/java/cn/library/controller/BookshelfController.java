package cn.library.controller;

import cn.library.aop.Pager;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookshelfQueryDto;
import cn.library.pojo.entity.BookShelf;
import cn.library.service.BookshelfService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书架的 Controller
 */
@RestController
@RequestMapping(value = "/bookshelf")
public class BookshelfController {

    @Resource
    private BookshelfService bookshelfService;

    /**
     * 书架新增
     *
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody BookShelf bookShelf) {
        return bookshelfService.save(bookShelf);
    }

    /**
     * 书架删除
     *
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return bookshelfService.batchDelete(ids);
    }

    /**
     * 书架修改
     *
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody BookShelf bookShelf) {
        return bookshelfService.update(bookShelf);
    }

    /**
     * 书架查询
     *
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<BookShelf>> query(@RequestBody BookshelfQueryDto bookshelfQueryDto) {
        return bookshelfService.query(bookshelfQueryDto);
    }

}
