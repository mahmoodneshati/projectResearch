/*
	TestPersianCalendarHelper.java
	2005-02-20 11:28:08
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

import java.util.Date;

import static com.ghasemkiani.util.PersianCalendarUtils.*;
import com.ghasemkiani.util.PersianCalendarHelper;
import com.ghasemkiani.util.PersianCalendarHelper1;

import com.ghasemkiani.test.TestUtils;

public class TestPersianCalendarHelper
{
	public static void main(String[] args)
	{
		System.out.println("This test compares PersianCalendarHelper and PersianCalendarHelper1 classes for 2000000 days.");
		System.out.println("");
		boolean error = false;
		
		long jpt = 0, pjt = 0, jpt1 = 0, pjt1 = 0, t;
		
		for(long i = -1000000; i < 1000000; i++)
		{
			if(i % 100000 == 0) System.out.println("Day #" + i);
			long j = i + EPOCH;
			
			t = System.nanoTime();
			long jp = PersianCalendarHelper.jp(j);
			jpt += (System.nanoTime() - t);
			
			t = System.nanoTime();
			long jp1 = PersianCalendarHelper1.jp(j);
			jpt1 += (System.nanoTime() - t);
			
			if(jp1 != jp)
			{
				error = true;
				System.out.println("Error 1");
			}
			else
			{
				long y = y(jp1);
				int m = m(jp1);
				int d = d(jp1);
				
				t = System.nanoTime();
				long pj = PersianCalendarHelper.pj(y, m, d);
				pjt += (System.nanoTime() - t);
				
				t = System.nanoTime();
				long pj1 = PersianCalendarHelper1.pj(y, m, d);
				pjt1 += (System.nanoTime() - t);
				
				if(pj1 != pj)
				{
					error = true;
					System.out.println("Error 2");
				}
				if(pj1 != j)
				{
					error = true;
					System.out.println("Error 3");
				}
			}
		}
		System.out.println("");
		System.out.println(error? "Test FAILED!": "Test SUCCEEDED!");
		System.out.println("");
		System.out.println("Time spent by PersianCalendarHelper:");
		System.out.println("jp: " + (jpt / 1000000) + " ms");
		System.out.println("pj: " + (pjt / 1000000) + " ms");
		
		System.out.println("Time spent by PersianCalendarHelper1:");
		System.out.println("jp: " + (jpt1 / 1000000) + " ms");
		System.out.println("pj: " + (pjt1 / 1000000) + " ms");
		
		TestUtils.printInfo();
	}
}
