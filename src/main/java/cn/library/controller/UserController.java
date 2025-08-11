package cn.library.controller;

import cn.library.aop.Pager;
import cn.library.aop.Protector;
import cn.library.pojo.api.Result;
import cn.library.pojo.dto.query.extend.UserQueryDto;
import cn.library.pojo.dto.update.UserLoginDTO;
import cn.library.pojo.dto.update.UserRegisterDTO;
import cn.library.pojo.dto.update.UserUpdateDTO;
import cn.library.pojo.entity.User;
import cn.library.pojo.vo.ChartVO;
import cn.library.pojo.vo.UserVO;
import cn.library.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户登录
     *
     */

    @PostMapping(value = "/login")
    @ResponseBody
    public Result<Object> login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }


    /**
     * token校验
     */
    @Protector
    @GetMapping(value = "/auth")
    @ResponseBody
    public Result<UserVO> auth() {
        return userService.auth();
    }


    /**
     * 通过ID查询用户信息
     *
     */
    @Protector
    @GetMapping(value = "/getById/{id}")
    @ResponseBody
    public Result<UserVO> getById(@PathVariable Integer id) {
        return userService.getById(id);
    }


    /**
     * 用户注册
     *
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.register(userRegisterDTO);
    }

    /**
     * 后台新增用户
     *
     */
    @Protector(role = "管理员")
    @PostMapping(value = "/insert")
    @ResponseBody
    public Result<String> insert(@RequestBody UserRegisterDTO userRegisterDTO) {
        return userService.insert(userRegisterDTO);
    }

    /**
     * 用户信息修改
     *
     */
    @Protector
    @PutMapping(value = "/update")
    @ResponseBody
    public Result<String> update(@RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.update(userUpdateDTO);
    }

    /**
     * 后台用户信息修改
     *
     */
    @Protector(role = "管理员")
    @PutMapping(value = "/backUpdate")
    @ResponseBody
    public Result<String> backUpdate(@RequestBody User user) {
        return userService.backUpdate(user);
    }

    /**
     * 用户修改密码
     *
     */
    @PutMapping(value = "/updatePwd")
    @ResponseBody
    public Result<String> updatePwd(@RequestBody Map<String, String> map) {
        return userService.updatePwd(map);
    }

    /**
     * 批量删除用户信息
     */
    @Protector(role = "管理员")
    @PostMapping(value = "/batchDelete")
    @ResponseBody
    public Result<String> batchDelete(@RequestBody List<Integer> ids) {
        return userService.batchDelete(ids);
    }

    /**
     * 查询用户数据
     *
     */
    @Pager
    @Protector(role = "管理员")
    @PostMapping(value = "/query")
    @ResponseBody
    public Result<List<User>> query(@RequestBody UserQueryDto userQueryDto) {
        return userService.query(userQueryDto);
    }

    /**
     * 统计用户存量数据
     */
    @GetMapping(value = "/daysQuery/{day}")
    @ResponseBody
    public Result<List<ChartVO>> query(@PathVariable Integer day) {
        return userService.daysQuery(day);
    }

}

