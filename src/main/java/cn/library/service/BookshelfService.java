package cn.library.service;

import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookshelfQueryDto;
import cn.library.pojo.entity.BookShelf;

import java.util.List;

/**
 * 书架业务逻辑接口
 */
public interface BookshelfService {

    Result<Void> save(BookShelf bookShelf);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(BookShelf bookShelf);

    Result<List<BookShelf>> query(BookshelfQueryDto bookshelfQueryDto);

}
