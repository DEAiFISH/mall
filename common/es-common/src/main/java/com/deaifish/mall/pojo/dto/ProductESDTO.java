package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dromara.easyes.annotation.IndexField;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/28 14:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductESDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    @NotNull(message = "商品ID不能为空", groups = {UpdateGroup.class})
    private Long productId;

    /**
     * 商品编码
     */
    @Schema(description = "商品编码")
    @NotBlank(message = "商品编码不能为空")
    @Length(max = 32, message = "商品编码长度不能超过{max}个字符")
    private String number;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    @Length(max = 128, message = "商品名称长度不能超过{max}个字符")
    private String name;

    /**
     * 商品分类ID
     */
    @Schema(description = "商品分类ID")
    @NotNull(message = "商品分类ID不能为空")
    private Integer classifyId;

    /**
     * 商品分类名称
     */
    @Schema(description = "商品分类名称")
    @NotNull(message = "商品分类名称不能为空")
    private String classifyName;

    /**
     * 商品品牌ID
     */
    @Schema(description = "商品品牌ID")
    @NotNull(message = "商品品牌ID不能为空")
    private Integer brandId;

    /**
     * 商品品牌名称
     */
    @Schema(description = "商品品牌名称")
    @NotNull(message = "商品品牌名称不能为空")
    private String brandName;

    /**
     * 价格
     */
    @Schema(description = "价格")
    @NotNull(message = "价格不能为空")
    @Min(value = 0, message = "价格不能小于{min}")
    private Double price;

    /**
     * 优惠价格
     */
    @Schema(description = "优惠价格")
    @Min(value = 0, message = "价格不能小于{min}")
    private Double preferentialPrice;

    /**
     * 简述
     */
    @Schema(description = "简述")
    @NotBlank(message = "简述不能为空")
    @Length(max = 512, message = "简述长度不能超过{max}个字符")
    private String briefDescription;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "状态不能为空")
    private Byte status;

    /**
     * 封面图路径
     */
    @Schema(description = "封面图路径")
    @NotBlank(message = "封面图路径不能为空")
    @Length(max = 256, message = "封面图路径长度不能超过{max}个字符")
    private String coverPicture;

    /**
     * 标签
     */
    @Schema(description = "标签")
    @NotBlank(message = "标签")
    @Length(max = 256, message = "标签不能超过{max}个字符")
    private String label;
}
