package cn.library.service;

import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookOrderHistoryQueryDto;
import cn.library.pojo.entity.BookOrderHistory;
import cn.library.pojo.vo.BookOrderHistoryVO;

import java.util.List;

/**
 * 书籍预约历史业务逻辑接口
 */
public interface BookOrderHistoryService {

    Result<Void> save(BookOrderHistory BookOrderHistory);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(BookOrderHistory BookOrderHistory);

    Result<List<BookOrderHistoryVO>> query(BookOrderHistoryQueryDto dto);

}
