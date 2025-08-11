package cn.library.pojo.dto.query.extend;

import cn.library.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 书籍收藏拓展查询类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BookSaveQueryDto extends QueryDto {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 书籍ID
     */
    private Integer bookId;
    private String name;
}
