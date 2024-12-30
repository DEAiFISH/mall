package com.deaifish.mall.fallback;

import com.deaifish.mall.api.BIZServiceApi;
import com.deaifish.mall.response.R;
import com.deaifish.mall.response.ResponseEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/30 21:01
 */
@Component
public class BIZServiceApiFallBack implements BIZServiceApi {
    @Override
    public R<List<String>> upload(String dir, List<MultipartFile> files) {
        return R.fail(ResponseEnum.DATA_ERROR, List.of("服务异常，请稍后再试"));
    }

    @Override
    public R<Boolean> delete(String url) {
        return R.fail(ResponseEnum.DATA_ERROR, false);
    }
}
