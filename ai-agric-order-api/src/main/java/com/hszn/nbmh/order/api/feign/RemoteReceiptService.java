package com.hszn.nbmh.order.api.feign;

import com.hszn.nbmh.common.core.constant.ServiceNameConstant;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.order.api.constant.OrderPathConstant;
import com.hszn.nbmh.order.api.entity.NbmhReceipt;
import com.hszn.nbmh.order.api.fallback.ReceiptServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午4:44 22/9/6
 * @Modified By:
 */
@FeignClient(value = ServiceNameConstant.ORDER_SERVICE, path = OrderPathConstant.RECEIPT_URL, fallback = ReceiptServiceFallback.class)
public interface RemoteReceiptService {

    @PostMapping("/addReceipt")
    Result addReceipt(@RequestBody NbmhReceipt receipt);
}
