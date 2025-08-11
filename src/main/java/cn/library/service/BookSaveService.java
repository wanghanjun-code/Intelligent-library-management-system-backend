package cn.library.service;

import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookSaveQueryDto;
import cn.library.pojo.entity.BookSave;
import cn.library.pojo.vo.BookSaveVO;

import java.util.List;

/**
 * 书籍收藏业务逻辑接口
 */
public interface BookSaveService {

    Result<Void> save(BookSave bookSave);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(BookSave bookSave);

    Result<List<BookSaveVO>> query(BookSaveQueryDto bookSaveQueryDto);

}
