package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhPrescriptionDrug;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 处方药品列表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Validated
public interface INbmhPrescriptionDrugService extends IService<NbmhPrescriptionDrug> {

    /**
     * 保存
     *
     * @param nbmhPrescriptionDrugList 处方药品列表
     * @return the list
     */
    List<Integer> save(List<NbmhPrescriptionDrug> nbmhPrescriptionDrugList);

    /**
     * 更新
     *
     * @param nbmhPrescriptionDrugList 处方药品列表
     * @return the int
     */
    int update(List<NbmhPrescriptionDrug> nbmhPrescriptionDrugList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhPrescriptionDrug> query(@NotNull NbmhPrescriptionDrug entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhPrescriptionDrug> list(@NotNull NbmhPrescriptionDrug entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
