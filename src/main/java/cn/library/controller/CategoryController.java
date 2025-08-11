package cn.library.controller;

import cn.library.aop.Pager;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.CategoryQueryDto;
import cn.library.pojo.entity.Category;
import cn.library.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 书籍类别的 Controller
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 书籍类别新增
     *
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    /**
     * 书籍类别删除
     *
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return categoryService.batchDelete(ids);
    }

    /**
     * 书籍类别修改
     *
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody Category category) {
        return categoryService.update(category);
    }

    /**
     * 书籍类别查询
     *
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<Category>> query(@RequestBody CategoryQueryDto categoryQueryDto) {
        return categoryService.query(categoryQueryDto);
    }

}
