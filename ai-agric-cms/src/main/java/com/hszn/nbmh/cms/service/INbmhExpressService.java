package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhAppealRecord;
import com.hszn.nbmh.cms.api.entity.NbmhExpress;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 快递公司表 服务类
 * </p>
 *
 * @author pyq
 * @since 2022-09-13
 */
public interface INbmhExpressService extends IService<NbmhExpress> {
    /**
     * 保存
     *
     * @param nbmhExpressList 快递公司列表
     * @return the list
     */
    List<Integer> save(List<NbmhExpress> nbmhExpressList);

    /**
     * 更新
     *
     * @param nbmhExpressList 快递公司列表
     * @return the int
     */
    int update(List<NbmhExpress> nbmhExpressList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhExpress> query(@NotNull NbmhExpress entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhExpress> list(@NotNull NbmhExpress entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList    the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
