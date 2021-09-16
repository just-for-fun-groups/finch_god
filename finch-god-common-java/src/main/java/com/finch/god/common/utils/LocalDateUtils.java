package com.finch.god.common.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateUtils {

    public static final String DEFAULT_YEAR = "YEAR";
    public static final String DEFAULT_MONTH = "MONTH";
    public static final String DEFAULT_DATE = "DAY";
    public static final String DEFAULT_HOUR = "HOUR";
    public static final String DEFAULT_MINUTE = "MINUTE";
    public static final String DEFAULT_SECOND = "SECOND";
    public static final String SDF_YEAR = "yyyy";
    public static final String SDF_DAY_1 = "yyyy-MM-dd";
    public static final String SDF_DAYS_2 = "yyyyMMdd";
    public static final String SDF_DAYS_3 = "yyyy.MM.dd";
    public static final String SDF_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String SDF_TIME_1 = "yyyyMMdd HH:mm:ss";
    public static final String SDF_TIME_2 = "HH:mm:ss";
    public static final String SDF_CN_1 = "yyyy年MM月dd日";
    public static final String SDF_CN_2 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String SDF_CN_3 = "yyyy年MM月";
    public static final String SDF_MONTH_1 = "yyyy-MM";


    private LocalDateUtils() {
    }

    /**
     * LocalDate转java.util.Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * java.util.Date转LocalDate
     */
    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDateTime转java.util.Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * java.util.Date转LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 获取秒级时间戳
     */
    public static Integer nowSecond() {
        Instant timestamp = Instant.now();
        return (int) timestamp.getEpochSecond();
    }

    /**
     * 获取毫秒时间戳
     */
    public static Long nowMills() {
        Instant timestamp = Instant.now();
        return timestamp.toEpochMilli();
    }

    /**
     * 秒级时间戳转Date
     */
    public static Date second2Date(Integer seconds) {
        return Date.from(Instant.ofEpochSecond((long) seconds));
    }

    /**
     * Date转秒级时间戳
     */
    public static Integer date2Second(Date date) {
        return (int) (date.getTime() / 1000);
    }

    /**
     * 获取今天，默认返回 yyyy-MM-dd 格式
     */
    public static String getToday() {
        LocalDate today = LocalDate.now();
        return today.toString();
    }

    /**
     * 获取今天，默认返回 自定义 格式
     */
    public static String getToday(String customizePattern) {
        DateTimeFormatter customizeFormatter = DateTimeFormatter.ofPattern(customizePattern);
        LocalDate today = LocalDate.now();
        return today.format(customizeFormatter);
    }

    public static Date stringToDate(String dateString, String customizePattern) {
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(customizePattern));
        return localDate2Date(localDate);
    }

    /**
     * 日期转字符串 格式22021-09-01
     */
    public static String dateTimeToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(SDF_TIME);
        if(date != null){
            return format.format(date);
        }
        return null;
    }

}
