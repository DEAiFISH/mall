package com.deaifish.mall.pojo.bo;

import java.util.Map;

/**
 * @description 物品类，封装了物品的id、bean对象和特征向量
 *
 * @author DEAiFISH
 * @date 2025/2/19 16:43
 */
public class Item {
    public Long id;
    Map<String, Long> features; // 物品的特征向量

    public Item(Long id, Map<String, Long> features) {
        this.id = id;
        this.features = features;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Long> getFeatures() {
        return features;
    }

    public void setFeatures(Map<String, Long> features) {
        this.features = features;
    }
}