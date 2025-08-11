package cn.library.pojo.dto.query.extend;

import cn.library.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookshelfQueryDto extends QueryDto {
    private String floor;
}
