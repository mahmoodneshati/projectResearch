/*
	SimplePersianCalendar.java
	2003-09-24 14:56:36
	Copyright © Ghasem Kiani <ghasemkiani@yahoo.com>
	
	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation; either version 2 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with this program; if not, write to the Free Software
	Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

/*
	HISTORY:
	Version 2.1 2005-03-18:
		A bug was corrected. Calculation of Julian days was being done on 
		the timezone-dependent time, not on the universal time. Now, this 
		is corrected.
	Version 2.0 2005-02-21:
		Some of the functionality was exported to other classes to be 
		usable by the com.ghasemkiani.util.icu.PersianCalendar class 
		which is based on IBM's ICU4J.
		Calculation algorithm was rewritten and some bugs were fixed. 
		Specifically, two functions for modulo and quotient were 
		introduced that solve some of the problems that arose in years 
		before 475 A.H.
		Added a new read/write property: julianDay.
		Characters ARABIC LETTER YEH and ARABIC LETTER KAF were replaced 
		by ARABIC LETTER FARSI YEH and ARABIC LETTER KEHEH, respectively, 
		in the Persian names of months and week days. This was done in 
		accordance with the CLDR locale for the Persian language, though I 
		don't like this personally, because the characters for Arabic and 
		Persian scripts had better be identical as much as possible. 
		So that we can use Cp1256, Internet searches in Persian are more 
		effective, and there is much less confusion in general.
		Changed Amordad to Mordad, since the former, though more correct, 
		is seldom used today.
	Version 1.3 2003-12-12:
		Added accessor get methods for "properties" persianMonthName
		and persianWeekDayName. Corrected some errors in the 
		documentation.
	Version 1.2 2003-11-17:
		Converted Persian literals to Unicode escape sequences.
	Version 1.1 2003-10-23:
		Added Persian names for months and days of the week.
		Added Javadoc documentation for the API.
	Version 1.0 2003-09-25:
		Started the project.
*/

package com.ghasemkiani.util;

import java.util.Date;
import java.util.GregorianCalendar;
import com.ghasemkiani.util.DateFields;
import com.ghasemkiani.util.PersianCalendarConstants;

import static com.ghasemkiani.util.PersianCalendarUtils.*;
import static com.ghasemkiani.util.PersianCalendarHelper.*;

/**
	This class is a subclass of <code>java.util.GregorianCalendar</code>, 
	with the added functionality that it can set/get date in the Persian 
	calendar system.
	
	The algorithms for conversion between Persian and Gregorian calendar systems  
	are placed in <code>{@link PersianCalendarHelper}</code> class.
	
	@author <a href="mailto:ghasemkiani@yahoo.com">Ghasem Kiani</a>
	@version 2.1
*/

public class SimplePersianCalendar extends GregorianCalendar implements PersianCalendarConstants
{
	private static String copyright = "Copyright \u00a9 2003-2005 Ghasem Kiani <ghasemkiani@yahoo.com>. All Rights Reserved.";
	// Julian day 0, 00:00:00 hours (midnight); milliseconds since 1970-01-01 00:00:00 UTC (Gregorian Calendar)
	private static final long JULIAN_EPOCH_MILLIS = -210866803200000L;
	private static final long ONE_DAY_MILLIS = 24L * 60L * 60L * 1000L;
	
	/**
		Get the Julian day corresponding to the date of this calendar.
		@since 2.0
		
		@return the Julian day corresponding to the date of this calendar.
	*/
	public long getJulianDay()
	{
		return div(getTimeInMillis() - JULIAN_EPOCH_MILLIS, ONE_DAY_MILLIS);
	}
	/**
		Set the date of this calendar to the specified Julian day.
		@since 2.0
		
		@param julianDay the desired Julian day to be set as the date of this calendar.
	*/
	public void setJulianDay(long julianDay)
	{
		setTimeInMillis(JULIAN_EPOCH_MILLIS + julianDay * ONE_DAY_MILLIS + mod(getTimeInMillis() - JULIAN_EPOCH_MILLIS, ONE_DAY_MILLIS));
	}
	
