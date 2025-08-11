package cn.library.pojo.vo;

import cn.library.pojo.entity.UserOperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserOperationLogVO extends UserOperationLog {
    private String userName;
}
