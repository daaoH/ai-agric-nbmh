package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalAccept;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 动物诊疗接单记录 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
public interface INbmhMedicalAcceptService extends IService<NbmhMedicalAccept> {

    /**
     * 保存
     *
     * @param nbmhMedicalAcceptList 动物诊疗接单记录列表
     * @return the list
     */
    List<Integer> save(List<NbmhMedicalAccept> nbmhMedicalAcceptList);

    /**
     * 更新
     *
     * @param nbmhMedicalAcceptList 动物诊疗接单记录列表
     * @return the int
     */
    int update(List<NbmhMedicalAccept> nbmhMedicalAcceptList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhMedicalAccept> query(@NotNull NbmhMedicalAccept entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhMedicalAccept> list(@NotNull NbmhMedicalAccept entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);

    /**
     * 兽医接单
     *
     * @param nbmhMedicalAcceptList 动物诊疗接单记录列表
     * @return the int
     */
    int acceptOrder(List<NbmhMedicalAccept> nbmhMedicalAcceptList);
}
