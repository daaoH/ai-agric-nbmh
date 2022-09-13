package com.hszn.nbmh.order.rabbitmq;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.hszn.nbmh.common.core.constant.TimeConstant;
import com.hszn.nbmh.common.core.exception.ServiceException;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.redis.cache.CachePrefix;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSku;
import com.hszn.nbmh.good.api.feign.RemoteGoodsSkuService;
import com.hszn.nbmh.good.api.params.vo.CartItemVo;
import com.hszn.nbmh.good.api.params.vo.ShopCartItemVo;
import com.hszn.nbmh.order.api.constant.OrderQueueNameConstant;
import com.hszn.nbmh.order.api.params.input.CreateOrderMessage;
import com.hszn.nbmh.order.api.params.input.CreateOrderParam;
import com.hszn.nbmh.order.api.params.input.PreOrderParam;
import com.hszn.nbmh.order.service.INbmhOrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author：袁德民
 * @Description:订单队列监听
 * @Date:下午8:48 22/9/13
 * @Modified By:
 */

@Slf4j
@Component
public class OrderListener {

    @Autowired
    private INbmhOrderService orderService;

    @Autowired
    private RemoteGoodsSkuService goodsSkuService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 订单创建消费者
     * @param orderMessage
     * @param channel
     * @param message
     * @throws IOException
     */
    @RabbitListener(queues = OrderQueueNameConstant.ORDER_CREATE)
    public void createOrder(CreateOrderMessage orderMessage, Channel channel, Message message) throws IOException {
        log.info("收到订单创建消息:deliveryTag{},当前时间{},消息内容{}.", message.getMessageProperties().getDeliveryTag(),
                DateUtil.now(),
                orderMessage.toString());
        try{
           CreateOrderParam param = orderMessage.getParam();
           Long orderId = orderMessage.getOrderId();
            List<PreOrderParam> orderItems = param.getPreOrderItems();
            for(PreOrderParam orderParam : orderItems){
                ShopCartItemVo cartItemVo = orderParam.getCartItems();
                List<NbmhGoodsSku> skuList = new ArrayList<NbmhGoodsSku>(cartItemVo.getItems().size());
                for(CartItemVo item : cartItemVo.getItems()){
                    Result<NbmhGoodsSku> ret = goodsSkuService.getGoodsSkuById(item.getSkuId());
                    if(ret.getCode() == 200){
                        NbmhGoodsSku goodsSku = ret.getData();
                        if(ObjectUtil.isNull(goodsSku)){
                            redisTemplate.opsForValue().setIfPresent("Order:Failed:" + orderId.toString(), "商品信息已经更新", TimeConstant.ONE_DAY, TimeUnit.SECONDS);
                            throw new ServiceException("商品信息已经更新");
                        }

                        int stock = goodsSku.getStock();
                        if(stock < 1) {
                            redisTemplate.opsForValue().set(CachePrefix.SKU_STOCK.getPrefix() + item.getSkuId(), stock);
                            redisTemplate.opsForValue().setIfPresent("Order:Failed:" + orderId.toString(), "商品信息已经更新", TimeConstant.ONE_DAY, TimeUnit.SECONDS);
                            throw new ServiceException("商品信息已经更新");
                        }

                        skuList.add(goodsSku);
                    }

                }

                orderService.createOrder(cartItemVo);

            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            log.info("订单创建执行失败: deliveryTag{}", message.getMessageProperties().getDeliveryTag());

            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
