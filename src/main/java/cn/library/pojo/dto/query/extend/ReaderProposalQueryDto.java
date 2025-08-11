package cn.library.pojo.dto.query.extend;

import cn.library.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReaderProposalQueryDto extends QueryDto {
    private String content;
    private Integer userId;
    private Boolean isPublish;
    private Boolean isReply;
}
