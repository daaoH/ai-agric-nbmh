package com.hszn.nbmh.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 兽医详细信息表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-30
 */
@Validated
public interface INbmhAnimalDoctorDetailService extends IService<NbmhAnimalDoctorDetail> {

    /**
     * 保存
     *
     * @param nbmhAnimalDoctorDetailList 兽医详细信息
     * @return the list
     */
    List<Integer> save(List<NbmhAnimalDoctorDetail> nbmhAnimalDoctorDetailList);

    /**
     * 更新
     *
     * @param nbmhAnimalDoctorDetailList 兽医详细信息
     * @return the int
     */
    int update(List<NbmhAnimalDoctorDetail> nbmhAnimalDoctorDetailList);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhAnimalDoctorDetail> query(@NotNull NbmhAnimalDoctorDetail entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhAnimalDoctorDetail> list(@NotNull NbmhAnimalDoctorDetail entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);

    /**
     * 更新兽医专家接诊次数
     *
     * @param doctorId 兽医Id
     * @return the int
     */
    int updateAcceptOrderNum(@NotNull Long doctorId);
}
