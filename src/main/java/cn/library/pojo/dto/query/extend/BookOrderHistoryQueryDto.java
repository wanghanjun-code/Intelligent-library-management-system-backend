package cn.library.pojo.dto.query.extend;

import cn.library.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookOrderHistoryQueryDto extends QueryDto {
    private Integer userId;
    private Boolean isReturn;
    private String name;
}
