package cn.library.mapper;

import cn.library.pojo.dto.query.extend.UserOperationLogQueryDto;
import cn.library.pojo.entity.UserOperationLog;
import cn.library.pojo.vo.UserOperationLogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户行为日志持久化接口
 */
@Mapper
public interface UserOperationLogMapper {

    void save(@Param(value = "list") List<UserOperationLog> list);

    void update(UserOperationLog userOperationLog);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<UserOperationLogVO> query(UserOperationLogQueryDto dto);

    Integer queryCount(UserOperationLogQueryDto dto);

}
