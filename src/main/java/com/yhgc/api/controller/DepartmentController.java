package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Department;
import com.yhgc.api.entity.Unitinfo;
import com.yhgc.api.service.DepartmentService;
import com.yhgc.api.service.UnitinfoService;
import com.yhgc.api.vo.RestResult;
import com.yhgc.api.vo.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "部门")
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private ResultGenerator generator;

    @Resource
    private UnitinfoService unitinfoService;

    /**
     *查询部门
     * @return
     */
    @ApiOperation("查询部门")
    @GetMapping(value = "/queryAllSectorInfo")
    public RestResult queryDpts(Integer unitId) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitId",unitId);
        List<Department> department = departmentService.list(queryWrapper);
        return generator.getSuccessResult(department);
    }

    /**
     *  修改单位信息
     * @param unitInfo
     *
     * @return
     */
    @ApiOperation("修改单位信息")
    @PostMapping(value = "/updateUnitInfo")
    public RestResult updateUnitInfo(@RequestBody Unitinfo unitInfo) {
        if (unitInfo == null) {
            generator.getFailResult("单位信息不能为空");
        }
        unitInfo.setCreateTime(new Date());
        QueryWrapper<Unitinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", unitInfo.getId());
        Boolean si = unitinfoService.update(unitInfo,queryWrapper);
        if (si != true) {
            return generator.getFailResult("修改失败");
        }
        return generator.getSuccessResult(unitInfo);
    }

    /**
     *  添加部门
     * @param department
     * @return
     */
    @ApiOperation("添加部门")
    @PostMapping(value = "/addDpt")
    public RestResult addDpt(@RequestBody Department department) {
        if (department == null) {
            generator.getFailResult("部门不能为空");
        }
        department.setCreateTime(new Date());
        department.setStatus(0);
        Boolean si = departmentService.save(department);
        if (si != true) {
            return generator.getFailResult("添加失败");
        }
        return generator.getSuccessResult(department);
    }

    /**
     *  修改部门信息
     * @param department
     * @return
     */
    @ApiOperation("修改部门信息")
    @PostMapping(value = "/updateDpt")
    public RestResult updateDpt(@RequestBody Department department) {
        if (department == null) {
            generator.getFailResult("部门不能为空");
        }
        department.setCreateTime(new Date());
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", department.getId());
        Boolean dt = departmentService.update(department,queryWrapper);
        if (dt != true) {
            return generator.getFailResult("修改失败");
        }
        return generator.getSuccessResult(department);
    }

    /**
     * 删除部门
     * @param department
     * @return
     */
    @ApiOperation("删除部门")
    @PostMapping(value = "/deleteDpt")
    public RestResult deleteDpt(@RequestBody Department department) {
        department.setStatus(2);
        //将实体对象进行包装，包装为操作条件
        Boolean ui =  departmentService.updateById(department);
        if (ui != true) {
            return generator.getFailResult("删除部门失败");
        }
        return generator.getSuccessResult();
    }
}

