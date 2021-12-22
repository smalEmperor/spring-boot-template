package com.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dufa
 * @since 2021-12-22 16:59:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("stock")
public class StockTbl implements Serializable {
    private static final long serialVersionUID = 962862291412889649L;

    @TableId(type = IdType.AUTO)
    private Integer id;

        
    private String commodityCode;

        
    private Integer count;

}
