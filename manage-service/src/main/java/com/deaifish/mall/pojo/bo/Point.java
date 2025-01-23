package com.deaifish.mall.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 坐标点
 *
 * @author DEAiFISH
 * @date 2025/1/23 20:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point<T,P> {
    private List<T> x;
    private List<P> y;
}