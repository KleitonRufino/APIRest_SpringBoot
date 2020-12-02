package br.com.apirest_springboot.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URLUtil {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}

	public static Date convertDate(String textDate, Date defaulValue) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone((TimeZone.getTimeZone("GMT")));
		try {
			return format.parse(textDate);
		} catch (ParseException e) {

		}
		return defaulValue;
	}

}
