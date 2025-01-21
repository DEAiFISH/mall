package com.deaifish.mall.pojo.qo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/21 18:46
 */
@Data
public class BrandQO {
    /**
     * 品牌编号
     */
    @Schema(description = "品牌编号")
    private String number;

    /**
     * 品牌名称
     */
    @Schema(description = "品牌名称")
    private String name;

}