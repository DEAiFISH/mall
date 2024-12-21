package com.deaifish.mall.entity.po;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @description TODO
 *
 * @author DEAiFISH
 * @date 2024/12/12 14:44
 */

@Entity
@Table(name = "product",schema = "mall_db")
@Comment("商品表")
public class ProductPO {

    /**
     * 商品ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", columnDefinition = "BIGINT COMMENT '商品ID'")
    private Long productId;

    /**
     * 商品编码
     */
    @Column(name = "number", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '商品编码'")
    private String number;

    /**
     * 商品名称
     */
    @Column(name = "name", nullable = false, length = 32, columnDefinition = "VARCHAR(32) COMMENT '商品名称'")
    private String name;

    /**
     * 商品分类ID
     */
    @Column(name = "classify_id", nullable = false, columnDefinition = "INT COMMENT '商品分类ID'")
    private Integer classifyId;

    /**
     * 商品品牌ID
     */
    @Column(name = "brand_id", nullable = false, columnDefinition = "INT COMMENT '商品品牌ID'")
    private Integer brandId;

    /**
     * 价格
     */
    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE COMMENT '价格'")
    private Double price;

    /**
     * 优惠价格
     */
    @Column(name = "preferential_price", columnDefinition = "DOUBLE COMMENT '优惠价格'")
    private Double preferentialPrice;

    /**
     * 特有规格参数（JSON）
     */
    @Column(name = "parameter", nullable = false, columnDefinition = "JSON COMMENT '特有规格参数（JSON格式：{属性：参数}）'")
    private String parameter;

    /**
     * 销量
     */
    @Column(name = "sale", nullable = false, columnDefinition = "INT DEFAULT 0 COMMENT '销量'")
    private Integer sale;

    /**
     * 简述
     */
    @Column(name = "brief_description", nullable = false, length = 64, columnDefinition = "VARCHAR(64) COMMENT '简述'")
    private String briefDescription;

    /**
     * 详细描述
     */
    @Column(name = "description", length = 512, columnDefinition = "VARCHAR(512) COMMENT '详细描述'")
    private String description;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT DEFAULT 1 COMMENT '状态（1: 上架, 0: 下架）'")
    private Byte status;

    /**
     * 封面图路径
     */
    @Column(name = "cover_picture", nullable = false, length = 256, columnDefinition = "VARCHAR(256) COMMENT '封面图路径'")
    private String coverPicture;

    /**
     * 详细图路径（，分割）
     */
    @Column(name = "details_picture", nullable = false, length = 256, columnDefinition = "VARCHAR(256) COMMENT '详细图路径（以逗号分割）'")
    private String detailsPicture;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'")
    private LocalDateTime updateTime;

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPreferentialPrice() {
        return preferentialPrice;
    }

    public void setPreferentialPrice(Double preferentialPrice) {
        this.preferentialPrice = preferentialPrice;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public String getDetailsPicture() {
        return detailsPicture;
    }

    public void setDetailsPicture(String detailsPicture) {
        this.detailsPicture = detailsPicture;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
