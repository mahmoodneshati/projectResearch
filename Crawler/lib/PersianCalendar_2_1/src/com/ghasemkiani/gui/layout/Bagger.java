/*
	Bagger.java
	2004-07-28 12:21:44
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

package com.ghasemkiani.gui.layout;

import java.awt.*;
import static java.awt.GridBagConstraints.*;
import javax.swing.*;

public class Bagger extends Object
{
	private Integer iPadX; public void setIPadX(Integer iPadX) { this.iPadX = iPadX; } public Integer getIPadX() { if(iPadX == null) iPadX = new Integer(0);  return iPadX; }
	private Integer iPadY; public void setIPadY(Integer iPadY) { this.iPadY = iPadY; } public Integer getIPadY() { if(iPadY == null) iPadY = new Integer(0);  return iPadY; }
	private Insets insets; public void setInsets(Insets insets) { this.insets = insets; } public Insets getInsets() { if(insets == null) insets = new Insets(1, 1, 1, 1);  return insets; }
	private Integer anchor; public void setAnchor(Integer anchor) { this.anchor = anchor; } public Integer getAnchor() { if(anchor == null) anchor = FIRST_LINE_START;  return anchor; }
	private JPanel jPanel; public void setJPanel(JPanel jPanel) { this.jPanel = jPanel; if(!(jPanel.getLayout() instanceof GridBagLayout)) jPanel.setLayout(new GridBagLayout()); } public JPanel getJPanel() { if(jPanel == null) setJPanel(new JPanel()); return jPanel; }
	private int x, y, xg, yg;
	private double xw, yw;
	public Bagger()
	{
		super();
		reset();
	}
	public Bagger(JPanel jPanel)
	{
		this();
		setJPanel(jPanel);
	}
	public void row()
	{
		row(1);
	}
	public void row(int n)
	{
		y += n;
		x = 0;
	}
	public void go()
	{
		go(1);
	}
	public void go(int n)
	{
		x += n;
	}
	public void xfill()
	{
		xfill(1);
	}
	public void yfill()
	{
		yfill(1);
	}
	public void xfill(double xf)
	{
		xw = xf;
	}
	public void yfill(double yf)
	{
		yw = yf;
	}
	public void xspan(int xs)
	{
		xg = xs;
	}
	public void yspan(int ys)
	{
		yg = ys;
	}
	public void add(Component c)
	{
		int fill = xw > 0? (yw > 0? BOTH: HORIZONTAL):(yw > 0? VERTICAL: NONE);
		getJPanel().add(c, new GridBagConstraints(x, y, xg, yg, xw, yw, getAnchor(), fill, getInsets(), getIPadX(), getIPadY()));
		x += xg;
		reset();
	}
	public void reset()
	{
		xg = 1;
		yg = 1;
		xw = 0;
		yw = 0;
	}
	public void center()
	{
		setAnchor(CENTER);
	}
	public void north()
	{
		setAnchor(NORTH);
	}
	public void northeast()
	{
		setAnchor(NORTHEAST);
	}
	public void east()
	{
		setAnchor(EAST);
	}
	public void southeast()
	{
		setAnchor(SOUTHEAST);
	}
	public void south()
	{
		setAnchor(SOUTH);
	}
	public void southwest()
	{
		setAnchor(SOUTHWEST);
	}
	public void west()
	{
		setAnchor(WEST);
	}
	public void northwest()
	{
		setAnchor(NORTHWEST);
	}
	public void pageStart()
	{
		setAnchor(PAGE_START);
	}
	public void pageEnd()
	{
		setAnchor(PAGE_END);
	}
	public void lineStart()
	{
		setAnchor(LINE_START);
	}
	public void lineEnd()
	{
		setAnchor(LINE_END);
	}
	public void firstLineStart()
	{
		setAnchor(FIRST_LINE_START);
	}
	public void firstLineEnd()
	{
		setAnchor(FIRST_LINE_END);
	}
	public void lastLineStart()
	{
		setAnchor(LAST_LINE_START);
	}
	public void lastLineEnd()
	{
		setAnchor(LAST_LINE_END);
	}
	public void pad(int i)
	{
		setIPadX(i);
		setIPadY(i);
	}
	public void pad(int x, int y)
	{
		setIPadX(x);
		setIPadY(y);
	}
	public void inset(int i)
	{
		inset(i, i, i, i);
	}
	public void inset(int a, int b, int c, int d)
	{
		setInsets(new Insets(a, b, c, d));
	}
	public Bagger bagger(double xf)
	{
		Bagger result = new Bagger();
		result.setIPadX(getIPadX());
		result.setIPadY(getIPadY());
		result.setInsets(getInsets());
		result.setAnchor(getAnchor());
		xfill(xf);
		add(result.getJPanel());
		// row();
		return result;
	}
	public Bagger bagger()
	{
		return bagger(1);
	}
}