	/**
		Sets the date of this calendar object to the specified
		Persian date (year, month, and day fields)
		@since 1.0
		
		@param year the Persian year.
		@param month the Persian month (zero-based).
		@param day the Persian day of month.
	*/
	public void setDateFields(int year, int month, int day)
	{
		setDateFields(new DateFields(year, month, day));
	}
	/**
		Sets the date of this calendar object to the specified
		Persian date fields
		@since 1.0
		
		@param dateFields the Persian date fields.
	*/
	public void setDateFields(DateFields dateFields)
	{
		int y = dateFields.getYear();
		int m = dateFields.getMonth();
		int d = dateFields.getDay();
		setJulianDay(pj(y > 0? y: y + 1, m, d));
	}
	/**
		Retrieves the date of this calendar object as the
		Persian date fields
		@since 1.0
		
		@return the date of this calendar as Persian date fields.
	*/
	public DateFields getDateFields()
	{
		long julianDay = getJulianDay();
		long r = jp(julianDay);
		long y = y(r);
		int m = m(r);
		int d = d(r);
		return new DateFields((int)(y > 0? y: y - 1), (int)m, (int)d);
	}
	/**
		Persian month names.
		@since 1.1
	*/
	public static final String[] persianMonths =
	{
		"\u0641\u0631\u0648\u0631\u062f\u06cc\u0646",             // Farvardin
		"\u0627\u0631\u062f\u06cc\u200c\u0628\u0647\u0634\u062a", // Ordibehesht
		"\u062e\u0631\u062f\u0627\u062f",                         // Khordad
		"\u062a\u06cc\u0631",                                     // Tir
		"\u0645\u0631\u062f\u0627\u062f",                         // Mordad
		"\u0634\u0647\u0631\u06cc\u0648\u0631",                   // Shahrivar
		"\u0645\u0647\u0631",                                     // Mehr
		"\u0622\u0628\u0627\u0646",                               // Aban
		"\u0622\u0630\u0631",                                     // Azar
		"\u062f\u06cc",                                           // Dey
		"\u0628\u0647\u0645\u0646",                               // Bahman
		"\u0627\u0633\u0641\u0646\u062f"                          // Esfand
	};
	
	/**
		Persian week day names.
		@since 1.1
	*/
	public static final String[] persianWeekDays =
	{
		"\u0634\u0646\u0628\u0647",                         // shanbeh
		"\u06cc\u06a9\u200c\u0634\u0646\u0628\u0647",       // yek-shanbeh
		"\u062f\u0648\u0634\u0646\u0628\u0647",             // do-shanbeh
		"\u0633\u0647\u200c\u0634\u0646\u0628\u0647",       // seh-shanbeh
		"\u0686\u0647\u0627\u0631\u0634\u0646\u0628\u0647", // chahar-shanbeh
		"\u067e\u0646\u062c\u200c\u0634\u0646\u0628\u0647", // panj-shanbeh
		"\u062c\u0645\u0639\u0647"                          // jom'eh
	};
	/**
		Gives the name of the specified Persian month.
		@since 1.1
		
		@param month the Persian month (zero-based).
		@return the name of the specified Persian month in Persian.
	*/
	public static String getPersianMonthName(int month)
	{
		return persianMonths[month];
	}
	/**
		Gives the name of the current Persian month for this calendar's date.
		@since 1.3
		
		@return the name of the current Persian month for this calendar's date in Persian.
	*/
	public String getPersianMonthName()
	{
		return getPersianMonthName(getDateFields().getMonth());
	}
	/**
		Gives the Persian name of the specified day of week.
		@since 1.1
		
		@param weekDay the day of week (use symbolic constants in the <code>java.util.Calendar</code> class).
		@return the name of the specified day of week in Persian.
	*/
	public static String getPersianWeekDayName(int weekDay)
	{
		switch(weekDay)
		{
			case SATURDAY:  return persianWeekDays[0];
			case SUNDAY:    return persianWeekDays[1];
			case MONDAY:    return persianWeekDays[2];
			case TUESDAY:   return persianWeekDays[3];
			case WEDNESDAY: return persianWeekDays[4];
			case THURSDAY:  return persianWeekDays[5];
			case FRIDAY:    return persianWeekDays[6];
		}
		return "";
	}
	/**
		Gives the Persian name of the current day of the week for this 
		calendar's date.
		@since 1.3
		
		@return the name of the current day of week for this calendar's date in Persian.
	*/
	public String getPersianWeekDayName()
	{
		return getPersianWeekDayName(get(DAY_OF_WEEK));
	}


}
