/*
	PersianDateFormat.java
	2005-01-11 23:08:03
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

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ghasemkiani.util.icu.PersianDateFormatSymbols;

/**
	This class is used for formatting dates in the Persian calendar system. 
	It uses the <code>PersianDateFormatSymbols</code> class for localized 
	names of Persian calendar eras and months.
	
	@author <a href="mailto:ghasemkiani@yahoo.com">Ghasem Kiani</a>
	@version 2.1
*/
public class PersianDateFormat extends SimpleDateFormat
{
	/**
		Creates a <code>PersianDateFormat</code> with the default pattern and locale.
	*/
	public PersianDateFormat()
	{
        this("yyyy/MM/dd G HH:mm:ss z", ULocale.getDefault());
	}
	/**
		Creates a <code>PersianDateFormat</code> with the specified pattern and the 
		default locale.
		
		@param pattern the pattern to be used by this object.
	*/
	public PersianDateFormat(String pattern)
	{
        this(pattern, ULocale.getDefault());
	}
	/**
		Creates a <code>PersianDateFormat</code> with the specified pattern and locale.
		
		@param pattern the pattern to be used by this object.
		@param loc the provided locale for this object.
	*/
	public PersianDateFormat(String pattern, Locale loc)
	{
        this(pattern, ULocale.forLocale(loc));
	}
	/**
		Creates a <code>PersianDateFormat</code> with the specified pattern and locale.
		
		@param pattern the pattern to be used by this object.
		@param loc the provided locale for this object.
	*/
	public PersianDateFormat(String pattern, ULocale loc)
	{
		super(pattern, loc);
		setDateFormatSymbols(new PersianDateFormatSymbols(loc));
		// Should we check if the locale is for Iran or Afghanistan?
		// Anyway, I don't think this class should be used for any other calendar type.
		setCalendar(new PersianCalendar(loc));
	}
}
