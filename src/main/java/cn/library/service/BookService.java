package cn.library.service;

import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookQueryDto;
import cn.library.pojo.entity.Book;
import cn.library.pojo.vo.BookVO;
import cn.library.pojo.vo.ChartVO;

import java.util.List;

/**
 * 书籍业务逻辑接口
 */
public interface BookService {

    Result<Void> save(Book book);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(Book book);

    Result<List<BookVO>> query(BookQueryDto bookQueryDto);

    Result<List<ChartVO>> daysQuery(Integer day);

}
