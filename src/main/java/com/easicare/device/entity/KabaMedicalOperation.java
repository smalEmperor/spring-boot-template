package com.easicare.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作记录表bean
 * @author df
 * @date 2019/6/25
 */
@Getter
@Setter
@TableName("kaba_medical_operation")
public class KabaMedicalOperation implements Serializable {
    @TableId(type= IdType.AUTO)
    private Long id;
    private String code;
    private Byte operateType;
    @NotBlank(message = "operator")
    private String operator;
    @NotBlank(message = "operateTable")
    private String operateTable;
    private String operateRecord;
    private String operateTime;
    private LocalDateTime createTime;
    private LocalDateTime lastTime;
    private Byte active;
}
