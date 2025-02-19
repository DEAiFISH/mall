package com.deaifish.mall.util;

import com.deaifish.mall.pojo.bo.CBProfileBO;
import com.deaifish.mall.pojo.bo.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 基于内容的推荐算法工具类
 *
 * @author DEAiFISH
 * @date 2025/2/19 16:23
 */
public class CBUtil<T> {

    /**
     * 计算两个向量的余弦相似度
     * @param vec1 用户特征
     * @param vec2 物品特征
     * @return 相似度值
     */
    // 计算余弦相似度
    public double cosineSimilarity(Map<String, Long> vec1, Map<String, Long> vec2) {
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        // 计算点积和向量的模
        for (String key : vec1.keySet()) {
            if (vec2.containsKey(key)) {
                dotProduct += vec1.get(key) * vec2.get(key);
            }
            norm1 += Math.pow(vec1.get(key), 2);
        }

        for (String key : vec2.keySet()) {
            norm2 += Math.pow(vec2.get(key), 2);
        }

        // 计算余弦相似度
        if (norm1 == 0 || norm2 == 0) {
            return 0.0;
        }
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    /**
     * 基于内容的推荐算法
     * @param cbProfileBO 用户特征和物品列表
     * @param topN 推荐物品的数量
     * @return 推荐物品列表
     */
    public  List<T> recommend(CBProfileBO<T> cbProfileBO , int topN) {
        Map<String,Long> userProfileFeatures = cbProfileBO.getUserProfileFeatures();
        List<Item> items = cbProfileBO.getItems();
        Map<Long, T> beanMap = cbProfileBO.getBeanMap();

        // 存储物品与用户偏好的相似度
        Map<Long, Double> similarityScores = new HashMap<>();

        // 计算每个物品与用户偏好的相似度
        for (Item item : items) {
            double similarity = cosineSimilarity(userProfileFeatures, item.getFeatures());
            similarityScores.put(item.id, similarity);
        }

        // 按相似度排序
        List<Map.Entry<Long, Double>> sortedList = new ArrayList<>(similarityScores.entrySet());
        sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // 返回前 topN 个推荐物品
        List<T> recommendations = new ArrayList<>();
        for (int i = 0; i < Math.min(topN, sortedList.size()); i++) {
            recommendations.add(beanMap.get(sortedList.get(i).getKey()));
        }

        return recommendations;
    }
}