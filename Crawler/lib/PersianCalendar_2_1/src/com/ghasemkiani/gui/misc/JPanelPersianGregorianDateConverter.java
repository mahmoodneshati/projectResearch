/*
	JPanelPersianGregorianDateConverter.java
	2003-09-24 21:35:54
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

package com.ghasemkiani.gui.misc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.ghasemkiani.util.SimplePersianCalendar;
import com.ghasemkiani.util.DateFields;

public class JPanelPersianGregorianDateConverter extends JPanel
{
	private JPanel jPanelGregorian = new JPanel(new GridBagLayout());
	private JLabel jLabelGregorianYear = new JLabel("Year");
	private JLabel jLabelGregorianMonth = new JLabel("Month");
	private JLabel jLabelGregorianDay = new JLabel("Day");
	private JTextField jTextFieldGregorianYear = new JTextField();
	private JTextField jTextFieldGregorianMonth = new JTextField();
	private JTextField jTextFieldGregorianDay = new JTextField();
	
	private JPanel jPanelPersian = new JPanel(new GridBagLayout());
	private JLabel jLabelPersianYear = new JLabel("Year");
	private JLabel jLabelPersianMonth = new JLabel("Month");
	private JLabel jLabelPersianDay = new JLabel("Day");
	private JTextField jTextFieldPersianYear = new JTextField();
	private JTextField jTextFieldPersianMonth = new JTextField();
	private JTextField jTextFieldPersianDay = new JTextField();
	private JTextField jTextFieldPersianText = new JTextField();
	
	private JLabel jLabelCopyRight = new JLabel("<html><center>&copy; 2003, Ghasem Kiani<br>[<font color='purple'>ghasemkiani@yahoo.com</font>]</center></html>", JLabel.CENTER);
	
	private JPanel jPanelButtons = new JPanel(new FlowLayout());
	
	public JPanelPersianGregorianDateConverter()
	{
		super();
		setLayout(new GridBagLayout());
		
		add(jPanelGregorian, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.setBorder(BorderFactory.createTitledBorder("Gregorian"));
		jPanelGregorian.add(jLabelGregorianYear, new GridBagConstraints(0, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jLabelGregorianMonth, new GridBagConstraints(1, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jLabelGregorianDay, new GridBagConstraints(2, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jTextFieldGregorianYear, new GridBagConstraints(0, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jTextFieldGregorianMonth, new GridBagConstraints(1, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelGregorian.add(jTextFieldGregorianDay, new GridBagConstraints(2, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
		add(jPanelPersian, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.setBorder(BorderFactory.createTitledBorder("Persian"));
		jPanelPersian.add(jLabelPersianYear, new GridBagConstraints(0, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jLabelPersianMonth, new GridBagConstraints(1, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jLabelPersianDay, new GridBagConstraints(2, 0, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jTextFieldPersianYear, new GridBagConstraints(0, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jTextFieldPersianMonth, new GridBagConstraints(1, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jTextFieldPersianDay, new GridBagConstraints(2, 1, 1, 1, 0.333, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		jPanelPersian.add(jTextFieldPersianText, new GridBagConstraints(0, 2, 3, 1, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
		jTextFieldPersianText.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		jTextFieldPersianText.setEditable(false);
		
		add(jLabelCopyRight, new GridBagConstraints(0, 6, 1, 1, 1, 0, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1), 0, 0));
		
		add(jPanelButtons, new GridBagConstraints(0, 7, 1, 1, 1, 1, GridBagConstraints.FIRST_LINE_START, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));
		
		jPanelButtons.add(new JButton(new AbstractAction("To Persian"){
			public void actionPerformed(ActionEvent ae)
			{
				convertGregorianToPersian();
			}
		}));
		jPanelButtons.add(new JButton(new AbstractAction("To Gregorian"){
			public void actionPerformed(ActionEvent ae)
			{
				convertPersianToGregorian();
			}
		}));
		jPanelButtons.add(new JButton(new AbstractAction("Current"){
			public void actionPerformed(ActionEvent ae)
			{
				setCurrentDate();
			}
		}));
		setCurrentDate();
	}
	private void convertGregorianToPersian()
	{
		int year, month, day;
		DateFields t;
		try
		{
			try
			{
				year = Integer.parseInt(jTextFieldGregorianYear.getText());
			}
			catch(NumberFormatException nfe)
			{
				year = 0;
			}
			try
			{
				month = Integer.parseInt(jTextFieldGregorianMonth.getText()) - 1;
			}
			catch(NumberFormatException nfe)
			{
				month = 0;
			}
			try
			{
				day = Integer.parseInt(jTextFieldGregorianDay.getText());
			}
			catch(NumberFormatException nfe)
			{
				day = 0;
			}
			
			SimplePersianCalendar c = new SimplePersianCalendar();
			c.set(c.YEAR, year);
			c.set(c.MONTH, month);
			c.set(c.DAY_OF_MONTH, day);
			t = c.getDateFields();
			jTextFieldPersianYear.setText(Long.toString(t.getYear()));
			jTextFieldPersianMonth.setText(Long.toString(t.getMonth() + 1));
			jTextFieldPersianDay.setText(Long.toString(t.getDay()));
			
			setPersianText(c);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void convertPersianToGregorian()
	{
		int year, month, day;
		DateFields t;
		try
		{
			try
			{
				year = Integer.parseInt(jTextFieldPersianYear.getText());
			}
			catch(NumberFormatException nfe)
			{
				year = 0;
			}
			try
			{
				month = Integer.parseInt(jTextFieldPersianMonth.getText()) - 1;
			}
			catch(NumberFormatException nfe)
			{
				month = 0;
			}
			try
			{
				day = Integer.parseInt(jTextFieldPersianDay.getText());
			}
			catch(NumberFormatException nfe)
			{
				day = 0;
			}
			
			SimplePersianCalendar c = new SimplePersianCalendar();
			c.setDateFields(year, month, day);
			jTextFieldGregorianYear.setText(Long.toString(c.get(c.ERA) == c.AD? c.get(c.YEAR): -(c.get(c.YEAR) - 1)));
			jTextFieldGregorianMonth.setText(Long.toString(c.get(c.MONTH) + 1));
			jTextFieldGregorianDay.setText(Long.toString(c.get(c.DAY_OF_MONTH)));
			
			setPersianText(c);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void setCurrentDate()
	{
		SimplePersianCalendar c = new SimplePersianCalendar();
		DateFields t = c.getDateFields();
		
		jTextFieldPersianYear.setText(Long.toString(t.getYear()));
		jTextFieldPersianMonth.setText(Long.toString(t.getMonth() + 1));
		jTextFieldPersianDay.setText(Long.toString(t.getDay()));
		
		convertPersianToGregorian();
	}
	private void setPersianText(SimplePersianCalendar c)
	{
		DateFields t = c.getDateFields();
		jTextFieldPersianText.setText(c.getPersianWeekDayName(c.get(SimplePersianCalendar.DAY_OF_WEEK)) + "¡ " + t.getDay() + " " + c.getPersianMonthName(t.getMonth()) + " " + t.getYear());
	}
}
