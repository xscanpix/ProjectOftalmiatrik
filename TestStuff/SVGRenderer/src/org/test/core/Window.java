package org.test.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame
{
	private Dimension lastDimension = new Dimension(800, 600);

	public Window(final String TITLE)
	{
		super(TITLE);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
	}

	public void setFullscreen()
	{
		if ((this.getExtendedState() == Frame.MAXIMIZED_BOTH)
				&& this.isUndecorated())
		{
			this.dispose();
			this.setVisible(false);
			this.setExtendedState(Frame.NORMAL);
			this.setUndecorated(false);
			this.setSize(lastDimension);
			this.setVisible(true);
		} else
		{
			lastDimension = getSize();
			this.dispose();
			this.setVisible(false);
			this.setUndecorated(true);
			this.setExtendedState(Frame.MAXIMIZED_BOTH);
			this.setVisible(true);
		}
	}

	@Override
	public void paint(Graphics g)
	{
		System.out.println("Repainting...");

		g.setColor(Color.WHITE);
		g.clearRect(0, 0, getWidth(), getHeight());
		
		super.paint(g);
	}
}
