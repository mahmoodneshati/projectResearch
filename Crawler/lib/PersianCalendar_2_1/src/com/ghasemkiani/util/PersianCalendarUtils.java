/*
	PersianCalendarUtils.java
	2005-02-20 05:56:36
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

package com.ghasemkiani.util;

/**
	This class contains some utility functions and constants used by other 
	Persian Calendar classes.
	
	@author <a href="mailto:ghasemkiani@yahoo.com">Ghasem Kiani</a>
	@version 2.1
*/
public class PersianCalendarUtils
{
	/**
		Julian day corresponding to 1 Farvardin 1 A.H., corresponding to 
		March 19, 622 A.D. by the Julian version of the Gregorian calendar.
	*/
	public static final long EPOCH = 1948321;
	/**
		A modulo function suitable for our purpose.
		
		@param a the dividend.
		@param b the divisor.
		@return the remainder of integer division.
	*/
	public static long mod(double a, double b)
	{
		return (long)(a - b * Math.floor(a / b));
	}
	/**
		An integer division function suitable for our purpose.
		
		@param a the dividend.
		@param b the divisor.
		@return the quotient of integer division.
	*/
	public static long div(double a, double b)
	{
		return (long)Math.floor(a / b);
	}
	/**
		Extracts the year from a packed long value.
		
		@param r the packed long value.
		@return the year part of date.
	*/
	public static long y(long r)
	{
		return r >> 16;
	}
	/**
		Extracts the month from a packed long value.
		
		@param r the packed long value
.
		@return the month part of date.
	*/
	public static int m(long r)
	{
		return (int)(r & 0xff00) >> 8;
	}
	/**
		Extracts the day from a packed long value.
		
		@param r the packed long value.
		@return the day part of date.
	*/
	public static int d(long r)
	{
		return (int)(r & 0xff);
	}
}
