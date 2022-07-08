package com.yhgc.api.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Unitinfo;
import com.yhgc.api.entity.Userinfo;
import com.yhgc.api.service.UnitinfoService;
import com.yhgc.api.service.UserinfoService;
import com.yhgc.api.vo.RestResult;
import com.yhgc.api.vo.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * 单位信息 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "单位信息")
@RestController
@RequestMapping("/unitinfo")
public class UnitinfoController {

    @Resource
    private UnitinfoService unitinfoService;

    @Resource
    private UserinfoService userinfoService;

    @Resource
    private ResultGenerator generator;
    
    /**
     *查询所有单位
     * @return
     */
    @ApiOperation("查询所有单位")
    @GetMapping(value = "/queryAllUnitInfo")
    public RestResult queryAllUnitInfo() {
        Page<Unitinfo> page = new Page<>(1,10);
        //根据条件查询数据
        IPage<Unitinfo> iPage = unitinfoService.page(page, null);
        return generator.getSuccessResult(iPage);
    }

    /**
     *  添加单位信息
     * @param unitinfo
     * @return
     */
    @ApiOperation("添加单位信息")
    @PostMapping(value = "/saveAndUpdate")
    public RestResult addUnitInfo(@RequestBody Unitinfo unitinfo) {
        String unitName =unitinfo.getUnitName();
        if (unitinfo == null) {
            generator.getFailResult("单位信息不能为空");
        }
        QueryWrapper<Unitinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitName",unitName);
        Unitinfo u = unitinfoService.getOne(queryWrapper);
        if (u != null){
            return generator.getFailResult("单位名称已经被注册");
        }
        unitinfo.setCreateTime(new Date());
        unitinfo.setStatus(0);
        Boolean ui = unitinfoService.save(unitinfo);
        if (ui != true) {
            return generator.getFailResult("添加失败");
        }
        HashMap<String,Object> hashMap =new HashMap<>();
        hashMap.put("unitId",unitinfo.getId());
        return generator.getSuccessResult(hashMap);
    }

    /**
     * 添加单位管理员
     * @param userinfo
     * @return
     */
    @ApiOperation("添加单位管理员")
    @PostMapping(value = "/addUnitAdmin")
    public RestResult addUnitAdmin(@RequestBody Userinfo userinfo) {
        String account = userinfo.getAccount();
        String password = userinfo.getPassword();
        String idCard = userinfo.getIdCard();
        if (account==null || password==null || idCard==null) {
            generator.getFailResult("单位管理员信息不能为空");
        }
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
//      queryWrapper.eq("account",account);
        queryWrapper.eq("idCard",idCard);
        Userinfo u =  userinfoService.getOne(queryWrapper);
        if (u != null){
            return generator.getFailResult("身份证已经被注册");
        }
        userinfo.setCreateTime(new Date());
        userinfo.setStatus(0);
        Boolean ui = userinfoService.save(userinfo);
        if (ui != true) {
            return generator.getFailResult("添加失败");
        }
        return generator.getSuccessResult(userinfo);
    }

    /**
     * 删除单位信息
     * @param unitinfo
     * @return
     */
    @ApiOperation("删除单位信息")
    @PostMapping(value = "/deleteUnitInfo")
    public RestResult deleteUnitInfo(@RequestBody Unitinfo unitinfo) {
        //将实体对象进行包装，包装为操作条件
        unitinfo.setStatus(2);
        QueryWrapper<Unitinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",unitinfo.getId());
        Boolean ui =  unitinfoService.update(unitinfo,queryWrapper);
        if (ui != true) {
            return generator.getFailResult("删除单位信息失败");
        }
        QueryWrapper<Userinfo> query = new QueryWrapper<>();
        query.eq("unitId",unitinfo.getId());
        List<Userinfo> list = userinfoService.list(query);
        for (Userinfo userinfo:list) {
            userinfo.setStatus(1);
            Boolean user =  userinfoService.update(userinfo,query);
            if (user != true) {
                return generator.getFailResult("删除单位信息失败");
            }
        }
        return generator.getSuccessResult();
    }
}

