package com.deaifish.mall.api;

import com.deaifish.mall.fallback.ProductServiceFallBack;
import com.deaifish.mall.pojo.dto.StockDTO;
import com.deaifish.mall.pojo.vo.StockVO;
import com.deaifish.mall.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description 产品服务接口
 *
 * @author DEAiFISH
 * @date 2024/12/30 20:59
 */
@FeignClient(value = "product-service", fallback = ProductServiceFallBack.class)
public interface ProductServiceApi {
    @GetMapping("/stock/v1/get/{pId}")
    public R<StockVO> getStockByProductId(@PathVariable("pId") Long pId);

    @PutMapping("/stock/v1/update")
    public R<StockVO> updateStock(@RequestBody StockDTO stockDTO);
}
