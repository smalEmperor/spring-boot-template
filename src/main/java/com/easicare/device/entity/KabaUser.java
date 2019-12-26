package com.easicare.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easicare.device.entity.validgroup.UserEditValidGroup;
import com.easicare.device.entity.validgroup.UserLoginValidGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("kaba_user")
public class KabaUser implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableId(type= IdType.AUTO)
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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime lastTime;

    /**
     * 0：无效；1：有效
     */
    @TableLogic
    private Integer active;
}