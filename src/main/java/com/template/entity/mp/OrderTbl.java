package com.template.entity.mp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dufa
 * @since 2021-12-22 16:55:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class OrderTbl implements Serializable {
    private static final long serialVersionUID = 759964446128226465L;
    
    @TableId(type = IdType.AUTO)
    private Integer id;

        
    private String userId;

        
    private String commodityCode;

        
    private Integer count;

        
    private Integer money;

}
