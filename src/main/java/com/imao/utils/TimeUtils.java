package com.imao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;



/**
 * 
* <p>Title: TimeUtils.java</p> 
* <p>Description: 时间工具类</p>
* @author chenkang
* @date 2018年8月25日  
* @version 1.0
 */
public class TimeUtils {
    
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String MINUTE_ONLY_PATTERN = "mm";
    public static final String HOUR_ONLY_PATTERN = "HH";
    
    /**
     * 日期相加减天数
     * @param date 如果为Null，则为当前时间
     * @param days 加减天数
     * @param includeTime 是否包括时分秒,true表示包含
     * @return
     * @throws ParseException 
     */
    public static Date dateAdd(Date date, int days, boolean includeTime) throws ParseException{
        if(date == null){
            date = new Date();
        }
        if(!includeTime){
            SimpleDateFormat sdf = new SimpleDateFormat(TimeUtils.DATE_TIME_PATTERN);
            date = sdf.parse(sdf.format(date));
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }
    
    /**
     * 时间格式化成字符串
     * @param date Date
     * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String dateFormat(Date date, String pattern) throws ParseException{
        if(StringUtils.isEmpty(pattern)){
            pattern = TimeUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    /**
     * 字符串解析成时间对象
     * @param dateTimeString String
     * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN，如果为空，则为yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date dateParse(String dateTimeString, String pattern) throws ParseException{
        if(StringUtils.isEmpty(pattern)){
            pattern = TimeUtils.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateTimeString);
    }
    
    /**
     * 将日期时间格式成只有日期的字符串（可以直接使用dateFormat，Pattern为Null进行格式化）
     * @param dateTime Date
     * @return
     * @throws ParseException
     */
    public static String dateTimeToDateString(Date dateTime) throws ParseException{
        String dateTimeString = TimeUtils.dateFormat(dateTime, TimeUtils.DATE_TIME_PATTERN);  
        return dateTimeString.substring(0, 10); 
    }
    /**
     * 将日期时间格式成只有日期的字符串（可以直接使用dateFormat，Pattern为Null进行格式化）
     * @param dateTime Date
     * @return
     * @throws ParseException
     */
    public static String dateTimeToString(Date dateTime) throws ParseException{
    	String dateTimeString = TimeUtils.dateFormat(dateTime, TimeUtils.DATE_TIME_PATTERN);  
    	return dateTimeString; 
    }
    
    /**
     * 当时、分、秒为00:00:00时，将日期时间格式成只有日期的字符串，
     * 当时、分、秒不为00:00:00时，直接返回
     * @param dateTime Date
     * @return
     * @throws ParseException
     */
    public static String dateTimeToDateStringIfTimeEndZero(Date dateTime) throws ParseException{
        String dateTimeString = TimeUtils.dateFormat(dateTime, TimeUtils.DATE_TIME_PATTERN);
        if(dateTimeString.endsWith("00:00:00")){
            return dateTimeString.substring(0, 10);
        }else{
            return dateTimeString;
        }
    }
    
    /**
     * 将日期时间格式成日期对象，和dateParse互用
     * @param dateTime Date
     * @return Date
     * @throws ParseException
     */
    public static Date dateTimeToDate(Date dateTime) throws ParseException{
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    /** 
     * 时间加减小时
     * @param startDate 要处理的时间，Null则为当前时间 
     * @param hours 加减的小时 
     * @return Date 
     */  
    public static Date dateAddHours(Date startDate, int hours) {  
        if (startDate == null) {  
            startDate = new Date();  
        }  
        Calendar c = Calendar.getInstance();  
        c.setTime(startDate);  
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);  
        return c.getTime();  
    }
    
    /**
     * 时间加减分钟
     * @param startDate 要处理的时间，Null则为当前时间 
     * @param minutes 加减的分钟
     * @return
     */
    public static Date dateAddMinutes(Date startDate, int minutes) {  
        if (startDate == null) {  
            startDate = new Date();  
        }  
        Calendar c = Calendar.getInstance();  
        c.setTime(startDate);  
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);  
        return c.getTime();  
    }
    
