package com.deaifish.mall.api;

import com.deaifish.mall.fallback.BIZServiceApiFallBack;
import com.deaifish.mall.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/30 20:59
 */
@FeignClient(value = "biz-service", path = "/biz/v1", fallback = BIZServiceApiFallBack.class)
public interface BIZServiceApi {

    /**
     * 上传文件
     * @param dir 目录
     * @param files 文件
     * @return R<List < String>> 文件路径
     */
    @PostMapping("/oss/upload")
    R<List<String>> upload(@RequestParam("dir") String dir,
                           @RequestParam("files") List<MultipartFile> files);

    /**
     * 删除文件
     * @param url 文件路径
     * @return R<Boolean> 删除成功
     */
    @DeleteMapping("/oss/delete")
    R<Boolean> delete(@RequestParam("url") String url);
}
