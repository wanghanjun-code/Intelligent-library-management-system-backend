package cn.library.service.impl;

import cn.library.mapper.BookMapper;
import cn.library.mapper.BookOrderHistoryMapper;
import cn.library.mapper.NoticeMapper;
import cn.library.mapper.UserMapper;
import cn.library.pojo.api.ApiResult;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.BookOrderHistoryQueryDto;
import cn.library.pojo.dto.query.extend.BookQueryDto;
import cn.library.pojo.dto.query.extend.NoticeQueryDto;
import cn.library.pojo.dto.query.extend.UserQueryDto;
import cn.library.pojo.vo.ChartVO;
import cn.library.service.ViewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页可视化
 */
@Service
public class ViewsServiceImpl implements ViewsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookOrderHistoryMapper bookOrderHistoryMapper;

    /**
     * 统计一些系统的基础数据
     *
     * @return Result<List < ChartVO>>
     */
    @Override
    public Result<List<ChartVO>> staticControls() {
        List<ChartVO> chartVOS = new ArrayList<>();
        // 1. 用户数
        UserQueryDto userQueryDto = new UserQueryDto();
        int userCount = userMapper.queryCount(userQueryDto);
        change(userCount, "存量用户（个）", chartVOS);
        // 2. 公告
        NoticeQueryDto noticeQueryDto = new NoticeQueryDto();
        int noticeCount = noticeMapper.queryCount(noticeQueryDto);
        change(noticeCount, "公告（篇）", chartVOS);
        // 3. 收录图书
        BookQueryDto bookQueryDto = new BookQueryDto();
        int bookCount = bookMapper.queryCount(bookQueryDto);
        change(bookCount, "收录图书", chartVOS);
        // 4. 借阅记录
        BookOrderHistoryQueryDto bookOrderHistoryQueryDto = new BookOrderHistoryQueryDto();
        int bookOrderHistoryCount = bookOrderHistoryMapper.queryCount(bookOrderHistoryQueryDto);
        change(bookOrderHistoryCount, "借阅记录", chartVOS);
        return ApiResult.success(chartVOS);
    }

    /**
     * 参数处理
     *
     * @param count    总数目
     * @param name     统计项
     * @param chartVOS 装它们的集合
     */
    private void change(Integer count, String name, List<ChartVO> chartVOS) {
        ChartVO chartVO = new ChartVO(name, count);
        chartVOS.add(chartVO);
    }


}
