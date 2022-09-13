package com.template.entity.mp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dufa
 * @since 2021-12-22 16:47:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("account")
public class AccountTbl implements Serializable {
    private static final long serialVersionUID = 100911503762114350L;

    @TableId(type = IdType.AUTO)
    private Integer id;

        
    private String userId;

        
    private Integer money;

}
