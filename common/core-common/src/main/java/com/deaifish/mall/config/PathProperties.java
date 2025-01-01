package com.deaifish.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description 路径配置文件类
 *
 * @author DEAiFISH
 * @date 2023/11/24 00:56
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss.path")
public class PathProperties {
    // 用户默认头像名称
    public String userDefaultPhotoName;
    // 用户头像文件夹路径
    public String userHeadTiltsDirPath;
    // 商品图片文件夹路径
    public String productDirPath;
    // 评价图片文件夹路径
    public String evaluationDirPath;
    // 品牌图片文件夹路径
    public String brandDirPath;
    // 分类图片文件夹路径
    public String classifyDirPath;
}
