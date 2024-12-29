package com.deaifish.mall.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @description OSS服务接口
 *
 * @author DEAiFISH
 * @date 2024/12/29 16:03
 */
public interface OSSService {
    /**
     * 上传文件
     * @param dir 目录
     * @param files 文件
     * @return List<String> 文件访问地址
     */
    List<String> upload(String dir, List<MultipartFile> files) throws ExecutionException, InterruptedException;

    /**
     * 删除文件
     * @param url 文件访问地址
     */
    void delete(String url);
}
