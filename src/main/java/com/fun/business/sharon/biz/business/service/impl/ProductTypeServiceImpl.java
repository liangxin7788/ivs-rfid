package com.fun.business.sharon.biz.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.business.sharon.biz.business.bean.ProductType;
import com.fun.business.sharon.biz.business.dao.ProductTypeMapper;
import com.fun.business.sharon.biz.business.service.ProductTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangxin
 * @since 2019-12-12
 */
@Service
@Slf4j
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements ProductTypeService {

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getTypeList() {
        return productTypeMapper.selectList(null);
    }

    @Override
    public Integer deleteType(Integer typeId) {
        log.info("删除产品类型 -> {}" + JSON.toJSONString(productTypeMapper.selectById(typeId)));
        return productTypeMapper.deleteById(typeId);
    }
}
