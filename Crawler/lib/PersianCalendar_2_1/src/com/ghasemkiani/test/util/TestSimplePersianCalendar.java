/*
	TestSimplePersianCalendar.java
	2005-02-21 10:56:57
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

package com.ghasemkiani.test.util;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ghasemkiani.util.SimplePersianCalendar;
import com.ghasemkiani.util.DateFields;
import static com.ghasemkiani.util.PersianCalendarUtils.EPOCH;
import com.ghasemkiani.test.TestUtils;

public class TestSimplePersianCalendar extends Object
{
	public static void main(String[] args)
	{
		System.out.println("");
		System.out.println("This test compares PersianCalendar and SimplePersianCalendar classes for 2000000 days.");
		System.out.println("The timezone of both calendars is arbitrarily set to America/Los_Angeles.");
		System.out.println("");
		boolean error = false;
		
		PersianCalendar pc = new PersianCalendar();
		SimplePersianCalendar spc = new SimplePersianCalendar();
		
		pc.setTimeZone(com.ibm.icu.util.TimeZone.getTimeZone("America/Los_Angeles"));
		// spc.setTimeZone(java.util.TimeZone.getTimeZone("America/Los_Angeles"));
		
		long pct1 = 0, spct1 = 0, pct2 = 0, spct2 = 0, t;
		
		for(int i = -1000000; i < 1000000; i++)
		{
			if(i % 100000 == 0) System.out.println("Day #" + i);
			int j = (int)(i + EPOCH);
			
			t = System.nanoTime();
			pc.set(pc.JULIAN_DAY, j);
			int pcy = pc.get(pc.EXTENDED_YEAR);
			int pcm = pc.get(pc.MONTH);
			int pcd = pc.get(pc.DAY_OF_MONTH);
			pct1 += (System.nanoTime() - t);
			
			t = System.nanoTime();
			spc.setJulianDay(j);
			DateFields df = spc.getDateFields();
			int spcy = df.getYear();
			int spcm = df.getMonth();
			int spcd = df.getDay();
			spct1 += (System.nanoTime() - t);
			
			if((pcy != (spcy > 0? spcy: spcy + 1)) || (pcm != spcm) || (pcd != spcd))
			{
				error = true;
				System.out.println("Error 1");
			}
			
			t = System.nanoTime();
			pc.set(pc.EXTENDED_YEAR, pcy);
			pc.set(pc.MONTH, pcm);
			pc.set(pc.DAY_OF_MONTH, pcd);
			int pcj = pc.get(pc.JULIAN_DAY);
			pct2 += (System.nanoTime() - t);
			
			t = System.nanoTime();
			DateFields df1 = new DateFields(spcy, spcm, spcd);
			spc.setDateFields(df1);
			long spcj = spc.getJulianDay();
			spct2 += (System.nanoTime() - t);
			
			if(pcj != spcj)
			{
				error = true;
				System.out.println("Error 2");
			}
			if(pcj != j)
			{
				error = true;
				System.out.println("Error 3");
			}
		}
		System.out.println("");
		System.out.println(error? "Test FAILED!": "Test SUCCEEDED!");
		System.out.println("");
		System.out.println("Time spent by PersianCalendar:");
		System.out.println("jp: " + (pct1 / 1000000) + " ms");
		System.out.println("pj: " + (pct2 / 1000000) + " ms");
		
		System.out.println("Time spent by SimplePersianCalendar:");
		System.out.println("jp: " + (spct1 / 1000000) + " ms");
		System.out.println("pj: " + (spct2 / 1000000) + " ms");
		
		TestUtils.printInfo();
	}
}
