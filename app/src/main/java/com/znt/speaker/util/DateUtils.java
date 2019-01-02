
package com.znt.speaker.util; 

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.text.TextUtils;

/** 
 * @ClassName: MyDateUtils 
 * @Description: TODO
 * @author yan.yu 
 * @date 2014-3-24 涓嬪崍2:50:46  
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils
{
	
	/**
	  * 鑾峰彇鐜板湪鏃堕棿
	  * 
	  * @return 杩斿洖鏃堕棿绫诲瀷 yyyy-MM-dd HH:mm:ss
	  */
	public static Date getNowDate() 
	{
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
	   String dateString = formatter.format(currentTime);
	   ParsePosition pos = new ParsePosition(8);
	   Date currentTime_2 = formatter.parse(dateString, pos);
	   return currentTime_2;
	}

	/**
	  * 鑾峰彇鐜板湪鏃堕棿
	  * 
	  * @return杩斿洖鐭椂闂存牸寮� yyyy-MM-dd
	  */
	public static Date getNowDateShort() 
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	  * 鑾峰彇鐜板湪鏃堕棿
	  * 
	  * @return杩斿洖瀛楃涓叉牸寮� yyyy-MM-dd HH:mm:ss
	  */
	public static String getStringDate() 
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	  * 鑾峰彇鐜板湪鏃堕棿
	  * 
	  * @return 杩斿洖鐭椂闂村瓧绗︿覆鏍煎紡yyyy-MM-dd
	  */
	public static String getStringDateShort() 
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	  * 鑾峰彇鏃堕棿 灏忔椂:鍒�;绉� HH:mm:ss
	  * 
	  * @return
	  */
	public static String getTimeShort()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	 * 鑾峰彇鏃堕棿 灏忔椂:鍒�;绉� HH:mm
	 * 
	 * @return
	 */
	public static String getTimeShortHead()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	  * 灏嗛暱鏃堕棿鏍煎紡瀛楃涓茶浆鎹负鏃堕棿 yyyy-MM-dd HH:mm:ss
	  * 
	  * @param strDate
	  * @return
	  */
	public static Date strToDateLong(String strDate) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**  * 灏嗛暱鏃堕棿鏍煎紡鏃堕棿杞崲涓哄瓧绗︿覆 yyyy-MM-dd HH:mm:ss  *   * @param dateDate  * @return  */
	public static String dateToStrLong(Date dateDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	  * 灏嗙煭鏃堕棿鏍煎紡鏃堕棿杞崲涓哄瓧绗︿覆 yyyy-MM-dd
	  * 
	  * @param dateDate
	  * @param k
	  * @return
	  */
	public static String dateToStr(Date dateDate)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	  * 灏嗙煭鏃堕棿鏍煎紡瀛楃涓茶浆鎹负鏃堕棿 yyyy-MM-dd 
	  * 
	  * @param strDate
	  * @return
	  */
	public static Date strToDate(String strDate) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	  * 寰楀埌鐜板湪鏃堕棿
	  * 
	  * @return
	  */
	public static Date getNow() 
	{
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	  * 鎻愬彇涓�涓湀涓殑鏈�鍚庝竴澶�
	  * 
	  * @param day
	  * @return
	  */
	public static Date getLastDate(long day) 
	{
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	  * 寰楀埌鐜板湪鏃堕棿
	  * 
	  * @return 瀛楃涓� yyyyMMdd HHmmss
	  */
	public static String getStringToday() 
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	public static String getStringToday(String format) 
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	public static String getStringTime(long time) 
	{
		Date currentTime = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	public static String getStringTimeHead(long time) 
	{
		Date currentTime = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		return dateString;
	}
	/**
	  * 寰楀埌鐜板湪灏忔椂
	  */
	public static String getHour() 
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	  * 寰楀埌鐜板湪鍒嗛挓
	  * 
	  * @return
	  */
	public static String getTime() 
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	  * 鏍规嵁鐢ㄦ埛浼犲叆鐨勬椂闂磋〃绀烘牸寮忥紝杩斿洖褰撳墠鏃堕棿鐨勬牸寮� 濡傛灉鏄痽yyyMMdd锛屾敞鎰忓瓧姣峺涓嶈兘澶у啓銆�
	  * 
	  * @param sformat
	  *            yyyyMMddhhmmss
	  * @return
	  */
	public static String getUserDate(String sformat) 
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	  * 浜屼釜灏忔椂鏃堕棿闂寸殑宸��,蹇呴』淇濊瘉浜屼釜鏃堕棿閮芥槸"HH:MM"鐨勬牸寮忥紝杩斿洖瀛楃鍨嬬殑鍒嗛挓
	  */
	public static String getTwoHour(String st1, String st2) 
	{
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else 
		{
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	  * 寰楀埌浜屼釜鏃ユ湡闂寸殑闂撮殧澶╂暟
	  */
	public static String getTwoDay(String sj1, String sj2) 
	{
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		myFormatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		long day = 0;
		try 
		{
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} 
		catch (Exception e) 
		{
			return "";
		}
		return day + "";
	}

	/**
	  * 鏃堕棿鍓嶆帹鎴栧悗鎺ㄥ垎閽�,鍏朵腑JJ琛ㄧず鍒嗛挓.
	  */
	public static String getPreTime(String sj1, String jj) 
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String mydate1 = "";
		try 
		{
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} 
		catch (Exception e) 
		{
	  
		}
		return mydate1;
	}

	/**
	  * 寰楀埌涓�涓椂闂村欢鍚庢垨鍓嶇Щ鍑犲ぉ鐨勬椂闂�,nowdate涓烘椂闂�,delay涓哄墠绉绘垨鍚庡欢鐨勫ぉ鏁�
	  */
	public static String getNextDay(String nowdate, String delay)
	{
		try
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		}
		catch(Exception e)
		{
			return "";
		}
	}

	/**
	  * 鍒ゆ柇鏄惁娑﹀勾
	  * 
	  * @param ddate
	  * @return
	  */
	public static boolean isLeapYear(String ddate) 
	{
		/**
	   * 璇︾粏璁捐锛� 1.琚�400鏁撮櫎鏄棸骞达紝鍚﹀垯锛� 2.涓嶈兘琚�4鏁撮櫎鍒欎笉鏄棸骞� 3.鑳借4鏁撮櫎鍚屾椂涓嶈兘琚�100鏁撮櫎鍒欐槸闂板勾
	   * 3.鑳借4鏁撮櫎鍚屾椂鑳借100鏁撮櫎鍒欎笉鏄棸骞�
	   */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) 
		{
			if ((year % 100) == 0)
				return false;
			else
				return true;
		}
		else
			return false;
	}

	/**
	  * 杩斿洖缇庡浗鏃堕棿鏍煎紡 26 Apr 2006
	  * 
	  * @param str
	  * @return
	  */
	public static String getEDate(String str) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	/**
	  * 鑾峰彇涓�涓湀鐨勬渶鍚庝竴澶�
	  * 
	  * @param dat
	  * @return
	  */
	public static String getEndDateOfMonth(String dat) 
	{
		// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) 
		{
			str += "31";
		} 
		else if (mon == 4 || mon == 6 || mon == 9 || mon == 11)
		{
			str += "30";
		} 
		else 
		{
			if (isLeapYear(dat)) 
			{
				str += "29";
			} 
			else 
			{
				str += "28";
			}
		}
		return str;
	}

	/**
	  * 鍒ゆ柇浜屼釜鏃堕棿鏄惁鍦ㄥ悓涓�涓懆
	  * 
	  * @param date1
	  * @param date2
	  * @return
	  */
	public static boolean isSameWeekDates(Date date1, Date date2) 
	{
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) 
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} 
		else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) 
		{
			// 濡傛灉12鏈堢殑鏈�鍚庝竴鍛ㄦí璺ㄦ潵骞寸涓�鍛ㄧ殑璇濆垯鏈�鍚庝竴鍛ㄥ嵆绠楀仛鏉ュ勾鐨勭涓�鍛�
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) 
		{
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	  * 浜х敓鍛ㄥ簭鍒�,鍗冲緱鍒板綋鍓嶆椂闂存墍鍦ㄧ殑骞村害鏄鍑犲懆
	  * 
	  * @return
	  */
	public static String getSeqWeek()
	{
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	  * 鑾峰緱涓�涓棩鏈熸墍鍦ㄧ殑鍛ㄧ殑鏄熸湡鍑犵殑鏃ユ湡锛屽瑕佹壘鍑�2002骞�2鏈�3鏃ユ墍鍦ㄥ懆鐨勬槦鏈熶竴鏄嚑鍙�
	  * 
	  * @param sdate
	  * @param num
	  * @return
	  */
	public static String getWeek(String sdate, String num) 
	{
		// 鍐嶈浆鎹负鏃堕棿
		Date dd = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 杩斿洖鏄熸湡涓�鎵�鍦ㄧ殑鏃ユ湡
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 杩斿洖鏄熸湡浜屾墍鍦ㄧ殑鏃ユ湡
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 杩斿洖鏄熸湡涓夋墍鍦ㄧ殑鏃ユ湡
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 杩斿洖鏄熸湡鍥涙墍鍦ㄧ殑鏃ユ湡
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 杩斿洖鏄熸湡浜旀墍鍦ㄧ殑鏃ユ湡
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 杩斿洖鏄熸湡鍏墍鍦ㄧ殑鏃ユ湡
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 杩斿洖鏄熸湡鏃ユ墍鍦ㄧ殑鏃ユ湡
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	  * 鏍规嵁涓�涓棩鏈燂紝杩斿洖鏄槦鏈熷嚑鐨勫瓧绗︿覆
	  * 
	  * @param sdate
	  * @return
	  */
	public static String getWeek(String sdate) 
	{
		// 鍐嶈浆鎹负鏃堕棿
		Date date = strToDate(sdate);
		int week = date.getDay();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour涓瓨鐨勫氨鏄槦鏈熷嚑浜嗭紝鍏惰寖鍥� 1~7
		// 1=鏄熸湡鏃� 7=鏄熸湡鍏紝鍏朵粬绫绘帹
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}
	public static String getWeekStr(String sdate)
	{
		String str = "";
		str = getWeek(sdate);
		if("1".equals(str))
		{
			str = "鏄熸湡鏃�";
		}
		else if("2".equals(str))
		{
			str = "鏄熸湡涓�";
		}
		else if("3".equals(str))
		{
			str = "鏄熸湡浜�";
		}
		else if("4".equals(str))
		{
			str = "鏄熸湡涓�";
		}
		else if("5".equals(str))
		{
			str = "鏄熸湡鍥�";
		}
		else if("6".equals(str))
		{
			str = "鏄熸湡浜�";
		}
		else if("7".equals(str))
		{
			str = "鏄熸湡鍏�";
		}
		return str;
	}

	/**
	  * 涓や釜鏃堕棿涔嬮棿鐨勫ぉ鏁�
	  * 
	  * @param date1
	  * @param date2
	  * @return
	  */
	public static long getDays(String date1, String date2)
	{
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 杞崲涓烘爣鍑嗘椂闂�
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		myFormatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		Date date = null;
		Date mydate = null;
		try 
		{
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} 
		catch (Exception e) 
		{
			return 0;
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	  * 褰㈡垚濡備笅鐨勬棩鍘� 锛� 鏍规嵁浼犲叆鐨勪竴涓椂闂磋繑鍥炰竴涓粨鏋� 鏄熸湡鏃� 鏄熸湡涓� 鏄熸湡浜� 鏄熸湡涓� 鏄熸湡鍥� 鏄熸湡浜� 鏄熸湡鍏� 涓嬮潰鏄綋鏈堢殑鍚勪釜鏃堕棿
	  * 姝ゅ嚱鏁拌繑鍥炶鏃ュ巻绗竴琛屾槦鏈熸棩鎵�鍦ㄧ殑鏃ユ湡
	  * 
	  * @param sdate
	  * @return
	  */
	public static String getNowMonth(String sdate)
	{
		// 鍙栬鏃堕棿鎵�鍦ㄦ湀鐨勪竴鍙�
		sdate = sdate.substring(0, 8) + "01";
		// 寰楀埌杩欎釜鏈堢殑1鍙锋槸鏄熸湡鍑�
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = getNextDay(sdate, (1 - u) + "");
		return newday;
	}

	/**
	  * 鍙栧緱鏁版嵁搴撲富閿� 鐢熸垚鏍煎紡涓簓yyymmddhhmmss+k浣嶉殢鏈烘暟
	  * @param k琛ㄧず鏄彇鍑犱綅闅忔満鏁帮紝鍙互鑷繁瀹�
	  */

	public static String getNo(int k) 
	{
		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	  * 杩斿洖涓�涓殢鏈烘暟
	  * 
	  * @param i
	  * @return
	  */
	public static String getRandom(int i) 
	{
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) 
		{
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}
	
	
	/** 
     * 鑾峰彇褰撳墠鏃堕棿闂撮殧锛屾寜鐓� XX浠ュ墠 鏍煎紡鏄剧ず   
     * String s = DateUtils.getTimeDisplay("2014-10-23T15:35:00+0800", this);
     * @param getDateString 
     * @return 
     */  
    public static String getTimeDisplay(long time) 
    {  
  
        final long currTime = System.currentTimeMillis();  
        final Date formatSysDate = new Date(currTime);  
  
        // 鍒ゆ柇褰撳墠鎬诲ぉ鏁�  
        final int sysMonth = formatSysDate.getMonth() + 1;  
        final int sysYear = formatSysDate.getYear();  
  
        // 璁＄畻鏈嶅姟鍣ㄨ繑鍥炴椂闂翠笌褰撳墠鏃堕棿宸��  
        final long seconds = (currTime - time) / 1000;  
        final long minute = seconds / 60;  
        final long hours = minute / 60;  
        final long day = hours / 24;  
        final long month = day / calculationDaysOfMonth(sysYear, sysMonth);  
        final long year = month / 12;  
  
        if (year > 0 || month > 0 || day > 0) 
        {  
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(  
                    "yyyy-MM-dd HH:mm");  
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
            return simpleDateFormat.format(new Date(time));  
        }
        else if (hours > 0) 
        {  
            return hours + "灏忔椂鍓�";  
        } else if (minute > 0) 
        {  
            return minute + "鍒嗛挓鍓�";  
        } 
        else if (seconds > 0) 
        {  
            return "1" + "鍒嗛挓鍓�";  
            // return seconds + context.getString(R.string.str_secondago);  
        } 
        else 
        {  
//          return "1" + context.getString(R.string.str_secondago);  
            return "1" + "鍒嗛挓鍓�"; //閮芥崲鎴愬垎閽熷墠  
        }  
    }  
      
      
    private static SimpleDateFormat getFormat(String partten) 
    {  
        return new SimpleDateFormat(partten);  
    }  
      
    /** 
     * 璁＄畻鏈堟暟 
     *  
     * @return 
     */  
    private static int calculationDaysOfMonth(int year, int month) 
    {  
        int day = 0;  
        switch (month) 
        {  
        // 31澶�  
        case 1:  
        case 3:  
        case 5:  
        case 7:  
        case 8:  
        case 10:  
        case 12:  
            day = 31;  
            break;  
        // 30澶�  
        case 4:  
        case 6:  
        case 9:  
        case 11:  
            day = 30;  
            break;  
        // 璁＄畻2鏈堝ぉ鏁�  
        case 2:  
            day = year % 100 == 0 ? year % 400 == 0 ? 29 : 28  
                    : year % 4 == 0 ? 29 : 28;  
            break;  
        }  
  
        return day;  
    }  
    
    public static String getDateFromLong(long time)
    {
    	Date currentTime = new Date(time);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
		String dateString = formatter.format(currentTime);
		return dateString;
    }
    public static String getEndDateFromLong(long time)
    {
    	Date currentTime = new Date(time);
    	SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    	formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));//如果不指定时区，在有些机器上会出现时间误差。 
    	String dateString = formatter.format(currentTime);
    	return dateString;
    }
    
    public static String getTimeFromLong(long time)
    {
    	String result = "";
    	
    	int day = (int) (time / (1000 * 60 * 60 * 24));
    	time = (int) (time % (1000 * 60 * 60 * 24));
    	if(day > 0)
    		result += day + "澶� ";
    	
    	int hour = (int) (time / (1000 * 60 * 60));
    	time = (int) (time % (1000 * 60 * 60));
    	result += hour + "灏忔椂 ";
    	
    	int min = (int) (time / (1000 * 60));
    	time = (int) (time % (1000 * 60));
    	result += min + "鍒嗛挓 ";
    	
    	int sec = (int) (time / (1000));
    	result += sec + "绉�";
    	
    	return result;
    }
    public static String getDeliverTime(long time)
    {
    	String result = "";
    	
    	int day = (int) (time / (1000 * 60 * 60 * 24));
    	time = (int) (time % (1000 * 60 * 60 * 24));
    	if(day > 0)
    		result += day + "澶� ";
    	
    	int hour = (int) (time / (1000 * 60 * 60));
    	time = (int) (time % (1000 * 60 * 60));
    	if(hour > 0)
    		result += hour + "灏忔椂 ";
    	
    	int min = (int) (time / (1000 * 60));
    	time = (int) (time % (1000 * 60));
    	result += min + "鍒嗛挓 ";
    	
    	/*int sec = (int) (time / (1000));
    	result += sec + "绉�";*/
    	
    	return result;
    }
    
    public static long timeToInt(String time, String tag)
    {
    	/*String s="2014-06-09 10:44:39";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d2=null;
        try 
        {
			d2 = sdf.parse(s);
			return d2.getTime();
		} 
        catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//灏哠tring to Date绫诲瀷
        return 0;*/
    	int timeLong = 0;
    	String[] strs = TextUtils.split(time, tag);
    	int hour = 0;
    	int min = 0;
    	int sec = 0;
    	if(strs.length > 0)
    	{
    		String tHour = strs[0];
    		if(!TextUtils.isEmpty(tHour))
    			hour = Integer.parseInt(tHour);
    		String tMin = strs[1];
    		if(!TextUtils.isEmpty(tMin))
    			min = Integer.parseInt(tMin);
    		if(strs.length > 1)
    		{
    			String tSec = strs[2];
    			if(!TextUtils.isEmpty(tSec))
    				sec = Integer.parseInt(tSec);
    		}
    	}
    	timeLong = hour * 60 * 60 + min * 60 + sec;
    	return timeLong;
    }
}
 
