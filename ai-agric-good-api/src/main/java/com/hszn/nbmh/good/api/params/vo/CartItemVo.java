package com.hszn.nbmh.good.api.params.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author：袁德民
 * @Description:　购物项数据
 * @Date:下午7:10 22/9/2
 * @Modified By:
 */
@Schema(description = "购物车数据项")
public class CartItemVo implements Serializable {

    @Schema(description = "购物车数据项")
    private Long shopId;

    @Schema(description = "购物车商品id")
    private Long skuId;

    @Schema(description = "是否选中 true选中 false不选中")
    private Boolean check = true;

    @Schema(description = "sku名称")
    private String title;

    @Schema(description = "sku图片")
    private String image;

    /**
     * 商品套餐属性
     */
    private List<String> skuAttrValues;

    @Schema(description = "价格")
    private BigDecimal price;

    @Schema(description = "数量")
    private Integer count;

    @Schema(description = "总价")
    private BigDecimal totalPrice;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getSkuAttrValues() {
        return skuAttrValues;
    }

    public void setSkuAttrValues(List<String> skuAttrValues) {
        this.skuAttrValues = skuAttrValues;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getTotalPrice() {
        return this.price.multiply(new BigDecimal("" + this.count));
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
