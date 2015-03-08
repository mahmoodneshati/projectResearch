/*
	PersianCalendarHelper1.java
	2005-02-20 21:26:50
	Copyright � Ghasem Kiani <ghasemkiani@yahoo.com>
	
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
	<p>This class is an illustration of the algorithm used for calculation of leap 
	years in the arithmetic version of the Persian calendar system. This class 
	is not actually used by other classes, but it can poractically substitute 
	the <code>{@link PersianCalendarHelper}</code> class. The difference is that this 
	class does not use algebraic formulas, but instead creates a table of 
	year start days according to the algorithm. I have tested this class and it 
	seems to be equivalent to the <code>{@link PersianCalendarHelper}</code> class, but 
	its performance is lower.</p>
	
	<p>This class is kept here just as an illustration of the algorithm. Please see 
	the source file for more information.</p>
	
	@author <a href="mailto:ghasemkiani@yahoo.com">Ghasem Kiani</a>
	@version 2.1
*/
public class PersianCalendarHelper1
{
	private static final int YGRAND = 2820;
	private static final int DGRAND = 1029983;
	private static final int YSTART = 475;
	private static final int DSTART = 2121446;
	
	private static final int[] yd = new int[YGRAND];
	private static int k = 0, n = 1;
	private static void y365()
	{
		yd[k++] = n;
		n += 365;
	}
	private static void y366()
	{
		yd[k++] = n;
		n += 366;
	}
	private static void c4()
	{
		for(int i = 0; i < 3; i++) y365();
		y366();
	}
	private static void c5()
	{
		for(int i = 0; i < 4; i++) y365();
		y366();
	}
	private static void cc29()
	{
		c5();
		for(int i = 0; i < 6; i++) c4();
	}
	private static void cc33()
	{
		c5();
		for(int i = 0; i < 7; i++) c4();
	}
	private static void cc37()
	{
		c5();
		for(int i = 0; i < 8; i++) c4();
	}
	private static void ccc128()
	{
		cc29();
		cc33();
		cc33();
		cc33();
	}
	private static void ccc132()
	{
		cc29();
		cc33();
		cc33();
		cc37();
	}
	private static void cccc()
	{
		for(int i = 0; i < 21; i++) ccc128();
		ccc132();
	}
	private static boolean inited = false;
	private static void init()
	{
		if(!inited)
		{
			cccc();
			inited = true;
		}
	}
	/**
		Determines if the specified year is a leap year in the Persian calendar.
		
		@param year the "Persian" year.
		@return <code>true</code> if <code>year</code> is a leap year, <code>false</code> otherwise.
	*/
	public static boolean isLeapYear(long year)
	{
		return pj(year + 1, 0, 1) - pj(year, 0, 1) == 366;
	}
	/**
		Returns the Julian day corresponding to the specified day in the Persian calendar.
		
		@param y the Persian year.
		@param m the Persian month.
		@param d the Persian day.
		@return the Julian day corresponding to the specified date in the Persian calendar.
	*/
	public static long pj(long y, int m, int d)
	{
		init();
		d--;
		
		long x = 0;
		
		y -= (YSTART - 1);
		x += (DSTART - 1);
		
		while(y < 1)
		{
			y += YGRAND;
			x -= DGRAND;
		}
		while(y > YGRAND)
		{
			y -= YGRAND;
			x += DGRAND;
		}
		
		long j = yd[(int)y - 1] + m * 30 + (m > 6? 6: m) + d;
		
		j += x;
		
		return j;
	}
	/**
		Returns the date in the Persian calendar corresponding to the specified Julian day. 
		The date fields (year, month, and day) are packed into a long value. See <code>{@link PersianCalendarUtils}</code>
		class for extraction of fields from the packed long value.
		
		@param j the Julian day.
		@return a packed long value containing the corresponding Persian year, month, and day.
	*/
	public static long jp(long j)
	{
		init();
		
		long x = 0;
		
		j -= (DSTART - 1);
		x += (YSTART - 1);
		
		while(j < 1)
		{
			j += DGRAND;
			x -= YGRAND;
		}
		while(j > DGRAND)
		{
			j -= DGRAND;
			x += YGRAND;
		}
		
		long y = 0, m = 0, d;
		while(y < YGRAND && yd[(int)y] <= j) y++;
		j -= yd[(int)y - 1];
		if( j > 186)
		{
			m = (j - 186) / 30 + 6;
			d = (j - 186) % 30;
		}
		else
		{
			m = j / 31;
			d = j % 31;
		}
		
		y += x;
		
		d++;
		return (y << 16) | (m << 8) | d;
	}
}
