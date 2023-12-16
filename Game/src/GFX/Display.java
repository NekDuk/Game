package GFX;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.FocusListener;

import javax.swing.JFrame;

import Engine.KeyManager;
import Utility.Util;

public class Display extends Canvas
{
	JFrame frame;
	Canvas canvas;
	String title;
	Dimension size;
	
	public Display(Dimension _size, String _title)
	{
		size = _size;
		title = _title;
		
		createDisplay();
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	void createDisplay()
	{
		Util.log("Creating display!", "DISPLAY");
		frame = new JFrame();
		frame.setPreferredSize(size);
		frame.setMinimumSize(size);
		frame.setMaximumSize(size);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocus();
		KeyManager.input = new KeyManager();
		frame.addKeyListener(KeyManager.input);
		
		
		canvas = new Canvas();
		canvas.setPreferredSize(size);
		canvas.setMinimumSize(size);
		canvas.setMaximumSize(size);
		frame.add(canvas);
		frame.pack();
		Util.logS("Created display!", "DISPLAY");
	}
}
