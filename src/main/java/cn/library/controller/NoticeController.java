package cn.library.controller;

import cn.library.aop.Pager;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.NoticeQueryDto;
import cn.library.pojo.entity.Notice;
import cn.library.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告的 Controller
 */
@RestController
@RequestMapping(value = "/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    /**
     * 公告新增
     *
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody Notice notice) {
        return noticeService.save(notice);
    }

    /**
     * 公告删除
     *
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return noticeService.batchDelete(ids);
    }

    /**
     * 公告修改
     *
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody Notice notice) {
        return noticeService.update(notice);
    }

    /**
     * 公告查询
     *
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<Notice>> query(@RequestBody NoticeQueryDto noticeQueryDto) {
        return noticeService.query(noticeQueryDto);
    }

}
