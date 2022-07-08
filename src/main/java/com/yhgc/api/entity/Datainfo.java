package com.yhgc.api.entity;

import java.math.BigDecimal;
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
 * 数据
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Datainfo对象", description="数据")
public class Datainfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据id")
    @TableId(value = "dataId", type = IdType.AUTO)
    private Long dataId;

    @ApiModelProperty(value = "单位id")
    @TableField("unitId")
    private Integer unitId;

    @ApiModelProperty(value = "工程id")
    @TableField("projectId")
    private Integer projectId;

    @ApiModelProperty(value = "流水号")
    @TableField("serialNo")
    private String serialNo;

    @ApiModelProperty(value = "文件名")
    @TableField("fileName")
    private String fileName;

    @ApiModelProperty(value = "桩号")
    @TableField("pileNo")
    private String pileNo;

    @ApiModelProperty(value = "孔号")
    @TableField("holeNo")
    private String holeNo;

    @ApiModelProperty(value = "桩长")
    @TableField("pileLength")
    private Integer pileLength;

    @ApiModelProperty(value = "孔深")
    @TableField("holeDepth")
    private Integer holeDepth;

    @ApiModelProperty(value = "砼强度")
    @TableField("concreteStrength")
    private String concreteStrength;

    @ApiModelProperty(value = "桩径")
    private String diameter;

    @ApiModelProperty(value = "定位有效性")
    @TableField("gpsValid")
    private Integer gpsValid;

    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "仪器编号")
    @TableField("machineId")
    private String machineId;

    @ApiModelProperty(value = "检测时间")
    @TableField("testTime")
    private Date testTime;

    @ApiModelProperty(value = "原始文件路径")
    @TableField("orginFilePath")
    private String orginFilePath;

    @ApiModelProperty(value = "原始文件更新时间")
    @TableField("orginFileTime")
    private Date orginFileTime;

    @ApiModelProperty(value = "分析文件路径")
    @TableField("analyFilePath")
    private String analyFilePath;

    @ApiModelProperty(value = "分析文件更新时间")
    @TableField("analyFileTime")
    private Date analyFileTime;

    @ApiModelProperty(value = "是否打印")
    @TableField("printStatus")
    private Integer printStatus;

    @ApiModelProperty(value = "数据状态;0：正常   1：作废")
    @TableField("dataStatus")
    private Integer dataStatus;

    @ApiModelProperty(value = "备注")
    private String remark;


}
