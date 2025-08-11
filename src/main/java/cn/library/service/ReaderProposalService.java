package cn.library.service;

import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.ReaderProposalQueryDto;
import cn.library.pojo.entity.ReaderProposal;
import cn.library.pojo.vo.ReaderProposalVO;

import java.util.List;

/**
 * 读者建议的业务逻辑接口
 */
public interface ReaderProposalService {

    Result<Void> save(ReaderProposal readerProposal);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(ReaderProposal readerProposal);

    Result<List<ReaderProposalVO>> query(ReaderProposalQueryDto readerProposalQueryDto);

}
