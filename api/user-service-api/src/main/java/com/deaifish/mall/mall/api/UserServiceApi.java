package com.deaifish.mall.mall.api;

import com.deaifish.mall.mall.fallback.UserServiceFallBack;
import com.deaifish.mall.mall.pojo.vo.UserInterestVO;
import com.deaifish.mall.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @description 产品服务接口
 *
 * @author DEAiFISH
 * @date 2024/12/30 20:59
 */
@FeignClient(value = "user-service", path = "/user-service/v1", fallback = UserServiceFallBack.class)
public interface UserServiceApi {


    /**
     * 用户兴趣度列表
     * @param userID
     * @return
     */
    @GetMapping("/interest/list/{uId}")
    public R<List<UserInterestVO>> interestList(@PathVariable("uId") Long userID) ;

}
