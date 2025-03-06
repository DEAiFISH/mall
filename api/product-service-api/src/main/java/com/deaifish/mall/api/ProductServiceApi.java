package com.deaifish.mall.api;

import com.deaifish.mall.fallback.ProductServiceFallBack;
import com.deaifish.mall.pojo.vo.LabelVO;
import com.deaifish.mall.pojo.vo.ProductVO;
import com.deaifish.mall.response.R;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description 产品服务接口
 *
 * @author DEAiFISH
 * @date 2024/12/30 20:59
 */
@FeignClient(value = "product-service", path = "/product-service/v1", fallback = ProductServiceFallBack.class)
public interface ProductServiceApi {

    /**
     * 根据商品id查询商品详情
     * @param pId
     * @return
     */
    @GetMapping("/product/get/{pId}")
    public R<ProductVO> getProductById(@PathVariable("pId") Long pId);

    /**
     * 减少库存
     * @param num
     * @return
     */
    @PutMapping("/stock/reduce")
    public R<Boolean> reduceStock(@RequestParam("num") @NotNull(message = "数量不能为空") Integer num,
                                  @RequestParam("pId") @NotNull(message = "商品id不能为空") Long pId) ;

    /**
     * 根据商品id查询标签列表
     * @param pId
     * @return
     */
    @GetMapping("/list/product/{pId}")
    public R<List<LabelVO>> listByProductId(@PathVariable("pId") Long pId);
}
