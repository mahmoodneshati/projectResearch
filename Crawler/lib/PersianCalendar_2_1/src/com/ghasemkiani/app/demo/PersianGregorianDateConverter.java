/*
	PersianGregorianDateConverter.java
	2003-09-25 01:37:55
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

package com.ghasemkiani.app.demo;

import javax.swing.*;
import com.ghasemkiani.gui.misc.JPanelPersianGregorianDateConverter;

public class PersianGregorianDateConverter extends JFrame
{
	public PersianGregorianDateConverter()
	{
		super("Persian-Gregorian Date Converter");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(new JPanelPersianGregorianDateConverter());
		pack();
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new PersianGregorianDateConverter();
	}
}
