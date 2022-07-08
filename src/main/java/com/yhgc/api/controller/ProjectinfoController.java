package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Projectinfo;
import com.yhgc.api.mapper.ProjectinfoMapper;
import com.yhgc.api.service.ProjectfilesService;
import com.yhgc.api.service.ProjectinfoService;
import com.yhgc.api.vo.RestResult;
import com.yhgc.api.vo.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 工程信息 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "工程信息")
@RestController
@RequestMapping("/projectinfo")
public class ProjectinfoController {

    @Resource
    private ProjectinfoService projectinfoService;

    @Resource
    private ProjectfilesService projectfilesService;


    @Resource
    private ResultGenerator generator;

    /**
     * 查询工程详细信息
     * @param id
     * @return
     */
    @ApiOperation("查询工程详细信息")
    @GetMapping(value = "/queryProjectInfo")
    public RestResult queryProjectInfo(Long id) {
        Projectinfo projectinfo = projectinfoService.getById(id);
        return generator.getSuccessResult(projectinfo);
    }

    /**
     * 查询工程及文件列表
     * @return
     */
    @ApiOperation("查询工程及文件列表")
    @GetMapping(value = "/queryProjectFileList")
    public RestResult queryProjectFileList(Integer unitId) {
        return generator.getSuccessResult(projectfilesService.queryProjectFileList(unitId));
    }

    /**
     *  修改工程信息
     * @param projectinfo
     * @return
     */
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody Projectinfo projectinfo) {
        if (projectinfo == null) {
            generator.getFailResult("工程信息不能为空");
        }
        projectinfo.setCreateTime(new Date());
        projectinfo.setDeclareTime(new Date());
        QueryWrapper<Projectinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", projectinfo.getId());
        queryWrapper.eq("status", 0);
        Boolean p = projectinfoService.update(projectinfo,queryWrapper);
        if (p != true) {
            return generator.getFailResult("修改失败");
        }
        return generator.getSuccessResult();
    }

    /**
     * 删除工程信息
     * @param projectinfo
     * @return
     */
    @ApiOperation("删除工程信息")
    @PostMapping (value = "/deleteProject")
    public RestResult deleteProject(@RequestBody Projectinfo projectinfo) {
        projectinfo.setStatus(2);
        //将实体对象进行包装，包装为操作条件
        Boolean pi =  projectinfoService.updateById(projectinfo);
        if (pi != true) {
            return generator.getFailResult("删除工程信息失败");
        }
        return generator.getSuccessResult();
    }

    /**
     * 申报工程
     * @param projectinfo
     * @return
     */
    @ApiOperation("申报工程")
    @PostMapping("/declareCheckProject")
    public RestResult declareCheckProject(@RequestBody Projectinfo projectinfo) {
        projectinfo.setDeclareTime(new Date());
        projectinfo.setStatus(0);
        Boolean ui = projectinfoService.save(projectinfo);
        if (ui != true) {
            return generator.getFailResult("添加失败");
        }
        return generator.getSuccessResult();
    }
}

