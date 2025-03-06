package com.deaifish.mall.fallback;

import com.deaifish.mall.api.ProductServiceApi;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.response.ResponseEnum;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/30 21:01
 */
@Component
public class ProductServiceFallBack implements ProductServiceApi {

    @Override
    public R<ProductVO> getProductById(Long pId) {
        return R.fail(ResponseEnum.DATA_ERROR, null);
    }

    @Override
    public R<Boolean> reduceStock(Integer num, Long pId) {
        return R.fail(ResponseEnum.DATA_ERROR, false);
    }

    @Override
    public R<List<LabelVO>> listByProductId(Long pId) {
        return R.fail(ResponseEnum.DATA_ERROR, null);
    }
}
