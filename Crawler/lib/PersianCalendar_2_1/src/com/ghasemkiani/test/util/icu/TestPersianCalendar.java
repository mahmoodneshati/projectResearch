/*
	TestPersianCalendar.java
	2005-02-21 11:30:10
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

package com.ghasemkiani.test.util.icu;

import com.ibm.icu.util.GregorianCalendar;
import static com.ibm.icu.util.Calendar.*;
import static com.ibm.icu.util.GregorianCalendar.*;

import com.ghasemkiani.util.icu.PersianCalendar;
import static com.ghasemkiani.util.PersianCalendarUtils.EPOCH;

import static com.ghasemkiani.util.PersianCalendarConstants.*;
import static com.ghasemkiani.util.icu.PersianCalendar.*;

import com.ghasemkiani.test.TestUtils;

public class TestPersianCalendar extends Object
{
	public static void main(String[] args)
	{
		System.out.println("");
		System.out.println("This program performs some tests on PersianCalendar class.");
		
		PersianCalendar pc = new PersianCalendar();
		GregorianCalendar gc = new GregorianCalendar();
		boolean error = false;
		
		/*
			Vernal equinox before immigration of Prophet Muhammad (PBUH) to Medina
			was on Friday, March 19, 622 A.D. (Julian calendar). This day is the 
			epoch of the Persian calendar. Note that this predates the conversion 
			of Julian to Gregorian calendar.
		*/
		gc.set(ERA, AD);
		gc.set(YEAR, 622);
		gc.set(MONTH, MARCH);
		gc.set(DAY_OF_MONTH, 19);
		if(gc.get(JULIAN_DAY) != EPOCH)
		{
			error = true;
			System.out.println("Error 1");
		}
		
		pc.set(JULIAN_DAY, (int)EPOCH);
		if
		(
			pc.get(EXTENDED_YEAR) != 1 ||
			pc.get(ERA) != AH ||
			pc.get(YEAR) != 1 ||
			pc.get(MONTH) != FARVARDIN ||
			pc.get(DAY_OF_MONTH) != 1 ||
			pc.get(DAY_OF_WEEK) != FRIDAY
		)
		{
			error = true;
			System.out.println("Error 2");
		}
		
		pc.set(ERA, AH);
		pc.set(YEAR, 1);
		pc.set(MONTH, FARVARDIN);
		pc.set(DAY_OF_MONTH, 1);
		if(pc.get(JULIAN_DAY) != EPOCH)
		{
			error = true;
			System.out.println("Error 3");
		}
		
		/*
			The Black Friday
		*/
		pc.set(ERA, AH);
		pc.set(YEAR, 1357);
		pc.set(MONTH, SHAHRIVAR);
		pc.set(DAY_OF_MONTH, 17);
		if(pc.get(DAY_OF_WEEK) != FRIDAY)
		{
			error = true;
			System.out.println("Error 4");
		}
		
		/*
			My Birthday: Wednesday, September 16, 1970 (Shahrivar 25, 1349 A.H.)
		*/
		gc.set(ERA, AD);
		gc.set(YEAR, 1970);
		gc.set(MONTH, SEPTEMBER);
		gc.set(DAY_OF_MONTH, 16);
		pc.set(JULIAN_DAY, gc.get(JULIAN_DAY));
		if
		(
			pc.get(EXTENDED_YEAR) != 1349 ||
			pc.get(ERA) != AH ||
			pc.get(YEAR) != 1349 ||
			pc.get(MONTH) != SHAHRIVAR ||
			pc.get(DAY_OF_MONTH) != 25 ||
			pc.get(DAY_OF_WEEK) != WEDNESDAY
		)
		{
			error = true;
			System.out.println("Error 5");
		}
		
		/*
			Marzieh's Birthday (my wife): Monday, Shahrivar 6, 1351 A.H.
		*/
		pc.set(ERA, AH);
		pc.set(YEAR, 1351);
		pc.set(MONTH, SHAHRIVAR);
		pc.set(DAY_OF_MONTH, 6);
		if(pc.get(DAY_OF_WEEK) != MONDAY)
		{
			error = true;
			System.out.println("Error 6");
		}
		
		/*
			Today: Monday, February 21, 2005 (Esfand 3, 1383 A.H.)
		*/
		gc.set(ERA, AD);
		gc.set(YEAR, 2005);
		gc.set(MONTH, FEBRUARY);
		gc.set(DAY_OF_MONTH, 21);
		pc.set(JULIAN_DAY, gc.get(JULIAN_DAY));
		if
		(
			pc.get(EXTENDED_YEAR) != 1383 ||
			pc.get(ERA) != AH ||
			pc.get(YEAR) != 1383 ||
			pc.get(MONTH) != ESFAND ||
			pc.get(DAY_OF_MONTH) != 3 ||
			pc.get(DAY_OF_WEEK) != MONDAY
		)
		{
			error = true;
			System.out.println("Error 7");
		}
		
		/*
			1375 A.H. was a leap year.
		*/
		pc.set(ERA, AH);
		pc.set(YEAR, 1375);
		pc.set(MONTH, ESFAND);
		pc.set(DAY_OF_MONTH, 30);
		if(pc.get(DAY_OF_MONTH) != 30)
		{
			error = true;
			System.out.println("Error 8");
		}
		
		/*
			The add function must not change smaller fields.
		*/
		pc.set(ERA, AH);
		pc.set(YEAR, 1375);
		pc.set(MONTH, ESFAND);
		pc.set(DAY_OF_MONTH, 25);
		pc.add(MONTH, 1);
		if(pc.get(DAY_OF_MONTH) != 25)
		{
			error = true;
			System.out.println("Error 9");
		}
		
		System.out.println("");
		System.out.println(error? "Some tests FAILED!": "All tests SUCCEEDED!");
		
		TestUtils.printInfo();
	}
}
