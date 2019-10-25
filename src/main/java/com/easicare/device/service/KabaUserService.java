package com.easicare.device.service;

import com.easicare.device.entity.KabaUser;

/**
 * @author df
 * @date 2019/8/19
 */
public interface KabaUserService {

    /**
     * 根据id获取处理过的数据
     * @param id 处理过的数据id
     */
    KabaUser getUser(Long id);
}
