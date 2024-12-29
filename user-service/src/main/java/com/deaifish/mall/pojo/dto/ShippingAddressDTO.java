package com.deaifish.mall.pojo.dto;

import com.deaifish.mall.group.ADDGroup;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class ShippingAddressDTO {

    /**
     * 收货地址ID
     */
    @Schema(description = "收货地址ID")
    @NotNull(message = "收货地址ID不能为空", groups = {ADDGroup.class})
    private Long addressId;

    /**
     * 用户ID
     */
    @Schema(description = "用户id")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    /**
     * 收货人名字
     */
    @Schema(description = "收货人名字")
    @NotBlank(message = "收货人名字不能为空")
    @Length(max = 32, message = "收货人名字长度不能超过{max}个字符}")
    private String name;

    /**
     * 省
     */
    @Schema(description = "省")
    @NotBlank(message = "省不能为空")
    @Length(max = 16, message = "省长度不能超过{max}个字符}")
    private String province;

    /**
     * 市
     */
    @Schema(description = "市")
    @NotBlank(message = "市不能为空")
    @Length(max = 16, message = "市长度不能超过{max}个字符}")
    private String city;

    /**
     * 区/县
     */
    @Schema(description = "区/县")
    @NotBlank(message = "区/县不能为空")
    @Length(max = 16, message = "区/县长度不能超过{max}个字符}")
    private String area;

    /**
     * 街道
     */
    @Schema(description = "街道")
    @NotBlank(message = "街道不能为空")
    @Length(max = 32, message = "街道长度不能超过{max}个字符}")
    private String street;

    /**
     * 详细地址
     */
    @Schema(description = "详细地址")
    @NotBlank(message = "详细地址不能为空")
    @Length(max = 32, message = "详细地址长度不能超过{max}个字符}")
    private String full;

    /**
     * 电话
     */
    @Schema(description = "电话")
    @NotBlank(message = "电话不能为空")
    @Length(max = 16, message = "电话长度不能超过{max}个字符}")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "电话格式不正确")
    private String phone;
}
