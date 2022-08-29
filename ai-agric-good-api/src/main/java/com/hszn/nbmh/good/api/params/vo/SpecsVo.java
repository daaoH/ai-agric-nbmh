package com.hszn.nbmh.good.api.params.vo;

import com.hszn.nbmh.good.api.entity.NbmhGoodsSpecification;
import lombok.Data;

import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午7:26 22/8/25
 * @Modified By:
 */
@Data
public class SpecsVo {

    private String name;

    private List<NbmhGoodsSpecification> valueList;
}
