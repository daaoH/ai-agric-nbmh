package com.hszn.nbmh.prevent.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.common.core.utils.DateUtils;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.prevent.api.entity.*;
import com.hszn.nbmh.prevent.api.params.input.StatistissParam;
import com.hszn.nbmh.prevent.api.params.out.MapAndDataResult;
import com.hszn.nbmh.prevent.api.params.out.VaccinStatisticsResult;
import com.hszn.nbmh.prevent.service.*;
import com.hszn.nbmh.user.api.feign.RemoteUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 防疫统计 前端控制器
 * </p>
 *
 * @author wangjun
 * @since 2022-09-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/nbmh-vaccin-statistics")
@Tag(name="防疫端统计接口")
public class NbmhVaccinStatisticsController {

    //用户接口
    private final RemoteUserService userService;

    //动物接口
    private final INbmhAnimalService nbmhAnimalService;

    //屠宰/无害化
    private final INbmhButcherReportService butcherReportService;

    //防疫接口
    private final INbmhVaccinService nbmhVaccinService;

    //检疫接口
    private final INbmhInspectService inspectService;

    //举报接口
    private final INbmhCheckService checkService;


    @PostMapping("/totality")
    @Operation(summary="站长-总体统计数据")
    @Inner(false)
    public Result totality(@RequestBody StatistissParam params) {

        //相应结果
        VaccinStatisticsResult result=new VaccinStatisticsResult();

        //返回值组转对象
        List<MapAndDataResult> mapAndDataResults=new ArrayList<>();


        //养殖户人数
        result.setUserCount((Long) userService.getUserInfoCount(params.getPreventStationId(), 5).getData());

        //免疫接种总数量
        result.setAnimalCount(nbmhAnimalService.count(Wrappers.<NbmhAnimal>query().lambda().eq(NbmhAnimal::getPreventStationId, params.getPreventStationId())));

        //自屠宰数量
        result.setButcherCount(butcherReportService.count(Wrappers.<NbmhButcherReport>query().lambda().eq(NbmhButcherReport::getPreventStationId, params.getPreventStationId()).eq(NbmhButcherReport::getReportType, 0)));

        //无害化数量
        result.setHarmlessCount(butcherReportService.count(Wrappers.<NbmhButcherReport>query().lambda().eq(NbmhButcherReport::getPreventStationId, params.getPreventStationId()).eq(NbmhButcherReport::getReportType, 1)));

        //开始时间
        String date="";
        if (params.isHalfYear()) {
            date=DateUtils.getDateHHMMSSScope("halfYear", null).get("halfYear");
        } else if (params.isOneYear()) {
            date=DateUtils.getDateHHMMSSScope("oneYear", null).get("oneYear");
        } else {
            System.out.println("待增加参数");
        }
        //当前时间
        String endTime=DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        // *************************************************************************************************************************************************
        //防疫数据
        List<NbmhVaccin> vaccins=nbmhVaccinService.list(Wrappers.<NbmhVaccin>query().lambda().eq(NbmhVaccin::getPreventStationId, params.getPreventStationId())
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') >= date_format('" + date + "','%Y-%m-%d %H:%i:%s')")
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') <= date_format('" + endTime + "','%Y-%m-%d %H:%i:%s')"));
        //数据分组
        Map<String, List<NbmhVaccin>> vGroupMap=vaccins.stream()
                .sorted(Comparator.comparing(NbmhVaccin::getCreateTime, Comparator.nullsLast(Date::compareTo)))
                .collect(Collectors.groupingBy(o -> DateFormatUtils.format(o.getCreateTime(), "MM")));

        //返回值对象组装
        MapAndDataResult vaccinMapAndData=new MapAndDataResult();
        //数组数值
        List<Integer> vaccinDataList=new ArrayList<>();
        //分组处理数据
        for (Map.Entry<String, List<NbmhVaccin>> entry : vGroupMap.entrySet()) {
            vaccinDataList.add(entry.getValue().size());
        }
        vaccinMapAndData.setName("防疫登记");
        vaccinMapAndData.setData(vaccinDataList);
        mapAndDataResults.add(vaccinMapAndData);
        // *************************************************************************************************************************************************
        //检疫数据
        List<NbmhInspect> inspects=inspectService.list(Wrappers.<NbmhInspect>query().lambda().eq(NbmhInspect::getPreventStationId, params.getPreventStationId())
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') >= date_format('" + date + "','%Y-%m-%d %H:%i:%s')")
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') <= date_format('" + endTime + "','%Y-%m-%d %H:%i:%s')"));

        //数据分组
        Map<String, List<NbmhInspect>> iGroupMap=inspects.stream()
                .sorted(Comparator.comparing(NbmhInspect::getCreateTime, Comparator.nullsLast(Date::compareTo)))
                .collect(Collectors.groupingBy(o -> DateFormatUtils.format(o.getCreateTime(), "MM")));

        //返回值对象组装
        MapAndDataResult inspectMapAndData=new MapAndDataResult();
        //数组数值
        List<Integer> inspectDataList=new ArrayList<>();
        //分组处理数据
        for (Map.Entry<String, List<NbmhInspect>> entry : iGroupMap.entrySet()) {
            inspectDataList.add(entry.getValue().size());
        }
        inspectMapAndData.setName("检疫登记");
        inspectMapAndData.setData(inspectDataList);
        mapAndDataResults.add(inspectMapAndData);
        // *************************************************************************************************************************************************
        //自屠宰数量
        List<NbmhButcherReport> butchers=butcherReportService.list(Wrappers.<NbmhButcherReport>query().lambda().eq(NbmhButcherReport::getPreventStationId, params.getPreventStationId()).eq(NbmhButcherReport::getReportType, 0)
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') >= date_format('" + date + "','%Y-%m-%d %H:%i:%s')")
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') <= date_format('" + endTime + "','%Y-%m-%d %H:%i:%s')"));
        //数据分组
        Map<String, List<NbmhButcherReport>> bGroupMap=butchers.stream()
                .sorted(Comparator.comparing(NbmhButcherReport::getCreateTime, Comparator.nullsLast(Date::compareTo)))
                .collect(Collectors.groupingBy(o -> DateFormatUtils.format(o.getCreateTime(), "MM")));

        //返回值对象组装
        MapAndDataResult butchersMapAndData=new MapAndDataResult();
        //数组数值
        List<Integer> butchersDataList=new ArrayList<>();
        //分组处理数据
        for (Map.Entry<String, List<NbmhButcherReport>> entry : bGroupMap.entrySet()) {
            butchersDataList.add(entry.getValue().size());
        }
        butchersMapAndData.setName("自屠宰登记");
        butchersMapAndData.setData(butchersDataList);
        mapAndDataResults.add(butchersMapAndData);
        // *************************************************************************************************************************************************
        //无害化数据
        List<NbmhButcherReport> harmless=butcherReportService.list(Wrappers.<NbmhButcherReport>query().lambda().eq(NbmhButcherReport::getPreventStationId, params.getPreventStationId()).eq(NbmhButcherReport::getReportType, 1)
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') >= date_format('" + date + "','%Y-%m-%d %H:%i:%s')")
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') <= date_format('" + endTime + "','%Y-%m-%d %H:%i:%s')"));
        //数据分组
        Map<String, List<NbmhButcherReport>> hGroupMap=harmless.stream()
                .sorted(Comparator.comparing(NbmhButcherReport::getCreateTime, Comparator.nullsLast(Date::compareTo)))
                .collect(Collectors.groupingBy(o -> DateFormatUtils.format(o.getCreateTime(), "MM")));

        //返回值对象组装
        MapAndDataResult harmlessMapAndData=new MapAndDataResult();
        //数组数值
        List<Integer> harmlessDataList=new ArrayList<>();
        //分组处理数据
        for (Map.Entry<String, List<NbmhButcherReport>> entry : hGroupMap.entrySet()) {
            harmlessDataList.add(entry.getValue().size());
        }
        harmlessMapAndData.setName("无害化登记");
        harmlessMapAndData.setData(harmlessDataList);
        mapAndDataResults.add(harmlessMapAndData);
        // *************************************************************************************************************************************************
        //稽查举报数据
        List<NbmhCheck> checks=checkService.list(Wrappers.<NbmhCheck>query().lambda().eq(NbmhCheck::getPreventStationId, params.getPreventStationId())
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') >= date_format('" + date + "','%Y-%m-%d %H:%i:%s')")
                .apply("date_format (create_time,'%Y-%m-%d %H:%i:%s') <= date_format('" + endTime + "','%Y-%m-%d %H:%i:%s')"));
        //数据分组
        Map<String, List<NbmhCheck>> cGroupMap=checks.stream()
                .sorted(Comparator.comparing(NbmhCheck::getCreateTime, Comparator.nullsLast(Date::compareTo)))
                .collect(Collectors.groupingBy(o -> DateFormatUtils.format(o.getCreateTime(), "MM")));

        //返回值对象组装
        MapAndDataResult checksMapAndData=new MapAndDataResult();
        //数组数值
        List<Integer> checksDataList=new ArrayList<>();
        //分组处理数据
        for (Map.Entry<String, List<NbmhCheck>> entry : cGroupMap.entrySet()) {
            checksDataList.add(entry.getValue().size());
        }
        checksMapAndData.setName("无害化登记");
        checksMapAndData.setData(checksDataList);
        mapAndDataResults.add(checksMapAndData);
        // *************************************************************************************************************************************************
        //返回值组装
        result.setCategories(DateUtils.getIntermediateMonth(date, endTime));//数据组装底部月份集合
        result.setSeries(mapAndDataResults);
        return Result.ok(result);
    }
}
