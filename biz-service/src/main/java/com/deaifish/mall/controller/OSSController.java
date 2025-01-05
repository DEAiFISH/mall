package com.deaifish.mall.controller;

import com.deaifish.mall.response.R;
import com.deaifish.mall.service.OSSService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description 文件服务
 *
 * @author DEAiFISH
 * @date 2024/12/29 16:02
 */
@RestController
@Tag(name = "文件服务")
@Validated
@RequestMapping("/biz/v1")
public class OSSController {
    @Resource
    private OSSService ossService;

    /**
     * 上传文件
     * @param dir 目录
     * @param files 文件
     * @return R<List < String>> 文件路径
     */
    @PostMapping("/oss/upload")
    public R<List<String>> upload(@RequestParam("dir") String dir,
                                  @RequestParam("files") List<MultipartFile> files) {
        return R.success("查询成功", ossService.upload(dir, files));
    }

    /**
     * 删除文件
     * @param url 文件路径
     * @return R<Boolean> 删除成功
     */
    @DeleteMapping("/oss/delete")
    public R<Boolean> delete(@RequestParam("url") String url) {
        ossService.delete(url);
        return R.success("删除成功", true);
    }
}
