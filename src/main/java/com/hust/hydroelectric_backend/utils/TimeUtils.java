package com.hust.hydroelectric_backend.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/10 17:08
 */
public class TimeUtils {

    public static final String FORAMT_STR_HH = "HH";
    public static final String FORAMT_STR_mm = "mm";
    public static final String FORAMT_STR_HH_mm = "HH:mm";
    public static final String FORAMT_STR_yyyyMM = "yyyyMM";
    public static final String FORAMT_STR_yyyyMMdd = "yyyyMMdd";
    public static final String FORAMT_STR_yyyyMMddHH = "yyyyMMddHH";
    public static final String FORAMT_STR_yyyyMMddHHmm = "yyyyMMddHHmm";
    public static final String FORAMT_STR_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String FORAMT_STR_HHmmss = "HHmmss";
    public static final String FORAMT_STR_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    public static final String FORAMT_STR_yyyy_MM_dd_kk_mm_ss_SSS = "yyyy-MM-dd kk:mm:ss.SSS";
    public static final String FORAMT_STR_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORAMT_STR_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String FORAMT_STR_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String FORAMT_STR_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String FORMAT_STR_MMdd = "MMdd";
    public static final int SLOT1 = 1;
    public static final int SLOT5 = 5;
    public static final int SLOT10 = 10;
    public static final int SLOT15 = 15;
    public static final int SLOT20 = 20;
    public static final int SLOT30 = 30;
    public static final int SLOT60 = 60;
    public static final int SECONDS_PER_MIN = 60;
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public TimeUtils() {
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String getFormatTime(SimpleDateFormat format) {
        return format.format(new Date());
    }

    public static String getFormatTimeByMillis(SimpleDateFormat format, long timeMillis) {
        return format.format(new Date(timeMillis));
    }

    public static String getFormatTimeByMillis(String format, long timeMillis) {
        return getFormatTimeByMillis(new SimpleDateFormat(format), timeMillis);
    }

    public static String getFormatTimeBySeconds(SimpleDateFormat format, long timeSeconds) {
        return getFormatTimeByMillis(format, timeSeconds * 1000L);
    }

    public static String getFormatTimeBySeconds(String format, long timeSeconds) {
        return getFormatTimeByMillis(format, timeSeconds * 1000L);
    }

    public static Date getSlotTime(int slot, Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(13, 0);
        calendar.set(14, 0);
        int[] slots = new int[]{1, 5, 10, 15, 20, 30, 60};
        if (ArrayUtils.contains(slots, slot)) {
            int minuteSlot = calendar.get(12) / slot * slot;
            calendar.set(12, minuteSlot);
            return calendar.getTime();
        } else {
            throw new IllegalArgumentException("Unsupported parameter slot" + slot);
        }
    }

    public static Long getUnixTimestamp(String timeStr) {
        return getUnixTimestamp(parseDateStr(timeStr));
    }

    public static Long getUnixTimestamp(Date time) {
        return time.getTime() / 1000L;
    }

    public static Long getUnixTimestamp(String format, String timeStr) {
        return getUnixTimestamp(parse(timeStr, format));
    }

    public static String formatDateTime(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDay(Date date) {
        return format(date, "yyyy-MM-dd");
    }

    public static String format(Date date, String dateFormat) {
        return date == null ? null : FastDateFormat.getInstance(dateFormat).format(date);
    }

    public static String getYesterday() {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,-24);
        String yesterdayDate=dateFormat.format(calendar.getTime());
        return yesterdayDate;
    }

    public static Date parse2Date(String date) {
        return parse(date, "yyyy-MM-dd");
    }

    public static Date parse2Time(String date) {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseDateStr(String time) {
        Pattern pattern = Pattern.compile("(([0-9]{4})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]))");
        Matcher matcher = pattern.matcher(time);
        if (matcher.matches()) {
            return parse(time, "yyyy-MM-dd");
        } else {
            pattern = Pattern.compile("(([0-9]{4})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01]))");
            matcher = pattern.matcher(time);
            if (matcher.matches()) {
                return parse(time, "yyyyMMdd");
            } else {
                pattern = Pattern.compile("(([0-9]{4})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])) ([01][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])");
                matcher = pattern.matcher(time);
                if (matcher.matches()) {
                    return parse(time, "yyyy-MM-dd HH:mm:ss");
                } else {
                    pattern = Pattern.compile("(([0-9]{4})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01]))([01][0-9]|2[0-3])([0-5][0-9])([0-5][0-9])");
                    matcher = pattern.matcher(time);
                    if (matcher.matches()) {
                        return parse(time, "yyyyMMddHHmmss");
                    } else {
                        pattern = Pattern.compile("(([0-9]{4})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01]))([01][0-9]|2[0-3])([0-5][0-9])");
                        matcher = pattern.matcher(time);
                        if (matcher.matches()) {
                            return parse(time, "yyyyMMddHHmm");
                        } else {
                            pattern = Pattern.compile("(([0-9]{4})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01]))([01][0-9]|2[0-3])");
                            matcher = pattern.matcher(time);
                            return matcher.matches() ? parse(time, "yyyyMMddHH") : null;
                        }
                    }
                }
            }
        }
    }

    public static Date parse(String dateString, String dateFormat) {
        try {
            return FastDateFormat.getInstance(dateFormat).parse(dateString);
        } catch (ParseException var3) {
            throw new IllegalArgumentException("cannot use dateformat:" + dateFormat + " parse datestring:" + dateString, var3);
        }
    }

    public static String getDateAfter(int amount) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.add(6, amount);
        return FastDateFormat.getInstance("yyyyMMdd").format(now.getTime());
    }

    public static Date addMinutes(Date date, int amount) {
        Calendar calendar = getCalendar(date);
        calendar.add(12, amount);
        return calendar.getTime();
    }

    public static Date addMinutes(String date, int amount) {
        return addMinutes(parse2Time(date), amount);
    }

    public static Date addDays(Date date, int amount) {
        Calendar calendar = getCalendar(date);
        calendar.add(6, amount);
        return calendar.getTime();
    }

    public static Date addDays(String date, int amount) {
        return addDays(parse2Date(date), amount);
    }

    public static String monthTime() {
        return FastDateFormat.getInstance("yyyyMM").format(new Date());
    }

    public static String dayTime() {
        return FastDateFormat.getInstance("yyyyMMdd").format(new Date());
    }

    public static String dayTime(long timeMillis) {
        return FastDateFormat.getInstance("yyyyMMdd").format(new Date(timeMillis));
    }

    public static String hourTime() {
        return FastDateFormat.getInstance("yyyyMMddHH").format(new Date());
    }

    public static String minuteTime() {
        return FastDateFormat.getInstance("yyyyMMddHHmm").format(new Date());
    }

    public static String minuteTime(long timeMillis) {
        return FastDateFormat.getInstance("yyyyMMddHHmm").format(new Date(timeMillis));
    }

    public static String secondTime() {
        return FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date());
    }

