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
 * 单位信息
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Unitinfo对象", description="单位信息")
public class Unitinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "单位名称")
    @TableField("unitName")
    private String unitName;

    @ApiModelProperty(value = "单位地址")
    @TableField("unitAddress")
    private String unitAddress;

    @ApiModelProperty(value = "单位电话")
    @TableField("unitTel")
    private String unitTel;

    @ApiModelProperty(value = "负责人")
    @TableField("bossName")
    private String bossName;

    @ApiModelProperty(value = "负责人电话")
    @TableField("bossTel")
    private String bossTel;

    @ApiModelProperty(value = "单位状态;0：正常   1：停用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
