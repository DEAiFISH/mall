package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.jpa.pojo.po.BasePO;
import com.deaifish.mall.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:52
 */
@Data
@Builder
public class ProductLabelDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品标签表ID
     */
    @Schema(description = "商品标签表ID")
    @NotNull(message = "商品标签表ID", groups = UpdateGroup.class)
    private Long productLabelId;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    @NotNull(message = "商品ID")
    private Long productId;

    /**
     * 标签ID
     */
    @Schema(description = "标签ID")
    @NotNull(message = "标签ID")
    private Integer labelId;
}