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
 * 用户信息
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Userinfo对象", description="用户信息")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位id")
    @TableField("unitId")
    private Integer unitId;

    @ApiModelProperty(value = "单位名称")
    @TableField("unitName")
    private String unitName;

    @ApiModelProperty(value = "部门id")
    @TableField("dptId")
    private Integer dptId;

    @ApiModelProperty(value = "部门名称")
    @TableField("dptName")
    private String dptName;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "身份证号")
    @TableField("idCard")
    private String idCard;

    @ApiModelProperty(value = "手机号")
    @TableField("phoneNum")
    private String phoneNum;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "权限")
    private String rights;

    @ApiModelProperty(value = "账户状态;0：正常 1：停用 2：删除")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
