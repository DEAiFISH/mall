package com.deaifish.mall.mall.fallback;

import com.deaifish.mall.mall.api.UserServiceApi;
import com.deaifish.mall.mall.pojo.dto.UserBrowseHistoryDTO;
import com.deaifish.mall.mall.pojo.vo.UserBrowseHistoryVO;
import com.deaifish.mall.mall.pojo.vo.UserInterestVO;
import com.deaifish.mall.response.R;
import com.deaifish.mall.response.ResponseEnum;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/30 21:01
 */
@Component
public class UserServiceFallBack implements UserServiceApi {

    @Override
    public R<List<UserInterestVO>> interestList(Long userID) {
        return R.fail(ResponseEnum.DATA_ERROR,null);
    }

    @Override
    public R<UserBrowseHistoryVO> historyAdd(UserBrowseHistoryDTO userBrowseHistoryDTO) {
        return R.fail(ResponseEnum.DATA_ERROR,null);
    }

    @Override
    public R<Boolean> interestUpdate(List<Integer> ids, Long userID, Integer number) {
        return R.fail(ResponseEnum.DATA_ERROR,null);
    }
}
