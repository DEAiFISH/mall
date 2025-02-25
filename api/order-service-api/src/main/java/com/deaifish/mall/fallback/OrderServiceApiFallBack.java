package com.deaifish.mall.fallback;

import com.deaifish.mall.api.OrderServiceApi;
import com.deaifish.mall.pojo.vo.OrderVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.response.ResponseEnum;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/1/15 18:17
 */
public class OrderServiceApiFallBack implements OrderServiceApi {
    @Override
    public R<OrderVO> finish(Long orderId) {
        return R.fail(ResponseEnum.DATA_ERROR,null);
    }

    @Override
    public R<OrderVO> pay(Long orderId, Byte paymentMethod) {
        return R.fail(ResponseEnum.DATA_ERROR,null);
    }
}