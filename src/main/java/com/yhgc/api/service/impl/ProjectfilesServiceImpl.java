package com.yhgc.api.service.impl;

import com.yhgc.api.entity.Projectfiles;
import com.yhgc.api.mapper.ProjectfilesMapper;
import com.yhgc.api.service.ProjectfilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-07-06
 */
@Service
public class ProjectfilesServiceImpl extends ServiceImpl<ProjectfilesMapper, Projectfiles> implements ProjectfilesService {
    @Resource
    private ProjectfilesMapper projectfilesMapper;

    @Override
    public List<Projectfiles> queryProjectFileList(Integer unitId) {
        return projectfilesMapper.queryProjectFileList(unitId);
    }
}
