package com.deaifish.mall.fallback;

import com.deaifish.mall.api.SearchServiceApi;
import com.deaifish.mall.pojo.dto.ProductESDTO;
import com.deaifish.mall.pojo.qo.ProductESQO;
import com.deaifish.mall.pojo.vo.ProductESVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.response.ResponseEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/30 21:01
 */
@Component
public class SearchServiceFallBack implements SearchServiceApi {
    @Override
    public String test() {
        return "错误";
    }

    @Override
    public R<Boolean> saveProduct(ProductESDTO productESDTO) {
        return R.fail(ResponseEnum.DATA_ERROR, false);
    }

    @Override
    public R<Boolean> saveProductBatch(List<ProductESDTO> esdtos) {
        return R.fail(ResponseEnum.DATA_ERROR, false);
    }

    @Override
    public R<Boolean> deleteProductById(long pId) {
        return R.fail(ResponseEnum.DATA_ERROR, false);
    }

    @Override
    public R<Boolean> deleteProductByNumber(List<Long> ids) {
        return R.fail(ResponseEnum.DATA_ERROR, false);
    }

    @Override
    public R<List<ProductESVO>> searchProduct(ProductESQO qo, Pageable page) {
        return R.fail(ResponseEnum.DATA_ERROR, List.of());
    }
}
