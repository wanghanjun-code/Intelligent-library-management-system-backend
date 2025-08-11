package cn.library.mapper;

import cn.library.pojo.dto.query.extend.BookSaveQueryDto;
import cn.library.pojo.entity.BookSave;
import cn.library.pojo.vo.BookSaveVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 书籍收藏持久化接口
 */
@Mapper
public interface BookSaveMapper {

    void save(BookSave bookSave);

    void update(BookSave bookSave);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<BookSaveVO> query(BookSaveQueryDto bookSaveQueryDto);

    Integer queryCount(BookSaveQueryDto bookSaveQueryDto);

}
