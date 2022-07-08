package com.yhgc.api.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author 易生雄
 * @since 2022-07-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Projectfiles对象", description="VIEW")
public class Projectfiles implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工程id")
    private Long id;

    @ApiModelProperty(value = "单位id")
    @TableField("unitId")
    private Long unitId;

    @ApiModelProperty(value = "工程名称")
    @TableField("projectName")
    private String projectName;

    @ApiModelProperty(value = "申报时间")
    @TableField("declareTime")
    private Date declareTime;

    @ApiModelProperty(value = "行业名称")
    @TableField("sectorName")
    private String sectorName;

    @ApiModelProperty(value = "检测方法名称")
    @TableField("testMethod")
    private String testMethod;

    @ApiModelProperty(value = "流水号")
    @TableField("serialNo")
    private String serialNo;

    @ApiModelProperty(value = "报检根数")
    @TableField("testCount")
    private Integer testCount;

    @ApiModelProperty(value = "检测人姓名")
    @TableField("accountName")
    private String accountName;

    @ApiModelProperty(value = "报告模板id")
    @TableField("reportId")
    private Integer reportId;

    @ApiModelProperty(value = "工程状态;0：审核中  1：已审核   2：作废")
    private Integer status;

    @ApiModelProperty(value = "闁轰胶澧楀畵涔甦")
    @TableField("dataId")
    private Long dataId;

    @ApiModelProperty(value = "文件名")
    @TableField("fileName")
    private String filename;

    @ApiModelProperty(value = "桩号")
    @TableField("pileNo")
    private String pileNo;

    @ApiModelProperty(value = "数据状态;0：正常   1：作废")
    @TableField("dataStatus")
    private Integer dataStatus;

    @ApiModelProperty(value = "是否打印")
    @TableField("printStatus")
    private Integer printStatus;


}
