package com.deaifish.mall.service.imppl;


import com.deaifish.mall.api.OrderServiceApi;
import com.deaifish.mall.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2025/2/25 16:09
 */
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final OrderServiceApi orderServiceApi;


    @Override
    public void pay(Long orderId) {
        orderServiceApi.pay(orderId, (byte) 1);
    }
}