    public static String millisecondTime() {
        return FastDateFormat.getInstance("yyyyMMddHHmmssSSS").format(new Date());
    }

    public static long curTimeMinStamp() {
        return System.currentTimeMillis() / 60000L;
    }

    public static long curTimeSecStamp() {
        return System.currentTimeMillis() / 1000L;
    }

    public static String getDate(long millis) {
        Date date = new Date();
        if (millis > 0L) {
            date.setTime(millis);
        }

        return formatDay(date);
    }

    public static int dayDiff(Date start, Date end) {
        Calendar startCalendar = getCalendar(start);
        Calendar endCalendar = getCalendar(end);
        startCalendar.set(11, 0);
        startCalendar.set(12, 0);
        startCalendar.set(13, 0);
        startCalendar.set(14, 0);
        endCalendar.set(11, 0);
        endCalendar.set(12, 0);
        endCalendar.set(13, 0);
        endCalendar.set(14, 0);
        long millSecDiff = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();
        int dayDiff = Math.round((float)(millSecDiff / 86400000L));
        return dayDiff;
    }

    public static int getYear(Date date) {
        return getCalendar(date).get(1);
    }

    public static int getDayOfYear(Date date) {
        Calendar calendar = getCalendar(date);
        return calendar.get(6);
    }

    public static boolean isFirstDayOfMonth(Date date) {
        if (date == null) {
            throw new RuntimeException("Date must be not null");
        } else {
            Calendar cal = getCalendar(date);
            int dayIndex = cal.get(5);
            return dayIndex == 1;
        }
    }

    public static int getMonthLastDay(Date date) {
        Calendar cal = getCalendar(date);
        return cal.getActualMaximum(5);
    }

    public static Date getFirstDayOfLastMonth(Date date) {
        if (date == null) {
            throw new RuntimeException("Date must be not null");
        } else {
            Calendar cal = getCalendar(date);
            cal.add(2, -1);
            cal.set(5, 1);
            return cal.getTime();
        }
    }

    public static long getHourNum(Date date) {
        return date.getTime() / 3600000L;
    }

    public static long getNowHourNum() {
        return System.currentTimeMillis() / 3600000L;
    }

    public static long getAddHourNum(int amount) {
        return getNowHourNum() + (long)amount;
    }

    public static long getNowMinuteMun() {
        return System.currentTimeMillis() / 60000L;
    }

    public static Date[] addDaysForArray(Date date, int... amount) {
        List<Date> result = new ArrayList();

        for(int i = 0; i < amount.length; ++i) {
            Calendar c = getCalendar(date);
            c.add(6, amount[i]);
            result.add(c.getTime());
        }

        return (Date[])result.toArray(new Date[0]);
    }

    public static boolean isEndOfMonth(Date date) {
        return getByField(DateUtils.addDays(date, 1), 5) == 1;
    }

    public static boolean isEndOfWeek(Date date) {
        return getByField(date, 7) == 1;
    }

    public static int getByField(Date date, int field) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(field);
    }

    public static int[] parseDatetimeToArray(String datetime) {
        int year = Integer.parseInt(datetime.substring(0, 4));
        int month = Integer.parseInt(datetime.substring(5, 7));
        int day = Integer.parseInt(datetime.substring(8, 10));
        return new int[]{year, month, day};
    }

    public static Date extract(Date date, String datePattern) {
        return !StringUtils.isBlank(datePattern) && date != null ? parse(format(date, datePattern), datePattern) : null;
    }

    public static Date parseDate(long unixTimestamp) {
        return new Date(unixTimestamp * 1000L);
    }

    public static long curMinSecStamp() {
        long seconds = curTimeSecStamp();
        return seconds - seconds % 60L;
    }

    public static long minSecStamp(Date date) {
        long seconds = getUnixTimestamp(date);
        return seconds - seconds % 60L;
    }
}
