package com.hszn.nbmh.shop.api.params.out;

import com.hszn.nbmh.shop.api.entity.NbmhPurchasePublish;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午1:25 22/8/30
 * @Modified By:
 */
@Schema(description = "采购发布列表返回数据")
@Data
public class PublishListReturn {

    @Schema(description = "采购发布信息")
    private NbmhPurchasePublish purchasePublish;

    @Schema(description = "时间间隔")
    private String timeInterval;

    @Schema(description = "用户类型")
    private Integer userType;

    @Schema(description = "认证状态")
    private Integer authStatus;
}
