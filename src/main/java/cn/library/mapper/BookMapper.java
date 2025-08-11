package cn.library.mapper;

import cn.library.pojo.dto.query.extend.BookQueryDto;
import cn.library.pojo.dto.query.extend.CategoryQueryDto;
import cn.library.pojo.entity.Book;
import cn.library.pojo.entity.Category;
import cn.library.pojo.vo.BookVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 书籍持久化接口
 */
@Mapper
public interface BookMapper {

    void save(Book book);

    void update(Book book);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<BookVO> query(BookQueryDto bookQueryDto);

    Integer queryCount(BookQueryDto bookQueryDto);

}
