package com.hszn.nbmh.prevent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 防疫信息表 Mapper 接口
 * </p>
 *
 * @author wangjun
 * @since 2022-08-16
 */
public interface NbmhVaccinMapper extends BaseMapper<NbmhVaccin> {


    List<NbmhVaccin> getVaccinPage(Map map);

}
