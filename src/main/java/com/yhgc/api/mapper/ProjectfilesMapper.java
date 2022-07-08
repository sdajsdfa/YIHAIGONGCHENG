package com.yhgc.api.mapper;

import com.yhgc.api.entity.Projectfiles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2022-07-06
 */
public interface ProjectfilesMapper extends BaseMapper<Projectfiles> {
    List<Projectfiles> queryProjectFileList(Integer unitId);

}
