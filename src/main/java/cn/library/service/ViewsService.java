package cn.library.service;

import cn.library.pojo.api.Result;
import cn.library.pojo.vo.ChartVO;

import java.util.List;

public interface ViewsService {

    Result<List<ChartVO>> staticControls();

}
