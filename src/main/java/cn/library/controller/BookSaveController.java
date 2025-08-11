package cn.library.controller;

import cn.library.aop.Pager;
import cn.library.context.LocalThreadHolder;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookSaveQueryDto;
import cn.library.pojo.entity.BookSave;
import cn.library.pojo.vo.BookSaveVO;
import cn.library.service.BookSaveService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书架收藏的 Controller
 */
@RestController
@RequestMapping(value = "/bookSave")
public class BookSaveController {

    @Resource
    private BookSaveService bookSaveService;

    /**
     * 书架收藏新增
     *
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody BookSave bookSave) {
        return bookSaveService.save(bookSave);
    }

    /**
     * 书架收藏删除
     *
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return bookSaveService.batchDelete(ids);
    }

    /**
     * 书架收藏修改
     *
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody BookSave bookSave) {
        return bookSaveService.update(bookSave);
    }

    /**
     * 书架收藏用户查询
     *
     */
    @Pager
    @PostMapping(value = "/queryUser")
    public Result<List<BookSaveVO>> queryUser(@RequestBody BookSaveQueryDto bookSaveQueryDto) {
        bookSaveQueryDto.setUserId(LocalThreadHolder.getUserId());
        if (bookSaveQueryDto.getCurrent() != null && bookSaveQueryDto.getSize() != null) {
            int page = bookSaveQueryDto.getCurrent();
            if (page < 1) page = 1;
            int offset = (page - 1) * bookSaveQueryDto.getSize();
            bookSaveQueryDto.setOffset(offset);
        }
        return bookSaveService.query(bookSaveQueryDto);
    }


    /**
     * 书架收藏查询
     *
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<BookSaveVO>> query(@RequestBody BookSaveQueryDto bookSaveQueryDto) {
        return bookSaveService.query(bookSaveQueryDto);
    }

}
