package cn.library.mapper;

import cn.library.pojo.dto.query.extend.UserQueryDto;
import cn.library.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户持久化接口
 */
public interface UserMapper {


    /**
     * 用户信息新增
     *
     */
    int insert(User userInsert);

    /**
     * 分页查询用户信息
     *
     */
    List<User> query(UserQueryDto userQueryDto);

    /**
     * 查询满足分页查询的记录总数
     *
     */
    int queryCount(UserQueryDto userQueryDto);

    /**
     * 更新用户信息
     *
     */
    int update(User user);

    /**
     * 批量删除用户信息
     *
     */
    void batchDelete(@Param(value = "ids") List<Integer> ids);

    /**
     * 根据不为空的查询信息查找用户
     *
     */
    User getByActive(User user);

}
