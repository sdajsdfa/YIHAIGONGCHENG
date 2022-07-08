package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Department;
import com.yhgc.api.entity.Unitinfo;
import com.yhgc.api.entity.Userinfo;
import com.yhgc.api.enums.Type;
import com.yhgc.api.service.UserinfoService;
import com.yhgc.api.vo.RestResult;
import com.yhgc.api.vo.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-20
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {

    @Resource
    private UserinfoService userinfoService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ResultGenerator generator;

    /**
     * 登录接口
     *
     * @param account
     * @param password
     * @return
     */
    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    public RestResult login(String account, String password) {
        if (account == null || password == null) {
            return generator.getFailResult("输入的用户名或密码不能为空");
        }
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        queryWrapper.eq("status", 0);
        Userinfo userinfo = userinfoService.getOne(queryWrapper);
        if (userinfo == null) {
            return generator.getFailResult("找不到用户信息");
        }
//        String at = userinfo.getAccount();
        String md5Password = getMd5(password, account);
        if (!userinfo.getPassword().equals(md5Password)) {
            return generator.getFailResult("密码不正确");
        }
        Userinfo info = new Userinfo();
        info.setId(userinfo.getId());
        info.setAccount(userinfo.getAccount());
        redisTemplate.opsForValue().set("userinfo", info);
        return generator.getSuccessResult(info);

    }

    //md5加密规则
    private String getMd5(String password, String account) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((account + password + account).getBytes()).toUpperCase();
        }
        return password;
    }

    /**
     * 注册接口(添加人员)
     * @param userinfo
     * @return
     * @throws Exception
     */
    @ApiOperation("添加人员")
    @PostMapping(value = "/addUser")
    public RestResult addUser(@RequestBody Userinfo userinfo) throws Exception {
        String account = userinfo.getAccount();
        String password = userinfo.getPassword();
        if (account==null || password==null){
            return generator.getFailResult("输入的用户名或密码不能为空");
        }
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",account);
        Userinfo ui =  userinfoService.getOne(queryWrapper);
        if (ui != null){
            return generator.getFailResult("用户名已经被注册");
        }
//      String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5(password, account);
        userinfo.setPassword(md5Password);
        userinfo.setStatus(0);
        userinfo.setCreateTime(new Date());
        Boolean b = userinfoService.save(userinfo);
        if (b != true) {
            return generator.getFailResult("添加失败");
        }
        return generator.getSuccessResult(userinfo);
    }

    /**
     * 修改人员
     * @param userinfo
     * @return
     */
    @ApiOperation("修改人员")
    @PostMapping(value = "/updateUser")
    public RestResult updateUser(@RequestBody Userinfo userinfo) {
        userinfo.setCreateTime(new Date());
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userinfo.getId());
        Boolean u = userinfoService.update(userinfo,queryWrapper);
        if (u != true) {
            return generator.getFailResult("修改失败");
        }
        return generator.getSuccessResult();
    }

    /**
     * 暂停人员
     * @param userinfo
     * @return
     */
    @ApiOperation("暂停人员")
    @PostMapping(value = "/pauseUser")
    public RestResult pauseUser(@RequestBody Userinfo userinfo) {
        userinfo.setCreateTime(new Date());
        userinfo.setStatus(1);
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userinfo.getId());
        Boolean u = userinfoService.update(userinfo,queryWrapper);
        if (u != true) {
            return generator.getFailResult("修改失败");
        }
        return generator.getSuccessResult(userinfo);
    }

    /**
     * 删除人员
     * @param userinfo
     * @return
     */
    @ApiOperation("删除人员")
    @PostMapping(value = "/invalidUser")
    public RestResult invalidUser(@RequestBody Userinfo userinfo) {
        userinfo.setStatus(2);
        //将实体对象进行包装，包装为操作条件
        Boolean ui =  userinfoService.updateById(userinfo);
        if (ui != true) {
            return generator.getFailResult("删除人员失败");
        }
        return generator.getSuccessResult(userinfo);
    }

    /**
     * 查询人员（按单位）
     * @param unitId
     * @return
     */
    @ApiOperation("查询人员（按单位）")
    @GetMapping(value = "/queryUsers")
    public RestResult queryUsers(Integer unitId){
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitId", unitId);
        List<Userinfo> userinfo = userinfoService.list(queryWrapper);
        return generator.getSuccessResult(userinfo);
    }

    /**
     * 查询人员（按部门）
     * @param unitId
     * @param dptId
     * @return
     */
    @ApiOperation("查询人员（按部门）")
    @GetMapping(value = "/queryUsersByDpt")
    public RestResult queryUsersByDpt(Integer unitId,Integer dptId){
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitId", unitId);
        queryWrapper.eq("dptId", dptId);
        List<Userinfo> userinfo = userinfoService.list(queryWrapper);
        return generator.getSuccessResult(userinfo);
    }

    /**
     * 查询人员（按状态）
     * @param unitId
     * @param status
     * @return
     */
    @ApiOperation("查询人员（按状态）")
    @GetMapping(value = "/queryUsersInByStatus")
    public RestResult queryUsersInByStatus(Integer unitId,Integer status){
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitId", unitId);
        queryWrapper.eq("status", status);
        List<Userinfo> userinfo = userinfoService.list(queryWrapper);
        return generator.getSuccessResult(userinfo);
    }
}

