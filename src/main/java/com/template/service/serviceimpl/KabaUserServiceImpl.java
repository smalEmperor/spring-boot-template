package com.template.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.entity.KabaUser;
import com.template.mapper.KabaUserMapper;
import com.template.service.KabaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author df
 * @date 2019/8/19
 */
@Service
public class KabaUserServiceImpl extends ServiceImpl<KabaUserMapper, KabaUser> implements KabaUserService {

    private final KabaUserMapper kabaUserMapper;

    @Autowired
    public KabaUserServiceImpl(KabaUserMapper kabaUserMapper) {
        this.kabaUserMapper = kabaUserMapper;
    }

    /**
     * 根据id获取处理过的数据
     * @param id 处理过的数据id
     */
    @Override
    @Cacheable(value = "userCache",key = "#id")
    public KabaUser getUser(Long id) {
        return kabaUserMapper.selectById(id);
    }
}
