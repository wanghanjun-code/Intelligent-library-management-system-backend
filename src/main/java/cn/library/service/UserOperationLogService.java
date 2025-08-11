package cn.library.service;

import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.UserOperationLogQueryDto;
import cn.library.pojo.entity.UserOperationLog;
import cn.library.pojo.vo.UserOperationLogVO;

import java.util.List;

/**
 * 用户行为日志的业务逻辑接口
 */
public interface UserOperationLogService {

    Result<Void> save(UserOperationLog userOperationLog);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(UserOperationLog userOperationLog);

    Result<List<UserOperationLogVO>> query(UserOperationLogQueryDto dto);

}
