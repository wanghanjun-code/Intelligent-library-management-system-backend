package cn.library.mapper;

import cn.library.pojo.dto.query.extend.BookOrderHistoryQueryDto;
import cn.library.pojo.entity.BookOrderHistory;
import cn.library.pojo.vo.BookOrderHistoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 书籍预约历史持久化接口
 */
@Mapper
public interface BookOrderHistoryMapper {

    void save(BookOrderHistory bookOrderHistory);

    void update(BookOrderHistory bookOrderHistory);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<BookOrderHistoryVO> query(BookOrderHistoryQueryDto dto);

    Integer queryCount(BookOrderHistoryQueryDto dto);

    List<BookOrderHistoryVO> queryUserBookUnreturned(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
}
