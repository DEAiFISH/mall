package com.deaifish.mall.fallback;

import com.deaifish.mall.api.ProductServiceApi;
import com.deaifish.mall.pojo.dto.StockDTO;
import com.deaifish.mall.pojo.vo.StockVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.response.ResponseEnum;
import org.springframework.stereotype.Component;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/30 21:01
 */
@Component
public class ProductServiceFallBack implements ProductServiceApi {

    @Override
    public R<StockVO> getStockByProductId(Long pId) {
        return R.fail(ResponseEnum.DATA_ERROR,null);
    }

    @Override
    public R<StockVO> updateStock(StockDTO stockDTO) {
        return R.fail(ResponseEnum.DATA_ERROR,null);
    }
}
