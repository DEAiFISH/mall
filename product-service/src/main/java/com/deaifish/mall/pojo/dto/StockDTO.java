package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.jpa.pojo.po.BasePO;
import com.deaifish.mall.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
 * @date 2024/12/12 14:51
 */
@Data
@Builder
public class StockDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 库存ID
     */
    @Schema(description = "库存ID")
    @NotNull(message = "库存ID不能为空", groups = {UpdateGroup.class})
    private Long stockId;

    /**
     * 商品ID
     */
    @Schema(description = "商品ID")
    @NotNull(message = "商品ID不能为空")
    private Long productId;

    /**
     * 库存量
     */
    @Schema(description = "库存量")
    @NotNull(message = "库存量不能为空")
    private Integer amount;

    /**
     * 警报库存量
     */
    @Schema(description = "警报库存量")
    @NotNull(message = "警报库存量不能为空")
    private Integer warningAmount;
}
