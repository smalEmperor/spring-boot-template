package com.template.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.entity.KabaMedicalOperation;
import com.template.mapper.KabaMedicalOperationMapper;
import com.template.service.KabaMedicalOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author df
 * @date 2019/8/19
 */
@Service
public class KabaMedicalOperationServiceImpl extends ServiceImpl<KabaMedicalOperationMapper, KabaMedicalOperation> implements KabaMedicalOperationService {

    private final KabaMedicalOperationMapper kabaMedicalOperationMapper;

    @Autowired
    public KabaMedicalOperationServiceImpl(KabaMedicalOperationMapper kabaMedicalOperationMapper) {
        this.kabaMedicalOperationMapper = kabaMedicalOperationMapper;
    }

    /**
     * 根据id获取原始数据
     * @param id  原始数据id
     */
    @Override
    public KabaMedicalOperation getMedicalOperationById(Long id) {
        return kabaMedicalOperationMapper.selectById(id);
    }

    /**
     * 根据条件获取数据
     * @param kabaMedicalOperation
     */
    @Override
    public List<KabaMedicalOperation> getMedicalOperationBySelect(KabaMedicalOperation kabaMedicalOperation) {
        return kabaMedicalOperationMapper.selectList(new QueryWrapper<>(kabaMedicalOperation));
    }

    /**
     * 分页
     */
    @Override
    public IPage<KabaMedicalOperation> getMedicalOperationByPage() {
        IPage<KabaMedicalOperation> page = new Page<>(1, 10);
       /* QueryWrapper<KabaMedicalOperation> wrapper = new QueryWrapper<>();
        KabaMedicalOperation student = new KabaMedicalOperation();
        student.setId(1L);
        wrapper.setEntity(student);*/
        return kabaMedicalOperationMapper.selectPage(page,null);
    }

}
