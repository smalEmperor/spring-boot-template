package com.easicare.device.service;

import com.easicare.device.entity.KabaMedicalOperation;

import java.util.List;

/**
 * @author df
 * @date 2019/8/19
 */
public interface KabaMedicalOperationService {

    /**
     * 根据id获取原始数据
     * @param id  原始数据id
     */
    KabaMedicalOperation getMedicalOperationById(Long id);

    /**
     * 更具条件获取数据
     * @param kabaMedicalOperation
     */
    List<KabaMedicalOperation> getMedicalOperationBySelect(KabaMedicalOperation kabaMedicalOperation);

}
