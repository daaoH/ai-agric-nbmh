package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhFarm;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 养殖场信息表 服务类
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */
@Validated
public interface INbmhFarmService extends IService<NbmhFarm> {

    /**
     * 保存
     *
     * @param nbmhFarmList 养殖场信息列表
     * @return the list
     */
    List<Integer> save(List<NbmhFarm> nbmhFarmList);

    /**
     * 更新
     *
     * @param NbmhFarmList 养殖场信息列表
     * @return the int
     */
    int update(@NotEmpty List<NbmhFarm> NbmhFarmList);

    /**
     * 批量更新养殖场动物JSON数据
     *
     * @param animalList 动物列表
     * @param updateType   更新类型
     */
    void updateAnimalJson(List<NbmhAnimal> animalList, int updateType);

    /**
     * 更新养殖场动物JSON数据
     *
     * @param animal    动物
     * @param updateType  更新类型
     * @return the int
     */
    int updateAnimalJson(NbmhAnimal animal, int updateType);

    /**
     * 分页查询
     *
     * @param entity        查询条件的实体
     * @param pageNum       第几页
     * @param pageSize      每页多少条
     * @param orderItemList 排序方式
     * @return the page
     */
    IPage<NbmhFarm> query(@NotNull NbmhFarm entity, int pageNum, int pageSize, List<OrderItem> orderItemList);

    /**
     * 查询
     *
     * @param entity        查询条件的实体
     * @param orderItemList 排序方式
     * @return the list
     */
    List<NbmhFarm> list(@NotNull NbmhFarm entity, List<OrderItem> orderItemList);

    /**
     * 删除.
     *
     * @param idList the id list
     */
    void delete(@NotEmpty List<Long> idList);

}

