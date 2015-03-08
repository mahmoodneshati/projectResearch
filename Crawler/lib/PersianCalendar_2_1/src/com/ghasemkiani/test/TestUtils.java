/*
	TestUtils.java
	2005-03-18 12:38:12
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

package com.ghasemkiani.test;

import java.util.Date;
import com.ibm.icu.util.*;

public class TestUtils
{
	public static void printInfo()
	{
		try
		{
			System.out.println("");
			System.out.println("System Properties:");
			System.out.println("");
			System.out.println("user.name:              " + System.getProperty("user.name"));
			System.out.println("java.runtime.version:   " + System.getProperty("java.runtime.version"));
			// System.out.println("user.language:          " + System.getProperty("user.language"));
			// System.out.println("user.country:           " + System.getProperty("user.country"));
			System.out.println("Default locale:         " + ULocale.getDefault());
			System.out.println("Default timezone:       " + TimeZone.getDefault().getID());
			System.out.println("");
			System.out.println("Date: " + new Date());
		}
		catch(Exception e)
		{
			System.err.println("Error in printing info: " + e);
		}
	}
	public static void main(String[] args)
	{
		printInfo();
	}
}
