package com.hszn.nbmh.common.core.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DateUtils {


//    public static void main(String[] args) throws IOException {
//        Map<String, String> week=getDateScope("month", null);
//        Map<String, String> dateHHMMSSScope=getDateHHMMSSScope("halfYear", null);
//        String data=DateUtils.getDateHHMMSSScope("halfYear", null).get("halfYear");
//        System.out.println(data);
//        System.out.println(dateHHMMSSScope);
//    }

    public static Map<String, String> getDateScope(String scope, Map<String, String> detail) {

        Map<String, String> dateMap=new HashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String minDate=format.format(date);
        dateMap.put("minDate", minDate);

        Calendar c=Calendar.getInstance();
        switch (scope) {
            case "week"://七天
                c.setTime(new Date());
                c.add(Calendar.DATE, 7);
                Date d=c.getTime();
                String maxDate=format.format(d);
                dateMap.put("maxDate", maxDate);
                break;

            case "halfMonth"://半个月
                c.setTime(new Date());
                c.add(Calendar.DATE, 15);
                Date h=c.getTime();
                String halfMonth=format.format(h);
                dateMap.put("maxDate", halfMonth);
                break;

            case "month"://一个月
                c.setTime(new Date());
                c.add(Calendar.MONTH, 1);
                Date m=c.getTime();
                String mon=format.format(m);
                dateMap.put("maxDate", mon);
                break;

            case "threeMonth"://三个月
                c.setTime(new Date());
                c.add(Calendar.MONTH, 3);
                Date m3=c.getTime();
                String mon3=format.format(m3);
                dateMap.put("maxDate", mon3);
                break;

            case "halfYear"://半年
                c.setTime(new Date());
                c.add(Calendar.MONTH, 6);
                Date m6=c.getTime();
                String mon6=format.format(m6);
                dateMap.put("maxDate", mon6);
                break;

            case "year"://一年
                c.setTime(new Date());
                c.add(Calendar.YEAR, 1);
                Date y=c.getTime();
                String year=format.format(y);
                dateMap.put("maxDate", year);
                break;
            case "cus"://自定义
                dateMap=detail;
                break;

        }
        return dateMap;
    }

    public static Map<String, String> getDateHHMMSSScope(String scope, Map<String, String> detail) {

        Map<String, String> dateMap=new HashMap<>();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c=Calendar.getInstance();
        switch (scope) {

            case "10min"://十分钟前
                Date tenMinutes=new Date();
                c.setTime(tenMinutes);
                c.add(Calendar.MINUTE, -10);
                Date d=c.getTime();
                String min=format.format(d);
                dateMap.put("minDate", min);
                dateMap.put("maxDate", format.format(tenMinutes));
                break;

            case "1h"://一小时前
                Date oneHourDate=new Date();
                c.setTime(oneHourDate);
                c.add(Calendar.HOUR, -1);
                Date h=c.getTime();
                String halfMonth=format.format(h);
                dateMap.put("minDate", halfMonth);
                dateMap.put("maxDate", format.format(oneHourDate));
                break;

            case "3h"://三小时前
                Date hDate=new Date();
                c.setTime(hDate);
                c.add(Calendar.HOUR, -3);
                Date m=c.getTime();
                String mon=format.format(m);
                dateMap.put("minDate", mon);
                dateMap.put("maxDate", format.format(hDate));
                break;

            case "today"://今天
                buidDateScope(dateMap, 0);
                break;

            case "yesterday"://昨天
                buidDateScope(dateMap, -1);
                break;

            case "beforeyesterday"://前天
                buidDateScope(dateMap, -2);
                break;

            case "halfYear"://近年前
                Date hyDate=new Date();
                c.setTime(hyDate);
                c.add(Calendar.MONTH, -6);
                Date hy=c.getTime();
                String month=format.format(hy);
                dateMap.put("halfYear", month);
                break;
            case "oneYear"://近一年
                Date oneDate=new Date();
                c.setTime(oneDate);
                c.add(Calendar.MONTH, -12);
                Date on=c.getTime();
                String oneYear=format.format(on);
                dateMap.put("oneYear", oneYear);
                break;
            case "cus"://自定义
                dateMap=detail;
                break;

        }
        return dateMap;
    }

    /**
     * @param dateMap 结果
     * @param day     0 当天开始结束时间、1明天开始结果时间 -1 前天开始结束时间
     * @return void
     * @Description //TODO 获取前后天的时间，返回、
     * @Date 15:34 2020/7/1
     **/
    private static void buidDateScope(Map<String, String> dateMap, int day) {

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar=new GregorianCalendar();
        calendar.add(Calendar.DATE, day);
        //一天的开始时间 yyyy:MM:dd 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dayStart=calendar.getTime();
        String startStr=format.format(dayStart);
        //一天的结束时间 yyyy:MM:dd 23:59:59
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date dayEnd=calendar.getTime();
        String endStr=format.format(dayEnd);
        dateMap.put("minDate", startStr);
        dateMap.put("maxDate", endStr);
    }


    /**
     * 获取两个时间时间段内日期集合
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return List<String>
     */
    public static List<String> getBetweenDates(String startDate, String endDate) {
        //返回结果
        List<String> result=new ArrayList<>();
        Date start=null;//定义起始日期
        Date end=null;//定义结束日期
        try {
            start=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            end=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=start;
        Calendar cd=Calendar.getInstance();//使用Calendar进行日期比较
        while (date.getTime() <= end.getTime()) {
            result.add(sdf.format(date));
            cd.setTime(date);
            cd.add(Calendar.DATE, 1);//增加一天放入集合
            date=cd.getTime();
        }
        return result;
    }


    /**
     * @param startDate 开始月份
     * @param endDate   开始月份
     * @return List<String>
     */
    public static List<String> getIntermediateMonth(String startDate, String endDate) {
        //返回结果
        List<String> result=new ArrayList<>();
        Calendar startTime=Calendar.getInstance();
        Calendar endTime=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        try {
            startTime.setTime(sdf.parse(startDate));
            startTime.add(Calendar.MONTH, -1);
            endTime.setTime(sdf.parse(endDate));
            while (startTime.before(endTime)) {
                startTime.add(Calendar.MONTH, 1);
                result.add(sdf.format(startTime.getTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {
        //返回结果
        List<String> result=new ArrayList<>();
        String startDate="2022-03-04";
        String endDate="2022-09-09";

        Calendar startTime=Calendar.getInstance();
        Calendar endTime=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        try {
            startTime.setTime(sdf.parse(startDate));
            startTime.add(Calendar.MONTH, -1);
            endTime.setTime(sdf.parse(endDate));
            while (startTime.before(endTime)) {
                startTime.add(Calendar.MONTH, 1);
                result.add(sdf.format(startTime.getTime()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(JSON.toJSONString(result));

    }


}



