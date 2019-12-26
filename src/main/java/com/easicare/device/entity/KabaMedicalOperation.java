package com.easicare.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作记录表bean
 * @author df
 * @date 2019/6/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("kaba_medical_operation")
public class KabaMedicalOperation implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String code;

    private Byte operateType;

    @NotBlank(message = "operator")
    private String operator;

    @NotBlank(message = "operateTable")
    private String operateTable;

    private String operateRecord;

    private String operateTime;
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