    /**
     * 时间加减秒数
     * @param startDate 要处理的时间，Null则为当前时间 
     * @param minutes 加减的秒数
     * @return
     */
    public static Date dateAddSeconds(Date startDate, int seconds) {  
        if (startDate == null) {  
            startDate = new Date();  
        }  
        Calendar c = Calendar.getInstance();  
        c.setTime(startDate);  
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + seconds);  
        return c.getTime();  
    }

    /** 
     * 时间加减天数 
     * @param startDate 要处理的时间，Null则为当前时间 
     * @param days 加减的天数 
     * @return Date 
     */  
    public static Date dateAddDays(Date startDate, int days) {  
        if (startDate == null) {  
            startDate = new Date();  
        }  
        Calendar c = Calendar.getInstance();  
        c.setTime(startDate);  
        c.set(Calendar.DATE, c.get(Calendar.DATE) + days);  
        return c.getTime();  
    }
    
    /** 
     * 时间加减月数
     * @param startDate 要处理的时间，Null则为当前时间 
     * @param months 加减的月数 
     * @return Date 
     */  
    public static Date dateAddMonths(Date startDate, int months) {  
        if (startDate == null) {  
            startDate = new Date();  
        }  
        Calendar c = Calendar.getInstance();  
        c.setTime(startDate);  
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);  
        return c.getTime();  
    }
    
    /** 
     * 时间加减年数
     * @param startDate 要处理的时间，Null则为当前时间 
     * @param years 加减的年数 
     * @return Date 
     */  
    public static Date dateAddYears(Date startDate, int years) {  
        if (startDate == null) {  
            startDate = new Date();  
        }  
        Calendar c = Calendar.getInstance();  
        c.setTime(startDate);  
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + years);  
        return c.getTime();  
    }  
    
    /** 
     * 时间比较（如果myDate>compareDate返回1，<返回-1，相等返回0） 
     * @param myDate 时间 
     * @param compareDate 要比较的时间 
     * @return int 
     */  
    public static int dateCompare(Date myDate, Date compareDate) {  
        Calendar myCal = Calendar.getInstance();  
        Calendar compareCal = Calendar.getInstance();  
        myCal.setTime(myDate);  
        compareCal.setTime(compareDate);  
        return myCal.compareTo(compareCal);  
    }
    
    /**
     * 获取两个时间中最小的一个时间
     * @param date
     * @param compareDate
     * @return
     */
    public static Date dateMin(Date date, Date compareDate) {
        if(date == null){
            return compareDate;
        }
        if(compareDate == null){
            return date;
        }
        if(1 == dateCompare(date, compareDate)){
            return compareDate;
        }else if(-1 == dateCompare(date, compareDate)){
            return date;
        }
        return date;  
    }
    
    /**
     * 获取两个时间中最大的一个时间
     * @param date
     * @param compareDate
     * @return
     */
    public static Date dateMax(Date date, Date compareDate) {
        if(date == null){
            return compareDate;
        }
        if(compareDate == null){
            return date;
        }
        if(1 == dateCompare(date, compareDate)){
            return date;
        }else if(-1 == dateCompare(date, compareDate)){
            return compareDate;
        }
        return date;  
    }
    
    /**
     * 获取两个日期（不含时分秒）相差的天数，不包含今天
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException 
     */
    public static int dateBetween(Date startDate, Date endDate) throws ParseException {
        Date dateStart = dateParse(dateFormat(startDate, DATE_PATTERN), DATE_PATTERN);
        Date dateEnd = dateParse(dateFormat(endDate, DATE_PATTERN), DATE_PATTERN);
        return (int) ((dateEnd.getTime() - dateStart.getTime())/1000/60/60/24); 
    }
    
    /**
     * 获取两个日期（不含时分秒）相差的天数，包含今天
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException 
     */
    public static int dateBetweenIncludeToday(Date startDate, Date endDate) throws ParseException {  
        return dateBetween(startDate, endDate) + 1;
    }
    
    /**
     * 获取日期时间的年份，如2017-02-13，返回2017
     * @param date
     * @return
     */
    public static int getYear(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
    
    /**
     * 获取日期时间的月份，如2017年2月13日，返回2
     * @param date
     * @return
     */
    public static int getMonth(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }
    
    /**
     * 获取日期时间的第几天（即返回日期的dd），如2017-02-13，返回13
     * @param date
     * @return
     */
    public static int getDate(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }
    
    /**
     * 获取日期时间当月的总天数，如2017-02-13，返回28
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }
    
    /**
     * 获取日期时间当年的总天数，如2017-02-13，返回2017年的总天数
     * @param date
     * @return
     */
    public static int getDaysOfYear(Date date) {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }
    
    /**
     * 根据时间获取当月最大的日期
     * <li>2017-02-13，返回2017-02-28</li>
     * <li>2016-02-13，返回2016-02-29</li>
     * <li>2016-01-11，返回2016-01-31</li>
     * @param date Date
     * @return
     * @throws Exception 
     */
    public static Date maxDateOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }
    
    /**
     * 获取固定间隔时刻集合
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param interval 时间间隔(单位：分钟)
     * @return
     * @throws ParseException 
     */
    public static List<String> getIntervalTimeList(String startDate,String endDate,int interval) throws ParseException{
    	Date d1 = TimeUtils.dateParse(startDate, TimeUtils.DATE_TIME_PATTERN);
    	Date d2 = TimeUtils.dateParse(endDate, TimeUtils.DATE_TIME_PATTERN);
        List<String> list = new ArrayList<>();
        while(d1.getTime()<=d2.getTime()){
           String dd = TimeUtils.dateFormat(d1, TimeUtils.DATE_TIME_PATTERN);
           list.add(dd);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(d1);
            calendar.add(Calendar.MINUTE,interval);
            if(calendar.getTime().getTime()>d2.getTime()){
                if(!startDate.equals(endDate)){
                   list.add(endDate);
                }
                d1 = calendar.getTime();
            }else{
                d1 = calendar.getTime();
            }
 
        }
        return list;
    }
    
    /**
     * 根据时间获取当月最小的日期，也就是返回当月的1号日期对象
     * @param date Date
     * @return
     * @throws Exception 
     */
    public static Date minDateOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }
    
    public static void main(String[] args) throws Exception {
//    	String aa = TimeUtils.dateTimeToDateString(TimeUtils.dateAddDays(new Date(),-1))+" 00:00:00";
//    	System.out.println("11===="+aa);
//    	Date d1 = TimeUtils.dateParse("2018-08-25 08:11:55", TimeUtils.DATE_TIME_PATTERN);
//    	Date d2 = TimeUtils.dateParse("2018-08-25 09:55:55", TimeUtils.DATE_TIME_PATTERN);
//    	String dd1 = "2018-08-25 08:11:55";
//    	String dd2 = "2018-08-25 09:55:55";
//    	List<String> dlist = getIntervalTimeList(dd1,dd2,30);
//    	for(String d : dlist)
//    	System.out.println(d+"==="+dlist.size());
        /*System.out.println(dateTimeToDate(new Date()));
        System.out.println(dateParse("2017-02-04 14:58:20", null));
        System.out.println(dateTimeToDateStringIfTimeEndZero(new Date()));
        System.out.println(dateTimeToDateStringIfTimeEndZero(dateTimeToDate(new Date())));*/
        //System.out.println(dateBetween(dateParse("2017-01-30", null), dateParse("2017-02-01", null)));
        //System.out.println(dateBetweenIncludeToday(dateParse("2017-01-30", null), dateParse("2017-02-01", null)));
//        System.out.println(getDate(dateParse("2017-01-17", null)));
        /*
        System.out.println(getDaysOfMonth(dateParse("2017-02-01", null)));
        System.out.println(getDaysOfYear(dateParse("2017-01-30", null)));*/
        //System.out.println(dateFormat(dateAddMonths(dateParse("2017-02-07", StrUtils.MONTH_PATTERN), -12), StrUtils.MONTH_PATTERN));
        /*System.out.println(dateFormat(maxDateOfMonth(dateParse("2016-02", "yyyy-MM")), null));
        System.out.println(dateFormat(minDateOfMonth(dateParse("2016-03-31", null)), null));*/
    }
    
    
}