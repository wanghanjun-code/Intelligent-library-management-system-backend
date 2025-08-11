package cn.library.controller;

import cn.library.aop.Log;
import cn.library.aop.Pager;
import cn.library.context.LocalThreadHolder;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.ReaderProposalQueryDto;
import cn.library.pojo.entity.ReaderProposal;
import cn.library.pojo.vo.ReaderProposalVO;
import cn.library.service.ReaderProposalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 读者建议的 Controller
 */
@RestController
@RequestMapping(value = "/readerProposal")
public class ReaderProposalController {

    @Resource
    private ReaderProposalService readerProposalService;

    /**
     * 读者建议新增
     *
     */
    @PostMapping(value = "/save")
    @Log(content = "向系统提交建议")
    public Result<Void> save(@RequestBody ReaderProposal readerProposal) {
        return readerProposalService.save(readerProposal);
    }

    /**
     * 读者建议删除
     *
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return readerProposalService.batchDelete(ids);
    }

    /**
     * 读者建议修改
     *
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody ReaderProposal readerProposal) {
        return readerProposalService.update(readerProposal);
    }

    /**
     * 读者建议查询
     *
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<ReaderProposalVO>> query(@RequestBody ReaderProposalQueryDto readerProposalQueryDto) {
        return readerProposalService.query(readerProposalQueryDto);
    }

    /**
     * 查询用户自己发布的读者建议
     *
     */
    @Pager
    @PostMapping(value = "/queryUser")
    public Result<List<ReaderProposalVO>> queryUser(@RequestBody ReaderProposalQueryDto readerProposalQueryDto) {
        readerProposalQueryDto.setUserId(LocalThreadHolder.getUserId());
        return readerProposalService.query(readerProposalQueryDto);
    }

}
