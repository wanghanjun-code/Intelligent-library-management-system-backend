package cn.library.pojo.vo;

import cn.library.pojo.entity.BookOrderHistory;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookOrderHistoryVO extends BookOrderHistory {
    private String userName;
    private String bookName;
    private String bookCover;
}
