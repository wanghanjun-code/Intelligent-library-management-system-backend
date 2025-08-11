package cn.library.pojo.dto.query.extend;

import cn.library.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询DTO参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDto extends QueryDto {
    /**
     * 用户的帐号
     */
    private String userAccount;
    /**
     * 用户的名称
     */
    private String userName;
    /**
     * 用户的邮箱
     */
    private String userEmail;
    /**
     * 用户的角色
     */
    private Boolean role;
    /**
     * 是否可以登录
     */
    private Boolean isLogin;

    private Boolean isWord;
}
