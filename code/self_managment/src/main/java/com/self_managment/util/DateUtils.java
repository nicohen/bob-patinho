package com.self_managment.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * Return a date with the first time of the month
     * 
     * @param month
     * @param year
     * @return java.util.Date
     * @throws IllegalArgumentException -
     *                 if month or year is wrong
     */
    public static Date getFirstDay(int month, int year) {
	Date date = new Date();

	date = org.apache.commons.lang.time.DateUtils
		.setMonths(date, month - 1);
	date = org.apache.commons.lang.time.DateUtils.setYears(date, year);
	date = org.apache.commons.lang.time.DateUtils.truncate(date,
		Calendar.MONTH);

	return date;
    }

    /**
     * Return a date with the first time of the month
     * 
     * @param month
     * @param year
     * @return java.util.Date
     * @throws IllegalArgumentException -
     *                 if date is null
     */
    public static Date getFirstDay(Date date) {
	date = org.apache.commons.lang.time.DateUtils.truncate(date,
		Calendar.MONTH);

	return date;
    }
    
    /**
     * Return a date with the last time of the month
     * 
     * @param month
     * @param year
     * @return java.util.Date
     * @throws IllegalArgumentException -
     *                 if month or year is wrong
     */
    public static Date getLastDay(Date date) {
	date = org.apache.commons.lang.time.DateUtils.ceiling(date,
		Calendar.MONTH);
	date = org.apache.commons.lang.time.DateUtils.addMilliseconds(date, -1);

	return date;
    }

    /**
     * Return a date with the last time of the month
     * 
     * @param month
     * @param year
     * @return java.util.Date
     * @throws IllegalArgumentException -
     *                 if month or year is wrong
     */
    public static Date getLastDay(int month, int year) {
	Date date = new Date();
	date = org.apache.commons.lang.time.DateUtils
		.setMonths(date, month - 1);
	date = org.apache.commons.lang.time.DateUtils.setYears(date, year);
	date = org.apache.commons.lang.time.DateUtils.ceiling(date,
		Calendar.MONTH);
	date = org.apache.commons.lang.time.DateUtils.addMilliseconds(date, -1);

	return date;
    }

}
