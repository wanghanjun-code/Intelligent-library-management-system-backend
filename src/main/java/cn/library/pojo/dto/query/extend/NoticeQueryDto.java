package cn.library.pojo.dto.query.extend;

import cn.library.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NoticeQueryDto extends QueryDto {
    private String name;
    private String content;
}
