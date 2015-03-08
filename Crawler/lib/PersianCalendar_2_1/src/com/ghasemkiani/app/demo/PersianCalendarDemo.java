/*
	PersianCalendarDemo.java
	2005-02-20 15:07:42
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
import com.ghasemkiani.gui.calendar.JPanelCalendar;

public class PersianCalendarDemo extends JFrame
{
	public PersianCalendarDemo()
	{
		super("Persian Calendar Demo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel jPanel = new JPanelCalendar();
		add(jPanel);
		pack();

		setVisible(true);
	}
	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		new PersianCalendarDemo();
	}
}
