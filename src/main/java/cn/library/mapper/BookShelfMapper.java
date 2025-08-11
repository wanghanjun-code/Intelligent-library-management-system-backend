package cn.library.mapper;

import cn.library.pojo.dto.query.extend.BookshelfQueryDto;
import cn.library.pojo.entity.BookShelf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 书架持久化接口
 */
@Mapper
public interface BookShelfMapper {

    void save(BookShelf bookShelf);

    void update(BookShelf bookShelf);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<BookShelf> query(BookshelfQueryDto bookshelfQueryDto);

    Integer queryCount(BookshelfQueryDto bookshelfQueryDto);

}
