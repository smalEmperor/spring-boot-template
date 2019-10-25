package com.easicare.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easicare.device.entity.validgroup.UserEditValidGroup;
import com.easicare.device.entity.validgroup.UserLoginValidGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@TableName("kaba_user")
public class KabaUser implements Serializable {
    @TableId(type= IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Long id;
    private String code;
    private Long roleId;
    private Integer isFamilyadmin;
    private String userName;
    private String userNameInitial;
    private String userNickname;
    private String userRealname;
    @NotBlank(message = "userPassword", groups = { UserLoginValidGroup.class, UserEditValidGroup.class })
    private String userPassword;
    @NotBlank(message = "userEmail", groups = { UserLoginValidGroup.class, UserEditValidGroup.class })
    private String userEmail;
    @NotBlank(message = "userPhone", groups = { UserLoginValidGroup.class, UserEditValidGroup.class })
    private String userPhone;
    private String userIdcard;
    private Long userBankcard;
    private String userFacedata;
    private String userFacedataProcess;
    private Long userLevel;
    private Long userScore;
    private String userIcon;
    private Byte userGender;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate userBirthday;
    private Integer userEducation;
    private Float userWeight;
    private Float userTall;
    private Long loginCount;
    private Byte useStatus;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime lastloginTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime lastTime;

    private Byte active;
}