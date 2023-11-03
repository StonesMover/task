package com.zzx.task.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.Date;

public class DateUtils {

    public static String GetTimeNow(){
        String now = StrUtil.subSuf(DateUtil.now(),10).replace(":","").trim();
        return now;
    }

    public static String GetDateToDay(){
        Date date = DateUtil.date();
        //计算今天是今年的第几天
        int toDay = DateUtil.dayOfYear(date);
        //计算年份
        String year  = StrUtil.subSuf(Convert.toStr(DateUtil.year(date)),2);
        //计算世纪
        String century = "1";
        return century+year+toDay;
    }
}