package com.easicare.device.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 操作记录表bean
 * @author df
 * @date 2019/6/25
 */
@Getter
@Setter
@TableName("kaba_medical_operation")
public class KabaMedicalOperation extends BaseEntity implements Serializable {

    private String code;
    private Byte operateType;
    @NotBlank(message = "operator")
    private String operator;
    @NotBlank(message = "operateTable")
    private String operateTable;
    private String operateRecord;
    private String operateTime;
}
