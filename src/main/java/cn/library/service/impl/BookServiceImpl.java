package cn.library.service.impl;

import cn.library.context.LocalThreadHolder;
import cn.library.mapper.BookMapper;
import cn.library.pojo.api.ApiResult;
import cn.library.pojo.api.PageResult;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.base.QueryDto;
import cn.library.pojo.dto.query.extend.BookQueryDto;
import cn.library.pojo.entity.Book;
import cn.library.pojo.vo.BookVO;
import cn.library.pojo.vo.ChartVO;
import cn.library.service.BookService;
import cn.library.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 书籍业务逻辑实现
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    /**
     * 书籍新增
     *
     */
    @Override
    public Result<Void> save(Book book) {
        if (book.getIsPlanBuy() == null) {
            book.setIsPlanBuy(false);
        }
        book.setCreateTime(LocalDateTime.now());
        bookMapper.save(book);
        return ApiResult.success();
    }

    /**
     * 书籍删除
     *
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        bookMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 书籍修改
     *
     */
    @Override
    public Result<Void> update(Book book) {
        bookMapper.update(book);
        return ApiResult.success();
    }

    /**
     * 书籍查询
     *
     */
    @Override
    public Result<List<BookVO>> query(BookQueryDto bookQueryDto) {
        bookQueryDto.setUserId(LocalThreadHolder.getUserId());
        List<BookVO> noticeList = bookMapper.query(bookQueryDto);
        Integer totalCount = bookMapper.queryCount(bookQueryDto);
        return PageResult.success(noticeList, totalCount);
    }

    /**
     * 统计指定时间里面的图书上架情况
     *
     */
    @Override
    public Result<List<ChartVO>> daysQuery(Integer day) {
        QueryDto queryDto = DateUtil.startAndEndTime(day);
        BookQueryDto bookQueryDto = new BookQueryDto();
        bookQueryDto.setStartTime(queryDto.getStartTime());
        bookQueryDto.setEndTime(queryDto.getEndTime());
        List<BookVO> bookVOS = bookMapper.query(bookQueryDto);
        List<LocalDateTime> localDateTimes = bookVOS.stream().map(BookVO::getCreateTime).collect(java.util.stream.Collectors.toList());
        List<ChartVO> chartVOS = DateUtil.countDatesWithinRange(day, localDateTimes);
        return ApiResult.success(chartVOS);
    }
}
