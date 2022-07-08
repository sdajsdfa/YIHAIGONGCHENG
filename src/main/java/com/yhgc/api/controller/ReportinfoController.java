package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Reportinfo;
import com.yhgc.api.service.ReportinfoService;
import com.yhgc.api.vo.RestResult;
import com.yhgc.api.vo.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 报告模板 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "报告模板")
@RestController
@RequestMapping("/reportinfo")
public class ReportinfoController {

    @Resource
    private ReportinfoService reportinfoService;

    @Resource
    private ResultGenerator generator;

    /**
     *  上传报告模板
     * @param reportinfo
     * @return
     */
    @ApiOperation("上传报告模板")
    @PostMapping(value = "/uploadReportTemplate")
    public RestResult uploadReportTemplate(@RequestBody Reportinfo reportinfo) {
        if(reportinfo.getTemplateType()==0 && reportinfo.getTemplateType()==1 ) {
            if (reportinfo == null) {
                generator.getFailResult("报告模板不能为空");
            }
            reportinfo.setCreateTime(new Date());
            Boolean ri = reportinfoService.save(reportinfo);
            if (ri != true) {
                return generator.getFailResult("添加失败");
            }
            return generator.getSuccessResult(reportinfo);
        }else {
            return generator.getFailResult("报告模板不能上传");
        }
    }

    /**
     * 下载报告模板
     * @param id
     * @return
     */
    @ApiOperation("下载报告模板")
    @GetMapping(value = "/downloadReportTemplate")
    public RestResult downloadReportTemplate(Integer id){
        QueryWrapper<Reportinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Reportinfo reportinfo = reportinfoService.getOne(queryWrapper);
        return generator.getSuccessResult(reportinfo);
    }

    /**
     * 更新报告模板使用次数
     * @param id
     * @param countAdd
     * @return
     */
    @ApiOperation("更新报告模板使用次数")
    @PostMapping(value = "/uploadReportTemplateCount")
    public RestResult uploadReportTemplateCount(Integer id,Integer countAdd) {
        Reportinfo reportinfo = reportinfoService.getById(id);
        if(reportinfo==null){
            return generator.getFailResult("报告模板不能为空");
        }
        reportinfo.setCount(countAdd);
        Boolean ri = reportinfoService.updateById(reportinfo);
        if (ri != true) {
            return generator.getFailResult("修改失败");
        }
        return generator.getSuccessResult(reportinfo);
    }

}

