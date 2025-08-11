package cn.library.service.impl;

import cn.library.context.LocalThreadHolder;
import cn.library.mapper.ReaderProposalMapper;
import cn.library.pojo.api.ApiResult;
import cn.library.pojo.api.PageResult;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.ReaderProposalQueryDto;
import cn.library.pojo.entity.ReaderProposal;
import cn.library.pojo.vo.ReaderProposalVO;
import cn.library.service.ReaderProposalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 读者建议业务逻辑实现
 */
@Service
public class ReaderProposalServiceImpl implements ReaderProposalService {

    @Resource
    private ReaderProposalMapper readerProposalMapper;

    /**
     * 读者建议新增
     *
     * @param readerProposal 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(ReaderProposal readerProposal) {
        readerProposal.setUserId(LocalThreadHolder.getUserId());
        readerProposal.setIsReply(false);
        readerProposal.setCreateTime(LocalDateTime.now());
        readerProposalMapper.save(readerProposal);
        return ApiResult.success();
    }

    /**
     * 读者建议删除
     *
     * @param ids 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        readerProposalMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 读者建议修改
     *
     * @param readerProposal 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(ReaderProposal readerProposal) {
        // 证明管理员回复
        if (readerProposal.getReplyContent() != null) {
            readerProposal.setReplyTime(LocalDateTime.now());
        }
        readerProposal.setIsReply(true);
        readerProposalMapper.update(readerProposal);
        return ApiResult.success();
    }

    /**
     * 读者建议查询
     *
     * @param readerProposalQueryDto 查询参数
     * @return Result<List < ReadingProposalVO>>
     */
    @Override
    public Result<List<ReaderProposalVO>> query(ReaderProposalQueryDto readerProposalQueryDto) {

        List<ReaderProposalVO> noticeList = readerProposalMapper.query(readerProposalQueryDto);
        Integer totalCount = readerProposalMapper.queryCount(readerProposalQueryDto);
        return PageResult.success(noticeList, totalCount);
    }


}
