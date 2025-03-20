package com.deaifish.mall.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.deaifish.mall.mapper.ProductESMapper;
import com.deaifish.mall.pojo.dto.ProductESDTO;
import com.deaifish.mall.pojo.po.ProductESPO;
import com.deaifish.mall.pojo.qo.ProductESQO;
import com.deaifish.mall.pojo.vo.ProductESVO;
import com.deaifish.mall.service.ESService;
import jakarta.annotation.Resource;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.dromara.easyes.core.kernel.Wrapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/7 14:09
 */
@Service
public class ESServiceImpl implements ESService {

    @Resource
    private ProductESMapper productESMapper;

    @Override
    public void saveProduct(ProductESDTO productDTO) {
        ProductESPO bean = BeanUtil.toBean(productDTO, ProductESPO.class);
        productESMapper.insert(bean);
    }

    @Override
    public void saveProductBatch(List<ProductESDTO> productDTOList) {
        List<ProductESPO> espos = productDTOList.stream().map(productDTO -> BeanUtil.toBean(productDTO, ProductESPO.class)).toList();
        productESMapper.insertBatch(espos);
    }

    @Override
    public void deleteProductById(long pId) {
        productESMapper.deleteById(pId);
    }

    @Override
    public void deleteProductByNumber(List<Long> ids) {
        productESMapper.deleteBatchIds(ids);
    }

    @Override
    public List<ProductESVO> searchProduct(ProductESQO qo, Pageable page) {
        List<ProductESPO> espos = productESMapper.selectList(createWrapper(qo));

        return espos.stream().map(productESPO -> BeanUtil.toBean(productESPO, ProductESVO.class)).toList();
    }

    private Wrapper<ProductESPO> createWrapper(ProductESQO qo) {
        LambdaEsQueryWrapper<ProductESPO> wrapper = new LambdaEsQueryWrapper<>();

        if(qo != null) {
            if (StrUtil.isNotBlank(qo.getName())) {
                wrapper.like(ProductESPO::getName, qo.getName());
            }
            if (StrUtil.isNotBlank(qo.getClassifyName())) {
                wrapper.like(ProductESPO::getClassifyName, qo.getClassifyName());
            }
            if (StrUtil.isNotBlank(qo.getBrandName())) {
                wrapper.like(ProductESPO::getBrandName, qo.getBrandName());
            }
            if (qo.getPriceMin() != null
                    && qo.getPriceMax() != null) {
                wrapper.between(ProductESPO::getPrice, qo.getPriceMin(), qo.getPriceMax());
            }
            if (StrUtil.isNotBlank(qo.getBriefDescription())) {
                wrapper.like(ProductESPO::getBriefDescription, qo.getBriefDescription());
            }
        }

        return wrapper;
    }
}