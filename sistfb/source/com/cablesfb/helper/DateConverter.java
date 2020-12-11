package com.cablesfb.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	public static String toMysqlDateStr(Date date) {
		String dateForMySql = "";
		if (date == null) {
		    dateForMySql = null;
		} else {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    dateForMySql = sdf.format(date);
		}

		return dateForMySql;
		}
	public static java.sql.Date convertFromJAVADateToSQLDate(
	        java.util.Date javaDate) {
	    java.sql.Date sqlDate = null;
	    if (javaDate != null) {
	        sqlDate = new java.sql.Date(javaDate.getTime());
	    }
	    return sqlDate;
	}
	
}
