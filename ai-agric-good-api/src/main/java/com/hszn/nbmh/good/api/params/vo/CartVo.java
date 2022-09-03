package com.hszn.nbmh.good.api.params.vo;

import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author：袁德民
 * @Description:　购物车数据
 * @Date:下午1:39 22/9/2
 * @Modified By:
 */
public class CartVo {

    /**
     * 购物车子项信息
     */
    List<CartItemVo> items;

    /**
     * 商品数量
     */
    private Integer countNum;

    /**
     * 商品类型数量
     */
    private Integer countType;

    /**
     * 商品总价
     */
    private BigDecimal totalAmount;

    /**
     * 减免价格
     */
    private BigDecimal reduce = new BigDecimal("0.00");

    public List<CartItemVo> getItems() {
        return items;
    }

    public void setItems(List<CartItemVo> items) {
        this.items = items;
    }

    public Integer getCountNum() {
        int count = 0;
        if(items != null && items.size() > 0){
            for(CartItemVo item : items){
                count += item.getCount();
            }
        }
        return count;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getCountType() {
        int count = 0;
        if(items != null && items.size() > 0){
            for(CartItemVo item : items){
                count += 1;
            }
        }
        return count;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal("0");
        //计算购物车项总价
        if(!CollectionUtils.isEmpty(items)){
            for(CartItemVo item : items){
                if(item.getCheck()){
                    amount = amount.add(item.getTotalPrice());
                }
            }
        }

        //减去优惠价格
        return amount.subtract(getReduce());
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }
}
