package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.jpa.pojo.po.BasePO;
import com.deaifish.mall.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:47
 */
@Data
@Builder
public class BrandDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品品牌ID
     */
    @Schema(description = "商品品牌ID")
    @NotNull(message = "品牌ID不能为空", groups = {UpdateGroup.class})
    private Integer brandId;

    /**
     * 品牌编码
     */
    @Schema(description = "品牌编码")
    @NotBlank(message = "品牌编码不能为空")
    @Length(max = 32, message = "品牌编码长度不能超过{max}")
    private String number;

    /**
     * 品牌名称
     */
    @Schema(description = "品牌名称")
    @NotBlank(message = "品牌名称不能为空")
    @Length(max = 32, message = "品牌名称长度不能超过{max}")
    private String name;

    /**
     * 图标路径
     */
    @Schema(description = "图标路径")
    @NotBlank(message = "图标路径不能为空")
    @Length(max = 256, message = "图标路径长度不能超过{max}")
    private String icon;

    /**
     * 专区大图路径
     */
    @Schema(description = "专区大图路径")
    @NotBlank(message = "专区大图路径不能为空")
    @Length(max = 256, message = "专区大图路径长度不能超过{max}")
    private String picture;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @NotBlank(message = "描述不能为空")
    @Length(max = 512, message = "描述长度不能超过{max}")
    private String description;
}
