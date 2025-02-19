package com.deaifish.mall.pojo.bo;

import java.util.List;
import java.util.Map;

/**
 * @description : 用户画像信息与商品信息的封装类
 *
 * @author DEAiFISH
 * @date 2025/2/19 16:30
 */
public class CBProfileBO<T> {
    Map<String, Long> userProfileFeatures;
    List<Item> items;
    Map<Long,T> beanMap;

    public CBProfileBO(Map<String, Long> userProfileFeatures, List<Item> items, Map<Long,T> beanMap) {
        this.userProfileFeatures = userProfileFeatures;
        this.items = items;
        this.beanMap = beanMap;
    }

    public Map<String, Long> getUserProfileFeatures() {
        return userProfileFeatures;
    }

    public void setUserProfileFeatures(Map<String, Long> userProfileFeatures) {
        this.userProfileFeatures = userProfileFeatures;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Map<Long, T> getBeanMap() {
        return beanMap;
    }

    public void setBeanMap(Map<Long, T> beanMap) {
        this.beanMap = beanMap;
    }
}