package cn.library.pojo.vo;

import cn.library.pojo.entity.ReaderProposal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReaderProposalVO extends ReaderProposal {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userAvatar;
}
