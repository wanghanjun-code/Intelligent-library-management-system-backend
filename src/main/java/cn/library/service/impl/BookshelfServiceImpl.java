package cn.library.service.impl;

import cn.library.mapper.BookShelfMapper;
import cn.library.pojo.api.ApiResult;
import cn.library.pojo.api.PageResult;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookshelfQueryDto;
import cn.library.pojo.entity.BookShelf;
import cn.library.service.BookshelfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书架业务逻辑实现
 */
@Service
public class BookshelfServiceImpl implements BookshelfService {

    @Resource
    private BookShelfMapper bookshelfMapper;

    /**
     * 书架新增
     *
     * @param bookShelf 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(BookShelf bookShelf) {
        bookshelfMapper.save(bookShelf);
        return ApiResult.success();
    }

    /**
     * 书架删除
     *
     * @param ids 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        bookshelfMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 书架修改
     *
     * @param bookShelf 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(BookShelf bookShelf) {
        bookshelfMapper.update(bookShelf);
        return ApiResult.success();
    }

    /**
     * 书架查询
     *
     * @param bookshelfQueryDto 查询参数
     * @return Result<List < BookShelf>>
     */
    @Override
    public Result<List<BookShelf>> query(BookshelfQueryDto bookshelfQueryDto) {
        List<BookShelf> noticeList = bookshelfMapper.query(bookshelfQueryDto);
        Integer totalCount = bookshelfMapper.queryCount(bookshelfQueryDto);
        return PageResult.success(noticeList, totalCount);
    }


}
