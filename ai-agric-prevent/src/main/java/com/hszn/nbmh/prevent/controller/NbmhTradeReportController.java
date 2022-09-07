package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.hszn.nbmh.common.core.enums.CommonEnum;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.NbmhAnimal;
import com.hszn.nbmh.prevent.api.entity.NbmhTradeReport;
import com.hszn.nbmh.prevent.api.params.input.TradeReportPageParam;
import com.hszn.nbmh.prevent.api.params.out.TradeReportPageResult;
import com.hszn.nbmh.prevent.service.INbmhAnimalService;
import com.hszn.nbmh.prevent.service.INbmhTradeReportService;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 活体交易信息表 前端控制器
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */
@Tag(name="活体交易信息报备")
@RestController
@RequiredArgsConstructor
@RequestMapping("/nbmh-trade-report")
public class NbmhTradeReportController {

    private final RemoteUserService userService;

    private final INbmhAnimalService animalService;

    @Autowired
    private INbmhTradeReportService tradeReportService;

    @Operation(summary = "新增活体交易信息报备", method = "POST")
    @PostMapping("/add")
    @Inner(false)
    public Result add(@RequestBody NbmhTradeReport nbmhTradeReport){

        List<Integer> idList = tradeReportService.save(Collections.singletonList(nbmhTradeReport));
        if(idList != null && idList.size()>0){
            return Result.ok();
        }

        return Result.failed(CommonEnum.DATA_ADD_FAILED.getMsg());
    }

    @Operation(summary = "根据ID查询活体交易信息报备", method = "POST")
    @Parameters({@Parameter(description="活体交易信息报备Id", name="id")})
    @PostMapping("/{id}")
    @Inner(false)
    public Result<NbmhTradeReport> getById(@PathVariable(value = "id") @NotBlank Long id) {

        return Result.ok(tradeReportService.getById(id));
    }

    @Operation(summary = "更新活体交易信息报备", method = "PUT")
    @PutMapping
    @Inner(false)
    public Result update(@RequestBody NbmhTradeReport nbmhTradeReport) {

        tradeReportService.update(Collections.singletonList(nbmhTradeReport));
        return Result.ok();
    }

    @Operation(summary = "分页查询活体交易信息", method = "POST")
    @Parameters({@Parameter(description="页码", name="pageNum"), @Parameter(description="每页显示条数", name="pageSize"), @Parameter(description="排序条件集合", name="orderItemList")})
    @PostMapping("/query")
    @Inner(false)
    public Result<IPage<NbmhTradeReport>> query(@RequestBody NbmhTradeReport nbmhTradeReport,
                                                @RequestParam @DecimalMin("1") int pageNum,
                                                @RequestParam @DecimalMin("1") int pageSize,
                                                @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        IPage<NbmhTradeReport> butcherReportPage = tradeReportService.query(nbmhTradeReport, pageNum, pageSize, orderItemList);

        return Result.ok(butcherReportPage);
    }

    @Operation(summary = "查询活体交易信息", method = "POST")
    @Parameters({@Parameter(description="排序条件集合", name="orderItemList")})
    @PostMapping("/list")
    @Inner(false)
    public Result<List<NbmhTradeReport>> list(@RequestBody NbmhTradeReport nbmhTradeReport,
                                              @RequestParam(value = "orderItemList", required = false) List<OrderItem> orderItemList) {

        return Result.ok(tradeReportService.list(nbmhTradeReport, orderItemList));
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value="删除活体交易信息报备")
    @Inner(false)
    public Result delete(@PathVariable Long id) {

        tradeReportService.delete(Collections.singletonList(id));
        return Result.ok();
    }


    @PostMapping("/tradeReportByPage")
    @Operation(summary="活体报备获取农户")
    public Result tradeReport(@RequestBody QueryRequest<TradeReportPageParam> param) {
        //返回结果集
        TradeReportPageResult tradeReportPageResult=new TradeReportPageResult<>();

        if (ObjectUtils.isEmpty(param.getQueryEntity())) {
            return Result.failed("请确认参数信息!");
        }
        //商贩信息
        CurUserInfo buyerUserInfo=userService.queryCurUserInfo(param.getQueryEntity().getBuyerId(), 6).getData();
        if (ObjectUtils.isEmpty(buyerUserInfo) ||
                ObjectUtils.isEmpty(buyerUserInfo.getUser()) ||
                ObjectUtils.isEmpty(buyerUserInfo.getExtraInfo())) {
            return Result.failed("请确认当前是用户身份是否为商贩用户!");
        }
        //养殖户信息
        CurUserInfo farmerUserInfo=userService.queryCurUserInfo(param.getQueryEntity().getFarmerId(), 5).getData();
        if (ObjectUtils.isEmpty(farmerUserInfo) ||
                ObjectUtils.isEmpty(farmerUserInfo.getUser()) ||
                ObjectUtils.isEmpty(farmerUserInfo.getExtraInfo())) {
            return Result.failed("请确认当前是用户身份是否为养殖用户!");
        }

        //添加条件
        LambdaQueryWrapper<NbmhAnimal> queryWrapper=new LambdaQueryWrapper<>();
        //农户id
        queryWrapper.eq(NbmhAnimal::getUserId, farmerUserInfo.getExtraInfo().getUserId());
        //状态   0=正常动物状态
        queryWrapper.eq(NbmhAnimal::getStatus, 0);

        //返回数据集
        List<NbmhAnimal> animals=animalService.list(queryWrapper);
        //获取数据集分组
        Map<Integer, List<NbmhAnimal>> groupMap=animals.stream().collect(Collectors.groupingBy(animal -> animal.getType()));

        //类型集合
        List<Integer> types=new ArrayList<>();

        //动物集合
        List<NbmhAnimal> animalList=new ArrayList<>();

        //分组处理数据
        for (Map.Entry<Integer, List<NbmhAnimal>> entry : groupMap.entrySet()) {
            types.add(entry.getValue().get(0).getType());
            if (entry.getValue().get(0).getType() == param.getQueryEntity().getAnimalType()) {
                animalList=animals.stream().skip((param.getPageNum() - 1) * param.getPageSize()).limit(param.getPageSize()).collect(Collectors.toList());
            }
        }
        tradeReportPageResult.setBuyerId(param.getQueryEntity().getBuyerId());
        tradeReportPageResult.setBuyerName(buyerUserInfo.getExtraInfo().getRealName());
        tradeReportPageResult.setBuyerPhone(buyerUserInfo.getUser().getPhone());
        tradeReportPageResult.setFarmerAvatarUrl(farmerUserInfo.getUser().getAvatarUrl());
        tradeReportPageResult.setFarmerId(param.getQueryEntity().getFarmerId());
        tradeReportPageResult.setFarmerName(farmerUserInfo.getExtraInfo().getRealName());
        tradeReportPageResult.setFarmerPhone(farmerUserInfo.getUser().getPhone());
        tradeReportPageResult.setList(animalList);
        return Result.ok(tradeReportPageResult);
    }

}
