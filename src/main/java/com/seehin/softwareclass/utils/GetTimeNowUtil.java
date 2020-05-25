package com.seehin.softwareclass.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/10 2:02
 */
public class GetTimeNowUtil {

    public Timestamp getTimestamp(){
        //定义时间：yyyy-MM-dd hh:mm:ss
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        timestamp = Timestamp.valueOf(timestamp.toString().substring(0,timestamp.toString().indexOf(".")));
        return timestamp;
    }

    public String getTimestampOfString(){
        //定义时间：yyyy-MM-dd hh:mm:ss
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.toString().substring(0,timestamp.toString().indexOf("."));
    }

    public String getDateOfString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }
}
