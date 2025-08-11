package cn.library.service;

import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.NoticeQueryDto;
import cn.library.pojo.entity.Notice;

import java.util.List;

/**
 * 公告业务逻辑接口
 */
public interface NoticeService {

    Result<Void> save(Notice notice);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(Notice notice);

    Result<List<Notice>> query(NoticeQueryDto noticeQueryDto);

}
