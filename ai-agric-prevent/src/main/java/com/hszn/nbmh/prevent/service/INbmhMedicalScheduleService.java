package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalSchedule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhMedicalSchedule;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 接诊时间设置 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Validated
public interface INbmhMedicalScheduleService extends IService<NbmhMedicalSchedule> {
    /**
     * 保存
     *
     * @param nbmhMedicalScheduleList 接诊时间设置列表
     * @return the list
     */
    List<Integer> save(List<NbmhMedicalSchedule> nbmhMedicalScheduleList);

    /**
     * 更新
     *
     * @param nbmhMedicalScheduleList 接诊时间设置列表
     * @return the int
     */
    int update(List<NbmhMedicalSchedule> nbmhMedicalScheduleList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhMedicalSchedule> query(@NotNull NbmhMedicalSchedule entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhMedicalSchedule> list(@NotNull NbmhMedicalSchedule entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);
}
