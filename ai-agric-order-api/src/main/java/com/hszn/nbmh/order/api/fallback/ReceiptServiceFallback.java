package com.hszn.nbmh.order.api.fallback;

import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.order.api.entity.NbmhReceipt;
import com.hszn.nbmh.order.api.feign.RemoteReceiptService;
import org.springframework.stereotype.Component;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午4:47 22/9/6
 * @Modified By:
 */
@Component
public class ReceiptServiceFallback implements RemoteReceiptService {

    @Override
    public Result addReceipt(NbmhReceipt receipt) {
        return null;
    }
}
