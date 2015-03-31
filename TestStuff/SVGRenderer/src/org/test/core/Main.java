package org.test.core;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main
{
	static boolean isRunning;
	static Window window;

	static JSVGCanvas canvas;
	static Document doc;
	static Element svg;

	static AffineTransform defaultTransform;

	private static Integer lastX = null;
	private static Integer lastY = null;

	public static void main(String... args)
	{
		isRunning = true;

		window = WindowManager
				.createFullscreenWindow("Zoom with 1/2. Move with mouse. Reset with R.");

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		DisplayMode mode = gd.getDisplayMode();
		int height = mode.getHeight();
		int width = mode.getWidth();

		MySVGGenerator gen = new MySVGGenerator(width, height, 96, "Sloan2.svg");

		canvas = new JSVGCanvas();

		try
		{
			String parser = XMLResourceDescriptor.getXMLParserClassName();
			SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);

			doc = f.createDocument("Sloan2.svg");
		} catch (Exception e)
		{
			System.out.println();
			e.printStackTrace();
		}

		canvas.setDoubleBuffered(true);
		canvas.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
		canvas.setDocument(doc);

		defaultTransform = canvas.getRenderingTransform();

		window.add("Center", canvas);

		MouseMotionListener mouse = new MouseMotionListener()
		{

			@Override
			public void mouseMoved(MouseEvent e)
			{

				Point p = window.getMousePosition();

				lastX = (int) p.getX();
				lastY = (int) p.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e)
			{
				Point p = window.getMousePosition();

				if (p != null)
				{
					if (lastX == null || lastY == null)
					{
						lastX = (int) p.getX();
						lastY = (int) p.getY();
					}

					AffineTransform transform = canvas.getRenderingTransform();
					transform.translate(
							(p.getX() - lastX) / transform.getScaleX(),
							((p.getY() - lastY)) / transform.getScaleX());
					canvas.setRenderingTransform(transform);

					lastX = (int) p.getX();
					lastY = (int) p.getY();
				}
			}
		};

		MouseListener mouseListener = new MouseListener()
		{

			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e)
			{

			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				// TODO Auto-generated method stub

			}
		};

		MouseWheelListener scroll = new MouseWheelListener()
		{

			@Override
			public void mouseWheelMoved(MouseWheelEvent e)
			{
				AffineTransform transform = canvas.getRenderingTransform();
				transform.translate(0, e.getWheelRotation());
				canvas.setRenderingTransform(transform);
			}
		};

		KeyListener keyboard = new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					window.setFullscreen();
				}
				if (e.getKeyCode() == KeyEvent.VK_2)
				{
					AffineTransform transform = canvas.getRenderingTransform();
					if (transform.getScaleX() < 4f)
					{
						transform.scale(1.2f, 1.2f);
						transform.translate((-canvas.getWidth() / 6f) / 2f, 0);
						canvas.setRenderingTransform(transform);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_1)
				{
					AffineTransform transform = canvas.getRenderingTransform();
					if (transform.getScaleX() > 0.1f)
					{
						transform.scale(1f - (1f / 6f), 1f - (1f / 6f));
						transform.translate((canvas.getWidth() / 5f) / 2f, 0);
						canvas.setRenderingTransform(transform);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_R)
				{
					canvas.setRenderingTransform(defaultTransform);
				}

				e.consume();
			}
		};

		window.addKeyListener(keyboard);
		canvas.addKeyListener(keyboard);
		canvas.addMouseWheelListener(scroll);
		canvas.addMouseMotionListener(mouse);
		canvas.addMouseListener(mouseListener);

		window.pack();

		window.setVisible(true);

	}
}
