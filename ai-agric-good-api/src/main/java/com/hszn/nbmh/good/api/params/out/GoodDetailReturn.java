package com.hszn.nbmh.good.api.params.out;

import com.hszn.nbmh.good.api.entity.NbmhComment;
import com.hszn.nbmh.good.api.entity.NbmhGoods;
import com.hszn.nbmh.good.api.entity.NbmhGoodsAttribute;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSpecification;
import com.hszn.nbmh.good.api.params.vo.CommentVo;
import com.hszn.nbmh.good.api.params.vo.SkuVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午3:25 22/8/26
 * @Modified By:
 */
@Schema(description = "商品详情返回数据")
@Data
public class GoodDetailReturn implements Serializable {

    @Schema(description = "商品基本信息")
    private NbmhGoods goods;

    @Schema(description = "商品属性")
    private List<NbmhGoodsAttribute> goodsAttribute;

    @Schema(description = "商品规格")
    private List<NbmhGoodsSpecification> specification;

    @Schema(description = "评论数据")
    private CommentVo comments;

    @Schema(description = "sku列表")
    private List<SkuVo> skus;
}
