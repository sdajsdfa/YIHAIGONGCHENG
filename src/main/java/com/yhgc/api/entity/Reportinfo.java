package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 报告模板
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Reportinfo对象", description="报告模板")
public class Reportinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "模板名称")
    @TableField("templateName")
    private String templateName;

    @ApiModelProperty(value = "所属行业id")
    @TableField("sectorId")
    private Integer sectorId;

    @ApiModelProperty(value = "所属检测方法id")
    @TableField("methodId")
    private Integer methodId;

    @ApiModelProperty(value = "模板分类;0：单桩模板  1：工程模板")
    @TableField("templateType")
    private Integer templateType;

    @ApiModelProperty(value = "模板内容")
    private String template;

    @ApiModelProperty(value = "模板状态;（暂无）")
    private Integer status;

    @ApiModelProperty(value = "使用次数")
    private Integer count;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
