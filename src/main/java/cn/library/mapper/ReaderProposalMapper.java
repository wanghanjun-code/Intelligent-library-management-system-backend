package cn.library.mapper;

import cn.library.pojo.dto.query.extend.ReaderProposalQueryDto;
import cn.library.pojo.entity.ReaderProposal;
import cn.library.pojo.vo.ReaderProposalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 读者建议持久化接口
 */
@Mapper
public interface ReaderProposalMapper {

    void save(ReaderProposal readerProposal);

    void update(ReaderProposal readerProposal);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<ReaderProposalVO> query(ReaderProposalQueryDto readerProposalQueryDto);

    Integer queryCount(ReaderProposalQueryDto readerProposalQueryDto);

}
