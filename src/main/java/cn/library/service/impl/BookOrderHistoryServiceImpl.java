package cn.library.service.impl;

import cn.library.context.LocalThreadHolder;
import cn.library.mapper.BookMapper;
import cn.library.mapper.BookOrderHistoryMapper;
import cn.library.pojo.api.ApiResult;
import cn.library.pojo.api.PageResult;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookOrderHistoryQueryDto;
import cn.library.pojo.dto.query.extend.BookQueryDto;
import cn.library.pojo.entity.Book;
import cn.library.pojo.entity.BookOrderHistory;
import cn.library.pojo.vo.BookOrderHistoryVO;
import cn.library.pojo.vo.BookVO;
import cn.library.service.BookOrderHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 书籍预约历史业务逻辑实现
 */
@Service
public class BookOrderHistoryServiceImpl implements BookOrderHistoryService {

    @Resource
    private BookOrderHistoryMapper bookOrderHistoryMapper;
    @Resource
    private BookMapper bookMapper;

    /**
     * 书籍预约历史新增（借书）
     *
     * @param bookOrderHistory 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(BookOrderHistory bookOrderHistory) {
        // 是不是已经借了这本书
        List<BookOrderHistoryVO> bookOrderHistoryVOS = bookOrderHistoryMapper.queryUserBookUnreturned(LocalThreadHolder.getUserId(), bookOrderHistory.getBookId());
        for (BookOrderHistoryVO bookOrderHistoryVO : bookOrderHistoryVOS) {
            if (!bookOrderHistoryVO.getIsReturn()) {
                return ApiResult.error("This book has not been returned yet.");
            }
        }
        // 借的书，是不是有足够的数量
        BookQueryDto bookQueryDto = new BookQueryDto();
        bookQueryDto.setId(bookOrderHistory.getBookId());
        List<BookVO> bookVOS = bookMapper.query(bookQueryDto);
        if (bookVOS.isEmpty()) {
            return ApiResult.error("Could not find the book information");
        }
        BookVO bookVO = bookVOS.get(0);
        if (bookVO.getNum() - bookOrderHistory.getDeadlineNum() < 0) {
            return ApiResult.error("Insufficient quantity");
        }
        Book book = new Book();
        book.setId(bookVO.getId());
        book.setNum(bookVO.getNum() - bookOrderHistory.getDeadlineNum());
        // 更新书籍库存
        bookMapper.update(book);
        // 设置用户ID
        bookOrderHistory.setUserId(LocalThreadHolder.getUserId());
        // 未归还
        bookOrderHistory.setIsReturn(false);
        bookOrderHistory.setCreateTime(LocalDateTime.now());
        bookOrderHistoryMapper.save(bookOrderHistory);
        return ApiResult.success();
    }

    /**
     * 书籍预约历史删除
     *
     * @param ids 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        bookOrderHistoryMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 书籍预约历史修改（还书）
     *
     * @param bookOrderHistory 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(BookOrderHistory bookOrderHistory) {
        BookOrderHistoryQueryDto queryDto = new BookOrderHistoryQueryDto();
        queryDto.setId(bookOrderHistory.getId());
        // 不能依赖前端传进来的参数，以数据库存储的为准
        List<BookOrderHistoryVO> bookOrderHistoryVOS = bookOrderHistoryMapper.query(queryDto);
        // 将书籍的库存加回去
        BookQueryDto bookQueryDto = new BookQueryDto();
        bookQueryDto.setId(bookOrderHistory.getBookId());
        List<BookVO> bookVOS = bookMapper.query(bookQueryDto);
        BookVO bookVO = bookVOS.get(0);
        Book book = new Book();
        book.setId(bookOrderHistoryVOS.get(0).getBookId());
        book.setNum(bookVO.getNum() + bookOrderHistoryVOS.get(0).getDeadlineNum());
        bookMapper.update(book);
        // 确认归还
        bookOrderHistory.setIsReturn(true);
        bookOrderHistoryMapper.update(bookOrderHistory);
        return ApiResult.success();
    }

    /**
     * 书籍预约历史查询
     *
     * @param dto 查询参数
     * @return Result<List < BookOrderHistoryVO>>
     */
    @Override
    public Result<List<BookOrderHistoryVO>> query(BookOrderHistoryQueryDto dto) {
        // 只用name字段查询，无需处理bookId
        List<BookOrderHistoryVO> noticeList = bookOrderHistoryMapper.query(dto);
        Integer totalCount = bookOrderHistoryMapper.queryCount(dto);
        return PageResult.success(noticeList, totalCount);
    }


}
