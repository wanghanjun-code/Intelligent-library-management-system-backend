package cn.library.service.impl;

import cn.library.context.LocalThreadHolder;
import cn.library.mapper.BookSaveMapper;
import cn.library.pojo.api.ApiResult;
import cn.library.pojo.api.PageResult;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookSaveQueryDto;
import cn.library.pojo.entity.BookSave;
import cn.library.pojo.vo.BookSaveVO;
import cn.library.service.BookSaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍收藏业务逻辑实现
 */
@Service
public class BookSaveServiceImpl implements BookSaveService {

    @Resource
    private BookSaveMapper bookSaveMapper;

    /**
     * 书籍收藏新增
     *
     * @param bookSave 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(BookSave bookSave) {
        // 如果这个用户收藏了这本书，就不能去继续收藏了
        BookSaveQueryDto bookSaveQueryDto = new BookSaveQueryDto();
        bookSaveQueryDto.setUserId(LocalThreadHolder.getUserId());
        bookSaveQueryDto.setBookId(bookSave.getBookId());
        Integer count = bookSaveMapper.queryCount(bookSaveQueryDto);
        if (count != 0) {
            return ApiResult.error("This book has already been favorited, please do not repeat the operation.");
        }
        bookSave.setUserId(LocalThreadHolder.getUserId());
        bookSaveMapper.save(bookSave);
        return ApiResult.success();
    }

    /**
     * 书籍收藏删除
     *
     * @param ids 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        bookSaveMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 书籍收藏修改
     *
     * @param bookSave 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(BookSave bookSave) {
        bookSaveMapper.update(bookSave);
        return ApiResult.success();
    }

    /**
     * 书籍收藏查询
     *
     * @param bookSaveQueryDto 查询参数
     * @return Result<List < BookSaveVO>>
     */
    @Override
    public Result<List<BookSaveVO>> query(BookSaveQueryDto bookSaveQueryDto) {
        List<BookSaveVO> noticeList = bookSaveMapper.query(bookSaveQueryDto);
        Integer totalCount = bookSaveMapper.queryCount(bookSaveQueryDto);
        return PageResult.success(noticeList, totalCount);
    }


}
