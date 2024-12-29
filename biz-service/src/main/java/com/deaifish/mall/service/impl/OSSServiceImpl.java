package com.deaifish.mall.service.impl;

import com.deaifish.mall.exception.MallException;
import com.deaifish.mall.service.OSSService;
import com.deaifish.mall.util.MallImgUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @description OSSService的实现类，用于处理文件上传和删除操作。
 *
 * @author DEAiFISH
 * @date 2024/12/29 16:03
 */
@Service
public class OSSServiceImpl implements OSSService {
    @Resource
    private MallImgUtil mallImgUtil;

    @Override
    public List<String> upload(String dir, List<MultipartFile> files) {
        List<CompletableFuture<String>> futures = files.stream().map(file ->
                CompletableFuture.supplyAsync(() -> {
                    try {
                        // 使用UUID生成唯一的文件名，避免冲突
                        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
                        return mallImgUtil.uploadImg(file.getInputStream(), fileName, dir);
                    } catch (IOException e) {
                        throw new MallException("文件上传失败", e); // 使用自定义的异常类型
                    }
                })
        ).toList();
        // 等待所有异步任务完成，并收集结果
        CompletableFuture<List<String>> completableFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join) // 获取每个Future的结果
                        .toList());// 转换为列表

        // 创建结果列表，用于存储上传后的图片URL
        List<String> urls;
        try {
            urls = completableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new MallException("文件上传失败", e);
        }
        return urls;
    }


    @Override
    public void delete(String url) {
        mallImgUtil.delImg(url);
    }
}
