/*
	JPanelCalendar.java
	2005-02-18 07:02:05
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

package com.ghasemkiani.gui.calendar;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;


import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.IslamicCalendar;
import com.ibm.icu.util.JapaneseCalendar;
import com.ibm.icu.util.ChineseCalendar;
import com.ibm.icu.util.BuddhistCalendar;
import com.ibm.icu.util.HebrewCalendar;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
 
import com.ghasemkiani.gui.layout.Bagger;
import com.ghasemkiani.util.icu.PersianCalendar;

public class JPanelCalendar extends JPanel
{
	private static final String[] FIELD_NAMES = {"Year", "Month", "Week of Month", "Week of Year", "Day of Month", "Day of Week", "Day of Week in Month", "Day of Year", "AM/PM", "Hour of day", "Hour", "Minute", "Second"};
	private static final int[] FIELDS = {Calendar.YEAR, Calendar.MONTH, Calendar.WEEK_OF_MONTH, Calendar.WEEK_OF_YEAR, Calendar.DAY_OF_MONTH, Calendar.DAY_OF_WEEK, Calendar.DAY_OF_WEEK_IN_MONTH, Calendar.DAY_OF_YEAR, Calendar.AM_PM, Calendar.HOUR_OF_DAY, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND};
	private static final String[] STYLE_NAMES = {"Default", "Short", "Medium", "Long", "Full"};
	private static final int[] STYLES = {DateFormat.DEFAULT, DateFormat.SHORT, DateFormat.MEDIUM, DateFormat.LONG, DateFormat.FULL};
	private static final String RTL_LANGUAGES = "fa,ar,iw,he";
	private static final Color LIGHTYELLOW = new Color(255, 255, 224);
	private static final Color LAVENDER = new Color(230, 230, 250);
	private JComboBox jComboBoxTimeZone; public void setJComboBoxTimeZone(JComboBox jComboBoxTimeZone) { this.jComboBoxTimeZone = jComboBoxTimeZone; } public JComboBox getJComboBoxTimeZone() { if(jComboBoxTimeZone == null) jComboBoxTimeZone = new JComboBox(TimeZone.getAvailableIDs());  return jComboBoxTimeZone; }
	private JComboBox jComboBoxLocale; public void setJComboBoxLocale(JComboBox jComboBoxLocale) { this.jComboBoxLocale = jComboBoxLocale; } public JComboBox getJComboBoxLocale() { if(jComboBoxLocale == null) jComboBoxLocale = new JComboBox(ULocale.getAvailableLocales());  return jComboBoxLocale; }
	private JComboBox jComboBoxCalendarType; public void setJComboBoxCalendarType(JComboBox jComboBoxCalendarType) { this.jComboBoxCalendarType = jComboBoxCalendarType; } public JComboBox getJComboBoxCalendarType() { if(jComboBoxCalendarType == null) jComboBoxCalendarType = new JComboBox(new String[] {"Gregorian", "Persian", "Islamic", "Japanese", "Chinese", "Buddhist", "Hebrew"});  return jComboBoxCalendarType; }
	private JTextField jTextFieldTimeInMillis; public void setJTextFieldTimeInMillis(JTextField jTextFieldTimeInMillis) { this.jTextFieldTimeInMillis = jTextFieldTimeInMillis; } public JTextField getJTextFieldTimeInMillis() { if(jTextFieldTimeInMillis == null) jTextFieldTimeInMillis = new JTextField();  return jTextFieldTimeInMillis; }
	private JCheckBox jCheckBoxApplyTimeInMillis; public void setJCheckBoxApplyTimeInMillis(JCheckBox jCheckBoxApplyTimeInMillis) { this.jCheckBoxApplyTimeInMillis = jCheckBoxApplyTimeInMillis; } public JCheckBox getJCheckBoxApplyTimeInMillis() { if(jCheckBoxApplyTimeInMillis == null) jCheckBoxApplyTimeInMillis = new JCheckBox("Apply");  return jCheckBoxApplyTimeInMillis; }
	private JTextField jTextFieldYear; public void setJTextFieldYear(JTextField jTextFieldYear) { this.jTextFieldYear = jTextFieldYear; } public JTextField getJTextFieldYear() { if(jTextFieldYear == null) jTextFieldYear = new JTextField();  return jTextFieldYear; }
	private JTextField jTextFieldMonth; public void setJTextFieldMonth(JTextField jTextFieldMonth) { this.jTextFieldMonth = jTextFieldMonth; } public JTextField getJTextFieldMonth() { if(jTextFieldMonth == null) jTextFieldMonth = new JTextField();  return jTextFieldMonth; }
	private JTextField jTextFieldDay; public void setJTextFieldDay(JTextField jTextFieldDay) { this.jTextFieldDay = jTextFieldDay; } public JTextField getJTextFieldDay() { if(jTextFieldDay == null) jTextFieldDay = new JTextField();  return jTextFieldDay; }
	private JTextField jTextFieldHour; public void setJTextFieldHour(JTextField jTextFieldHour) { this.jTextFieldHour = jTextFieldHour; } public JTextField getJTextFieldHour() { if(jTextFieldHour == null) jTextFieldHour = new JTextField();  return jTextFieldHour; }
	private JTextField jTextFieldMinute; public void setJTextFieldMinute(JTextField jTextFieldMinute) { this.jTextFieldMinute = jTextFieldMinute; } public JTextField getJTextFieldMinute() { if(jTextFieldMinute == null) jTextFieldMinute = new JTextField();  return jTextFieldMinute; }
	private JTextField jTextFieldSecond; public void setJTextFieldSecond(JTextField jTextFieldSecond) { this.jTextFieldSecond = jTextFieldSecond; } public JTextField getJTextFieldSecond() { if(jTextFieldSecond == null) jTextFieldSecond = new JTextField();  return jTextFieldSecond; }
	private JCheckBox jCheckBoxApplyDate; public void setJCheckBoxApplyDate(JCheckBox jCheckBoxApplyDate) { this.jCheckBoxApplyDate = jCheckBoxApplyDate; } public JCheckBox getJCheckBoxApplyDate() { if(jCheckBoxApplyDate == null) jCheckBoxApplyDate = new JCheckBox("Apply");  return jCheckBoxApplyDate; }
	private JComboBox jComboBoxFieldAdd; public void setJComboBoxFieldAdd(JComboBox jComboBoxFieldAdd) { this.jComboBoxFieldAdd = jComboBoxFieldAdd; } public JComboBox getJComboBoxFieldAdd() { if(jComboBoxFieldAdd == null) jComboBoxFieldAdd = new JComboBox(FIELD_NAMES);  return jComboBoxFieldAdd; }
	private JTextField jTextFieldAmountAdd; public void setJTextFieldAmountAdd(JTextField jTextFieldAmountAdd) { this.jTextFieldAmountAdd = jTextFieldAmountAdd; } public JTextField getJTextFieldAmountAdd() { if(jTextFieldAmountAdd == null) jTextFieldAmountAdd = new JTextField();  return jTextFieldAmountAdd; }
	private JCheckBox jCheckBoxApplyAdd; public void setJCheckBoxApplyAdd(JCheckBox jCheckBoxApplyAdd) { this.jCheckBoxApplyAdd = jCheckBoxApplyAdd; } public JCheckBox getJCheckBoxApplyAdd() { if(jCheckBoxApplyAdd == null) jCheckBoxApplyAdd = new JCheckBox("Apply");  return jCheckBoxApplyAdd; }
	private JComboBox jComboBoxFieldRoll; public void setJComboBoxFieldRoll(JComboBox jComboBoxFieldRoll) { this.jComboBoxFieldRoll = jComboBoxFieldRoll; } public JComboBox getJComboBoxFieldRoll() { if(jComboBoxFieldRoll == null) jComboBoxFieldRoll = new JComboBox(FIELD_NAMES);  return jComboBoxFieldRoll; }
	private JTextField jTextFieldAmountRoll; public void setJTextFieldAmountRoll(JTextField jTextFieldAmountRoll) { this.jTextFieldAmountRoll = jTextFieldAmountRoll; } public JTextField getJTextFieldAmountRoll() { if(jTextFieldAmountRoll == null) jTextFieldAmountRoll = new JTextField();  return jTextFieldAmountRoll; }
	private JCheckBox jCheckBoxApplyRoll; public void setJCheckBoxApplyRoll(JCheckBox jCheckBoxApplyRoll) { this.jCheckBoxApplyRoll = jCheckBoxApplyRoll; } public JCheckBox getJCheckBoxApplyRoll() { if(jCheckBoxApplyRoll == null) jCheckBoxApplyRoll = new JCheckBox("Apply");  return jCheckBoxApplyRoll; }
	private JComboBox jComboBoxFormatDate; public void setJComboBoxFormatDate(JComboBox jComboBoxFormatDate) { this.jComboBoxFormatDate = jComboBoxFormatDate; } public JComboBox getJComboBoxFormatDate() { if(jComboBoxFormatDate == null) jComboBoxFormatDate = new JComboBox(STYLE_NAMES);  return jComboBoxFormatDate; }
	private JComboBox jComboBoxFormatTime; public void setJComboBoxFormatTime(JComboBox jComboBoxFormatTime) { this.jComboBoxFormatTime = jComboBoxFormatTime; } public JComboBox getJComboBoxFormatTime() { if(jComboBoxFormatTime == null) jComboBoxFormatTime = new JComboBox(STYLE_NAMES);  return jComboBoxFormatTime; }
	private JTextField jTextFieldPattern; public void setJTextFieldPattern(JTextField jTextFieldPattern) { this.jTextFieldPattern = jTextFieldPattern; } public JTextField getJTextFieldPattern() { if(jTextFieldPattern == null) jTextFieldPattern = new JTextField();  return jTextFieldPattern; }
	private JCheckBox jCheckBoxApplyPattern; public void setJCheckBoxApplyPattern(JCheckBox jCheckBoxApplyPattern) { this.jCheckBoxApplyPattern = jCheckBoxApplyPattern; } public JCheckBox getJCheckBoxApplyPattern() { if(jCheckBoxApplyPattern == null) jCheckBoxApplyPattern = new JCheckBox("Apply");  return jCheckBoxApplyPattern; }
	private JButton jButtonRefresh; public void setJButtonRefresh(JButton jButtonRefresh) { this.jButtonRefresh = jButtonRefresh; } public JButton getJButtonRefresh() { if(jButtonRefresh == null) jButtonRefresh = new JButton("Refresh");  return jButtonRefresh; }
	private JTextField jTextFieldDisplay; public void setJTextFieldDisplay(JTextField jTextFieldDisplay) { this.jTextFieldDisplay = jTextFieldDisplay; } public JTextField getJTextFieldDisplay() { if(jTextFieldDisplay == null) jTextFieldDisplay = new JTextField();  return jTextFieldDisplay; }
	public JPanelCalendar()
	{
		super();
		init();
	}
	protected void init()
	{
		setLayout(new BorderLayout());
		Bagger b = new Bagger(), b1;
		add(b.getJPanel(), BorderLayout.CENTER);
		b.lineStart();
		
		b.xspan(3);
		b1 = b.bagger();
		b1.pad(0, 0);
		b1.inset(0);
		b1.xfill();
		JLabel jLabelTop1 = new JLabel("PERSIAN CALENDAR DEMO", JLabel.CENTER);
		jLabelTop1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		jLabelTop1.setOpaque(true);
		jLabelTop1.setBackground(LIGHTYELLOW);
		b1.add(jLabelTop1);
		b1.row();
		b1.xfill();
		JLabel jLabelTop2 = new JLabel("Ghasem Kiani, M.D.", JLabel.CENTER);
		jLabelTop2.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		jLabelTop2.setOpaque(true);
		jLabelTop2.setBackground(LIGHTYELLOW);
		b1.add(jLabelTop2);
		b.row();
		
		b.xfill(0.2);
		b.add(createJLabelFor("Time &Zone", getJComboBoxTimeZone()));
		b.add(getJComboBoxTimeZone());
		b.row();
		
		b.xfill(0.2);
		b.add(createJLabelFor("&Locale", getJComboBoxLocale()));
		b.add(getJComboBoxLocale());
		b.row();
		
		b.xfill(0.2);
		b.add(createJLabelFor("&Calendar Type", getJComboBoxCalendarType()));
		b.add(getJComboBoxCalendarType());
		b.row();
		
		b.xfill(0.2);
		b.add(createJLabelFor("&Time in Millis", getJTextFieldTimeInMillis()));
		b.xfill(0.6);
		b.add(getJTextFieldTimeInMillis());
		b.add(getJCheckBoxApplyTimeInMillis());
		b.row();
		
		b.xfill(0.2);
		b.add(createJLabelFor("&Date", getJTextFieldYear()));
		b1 = b.bagger(0.6);
		b1.xfill(0.25);
		b1.add(getJTextFieldYear());
		b1.add(new JLabel("/"));
		b1.xfill(0.15);
		b1.add(getJTextFieldMonth());
		b1.add(new JLabel("/"));
		b1.xfill(0.15);
		b1.add(getJTextFieldDay());
		b1.add(new JLabel(" "));
		b1.xfill(0.15);
		b1.add(getJTextFieldHour());
		b1.add(new JLabel(":"));
		b1.xfill(0.15);
		b1.add(getJTextFieldMinute());
		b1.add(new JLabel(":"));
		b1.xfill(0.15);
		b1.add(getJTextFieldSecond());
		b.add(getJCheckBoxApplyDate());
		b.row();
		
		b.xfill(0.2);
		b.add(createJLabelFor("&Add", getJComboBoxFieldAdd()));
		b1 = b.bagger(0.6);
		b1.add(getJComboBoxFieldAdd());
		b1.add(new JLabel(" by "));
		b1.xfill(0.2);
		b1.add(getJTextFieldAmountAdd());
		b.add(getJCheckBoxApplyAdd());
		b.row();
		
		b.xfill(0.2);
		b.add(createJLabelFor("&Roll", getJComboBoxFieldRoll()));
		b1 = b.bagger(0.6);
		b1.add(getJComboBoxFieldRoll());
		b1.add(new JLabel(" by "));
		b1.xfill(0.2);
		b1.add(getJTextFieldAmountRoll());
		b.add(getJCheckBoxApplyRoll());
		b.row();
		
		b.xfill(0.2);
		b.add(new JLabel("Style"));
		b1 = b.bagger(0.6);
		b1.lineStart();
		b1.add(createJLabelFor("Dat&e", getJComboBoxFormatDate()));
		b1.add(getJComboBoxFormatDate());
		b1.add(createJLabelFor("T&ime", getJComboBoxFormatTime()));
		b1.add(getJComboBoxFormatTime());
		b1.bagger();
		b.row();
		
		b.xfill(0.2);
		b.add(createJLabelFor("&Pattern", getJTextFieldPattern()));
		b.xfill(0.6);
		b.add(getJTextFieldPattern());
		b.add(getJCheckBoxApplyPattern());
		b.row();
		
		b.xspan(3);
		b.yfill();
		b1 = b.bagger();
		b1.pageStart();
		b1.add(getJButtonRefresh());
		b.row();
		
		getJTextFieldDisplay().setEditable(false);
		b.xspan(3);
		b.xfill();
		b.add(getJTextFieldDisplay());
		b.row();
		
		getJComboBoxTimeZone().setSelectedItem(TimeZone.getDefault().getID());
		getJComboBoxLocale().setSelectedItem(ULocale.getDefault());
		getJTextFieldAmountAdd().setText("0");
		getJTextFieldAmountRoll().setText("0");
		getJTextFieldDisplay().setFont(new Font("Tahoma", Font.PLAIN, 12));
		getJTextFieldDisplay().setBackground(LAVENDER);
		
		getJButtonRefresh().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				doRefresh();
			}
		});
		
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLineBorder(Color.red, 1)));
		
		doRefresh();
	}
	protected JLabel createJLabelFor(String text, Component target)
	{
		JLabel jLabel = new JLabel();
		if(text != null && text.length() > 0)
		{
			int c = 0;
			int amp = text.indexOf("&");
			if(amp >= 0)
			{
				c = amp;
				text = text.substring(0, amp) + text.substring(amp + 1);
			}
			jLabel.setText(text);
			jLabel.setDisplayedMnemonicIndex(c);
			jLabel.setDisplayedMnemonic(text.charAt(c));
		}
		jLabel.setLabelFor(target);
		return jLabel;
	}
	public void doRefresh()
	{
		try
		{
			Calendar calendar;
			TimeZone timeZone = TimeZone.getTimeZone(String.valueOf(getJComboBoxTimeZone().getSelectedItem()));
			ULocale uLocale = ULocale.createCanonical(String.valueOf(getJComboBoxLocale().getSelectedItem()));
			String calendarType = String.valueOf(getJComboBoxCalendarType().getSelectedItem());
			if("Persian".equals(calendarType)) calendar = new PersianCalendar(timeZone, uLocale);
			else if("Islamic".equals(calendarType)) calendar = new IslamicCalendar(timeZone, uLocale);
			else if("Gregorian".equals(calendarType)) calendar = new GregorianCalendar(timeZone, uLocale);
			else if("Japanese".equals(calendarType)) calendar = new JapaneseCalendar(timeZone, uLocale);
			else if("Chinese".equals(calendarType)) calendar = new ChineseCalendar(timeZone, uLocale);
			else if("Buddhist".equals(calendarType)) calendar = new BuddhistCalendar(timeZone, uLocale);
			else if("Hebrew".equals(calendarType)) calendar = new HebrewCalendar(timeZone, uLocale);
			else calendar = new GregorianCalendar(timeZone, uLocale);
			if(getJCheckBoxApplyTimeInMillis().isSelected())
			{
				try{ calendar.setTimeInMillis(Long.parseLong(getJTextFieldTimeInMillis().getText())); } catch(Exception e){ }
			}
			if(getJCheckBoxApplyDate().isSelected())
			{
				try{ calendar.set(Calendar.EXTENDED_YEAR, Integer.parseInt(getJTextFieldYear().getText())); } catch(Exception e){ }
				try{ calendar.set(Calendar.MONTH, Integer.parseInt(getJTextFieldMonth().getText()) - 1); } catch(Exception e){ }
				try{ calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(getJTextFieldDay().getText())); } catch(Exception e){ }
				try{ calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(getJTextFieldHour().getText())); } catch(Exception e){ }
				try{ calendar.set(Calendar.MINUTE, Integer.parseInt(getJTextFieldMinute().getText())); } catch(Exception e){ }
				try{ calendar.set(Calendar.SECOND, Integer.parseInt(getJTextFieldSecond().getText())); } catch(Exception e){ }
			}
			if(getJCheckBoxApplyAdd().isSelected())
			{
				try{ calendar.add(FIELDS[getJComboBoxFieldAdd().getSelectedIndex()], Integer.parseInt(getJTextFieldAmountAdd().getText())); } catch(Exception e){ }
			}
			if(getJCheckBoxApplyRoll().isSelected())
			{
				try{ calendar.roll(FIELDS[getJComboBoxFieldRoll().getSelectedIndex()], Integer.parseInt(getJTextFieldAmountRoll().getText())); } catch(Exception e){ }
			}
			getJTextFieldTimeInMillis().setText(String.valueOf(calendar.getTimeInMillis()));
			getJTextFieldYear().setText(String.valueOf(calendar.get(Calendar.EXTENDED_YEAR)));
			getJTextFieldMonth().setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
			getJTextFieldDay().setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
			getJTextFieldHour().setText(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
			getJTextFieldMinute().setText(String.valueOf(calendar.get(Calendar.MINUTE)));
			getJTextFieldSecond().setText(String.valueOf(calendar.get(Calendar.SECOND)));
			SimpleDateFormat sdf = (SimpleDateFormat)calendar.getDateTimeFormat(STYLES[getJComboBoxFormatDate().getSelectedIndex()], STYLES[getJComboBoxFormatTime().getSelectedIndex()], uLocale);
			if(getJCheckBoxApplyPattern().isSelected()) sdf.applyPattern(getJTextFieldPattern().getText());
			if(RTL_LANGUAGES.indexOf(uLocale.getLanguage()) >= 0) getJTextFieldDisplay().applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			else getJTextFieldDisplay().applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			getJTextFieldDisplay().setText(sdf.format(calendar.getTime()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
