/*
	PersianDateFormatSymbols.java
	2005-01-11 18:16:26
	Copyright © Ghasem Kiani <ghasemkiani@yahoo.com>
	
	This program is free software; you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation; either version 2 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with this program; if not, write to the Free Software
	Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/

package com.ghasemkiani.util.icu;

import java.util.*;

import com.ibm.icu.text.DateFormatSymbols;
import com.ibm.icu.util.ULocale;

/**
	This class uses a resource bundle to extract localized names of the Persian 
	calendar eras and months.
	
	@author <a href="mailto:ghasemkiani@yahoo.com">Ghasem Kiani</a>
	@version 2.1
*/
public class PersianDateFormatSymbols extends DateFormatSymbols
{
	private static final String BUNDLE = "com.ghasemkiani.util.icu.Resources";
	/**
		Creates a <code>PersianDateFormatSymbols</code> for the default locale.
	*/
	public PersianDateFormatSymbols()
	{
		this(Locale.getDefault());
	}
	/**
		Creates a <code>PersianDateFormatSymbols</code> for the specified locale.
		
		@param uLocale the provided locale for this object.
	*/
	public PersianDateFormatSymbols(ULocale uLocale)
	{
		this(uLocale.toLocale());
	}
	/**
		Creates a <code>PersianDateFormatSymbols</code> for the specified locale.
		
		@param locale the provided locale for this object.
	*/
	public PersianDateFormatSymbols(Locale locale)
	{
		super(locale);
		initializePersianData(locale);
	}
	protected void initializePersianData(Locale locale)
	{
		ResourceBundle rb = ResourceBundle.getBundle(BUNDLE, locale);
		setEras(new String[]
			{
				rb.getString("persianCalendar.era0"),
				rb.getString("persianCalendar.era1"),
			}
		);
		setMonths(new String[]
			{
				rb.getString("persianCalendar.month00"),
				rb.getString("persianCalendar.month01"),
				rb.getString("persianCalendar.month02"),
				rb.getString("persianCalendar.month03"),
				rb.getString("persianCalendar.month04"),
				rb.getString("persianCalendar.month05"),
				rb.getString("persianCalendar.month06"),
				rb.getString("persianCalendar.month07"),
				rb.getString("persianCalendar.month08"),
				rb.getString("persianCalendar.month09"),
				rb.getString("persianCalendar.month10"),
				rb.getString("persianCalendar.month11"),
			}
		);
		setShortMonths(new String[]
			{
				rb.getString("persianCalendar.monthShort00"),
				rb.getString("persianCalendar.monthShort01"),
				rb.getString("persianCalendar.monthShort02"),
				rb.getString("persianCalendar.monthShort03"),
				rb.getString("persianCalendar.monthShort04"),
				rb.getString("persianCalendar.monthShort05"),
				rb.getString("persianCalendar.monthShort06"),
				rb.getString("persianCalendar.monthShort07"),
				rb.getString("persianCalendar.monthShort08"),
				rb.getString("persianCalendar.monthShort09"),
				rb.getString("persianCalendar.monthShort10"),
				rb.getString("persianCalendar.monthShort11"),
			}
		);
	}
}
