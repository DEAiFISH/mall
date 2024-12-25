package com.deaifish.mall.entity.vo;

import com.deaifish.mall.validation.group.ADDGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @description 收货地址
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddressVO {

    /**
     * 收货地址ID
     */
    @Schema(description = "收货地址ID")
    private Long addressId;

    /**
     * 用户ID
     */
    @Schema(description = "用户id")
    private Long userId;

    /**
     * 收货人名字
     */
    @Schema(description = "收货人名字")
    private String name;

    /**
     * 省
     */
    @Schema(description = "省")
    private String province;

    /**
     * 市
     */
    @Schema(description = "市")
    private String city;

    /**
     * 区/县
     */
    @Schema(description = "区/县")
    private String area;

    /**
     * 街道
     */
    @Schema(description = "街道")
    private String street;

    /**
     * 详细地址
     */
    @Schema(description = "详细地址")
    private String full;

    /**
     * 电话
     */
    @Schema(description = "电话")
    private String phone;
}
