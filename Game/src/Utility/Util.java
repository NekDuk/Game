package Utility;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Util 
{
	public static final String version = "0.0.1";
	public static final String title = "Traffic Simulator";
	
	
	public static void prt(String s)
	{
		System.out.println(s);
	}
	
	public static String loadFileAsString(String path)
	{
		StringBuilder b = new StringBuilder();
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)
				b.append(line + "\n");
			br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return b.toString();
	}
	
	public static int parseInt(String i)
	{
		try
		{
			return Integer.parseInt(i);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public static double parseDouble(String i)
	{
		try
		{
			return Double.parseDouble(i);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	public static void error(String s)
	{
		System.out.println("\u001B[31m \u001B[47m" + s + "\u001B[0m");
	}
	public enum Message
	{
		MESSAGE,
		WARNING,
		ERROR,
		SUCCESS
	}
	static long startTime = 0;
	public static void init()
	{
		startTime = System.nanoTime();
		Util.log("Utility intilized", title + " " + version, Util.Message.MESSAGE, "UTILITY");

	}
	public long getStartTime()
	{
		return startTime;
	}
	
	public static void log(String s, String highlight, Message msg, String name)
	{
		String color = "";
		String txt = "";
		switch(msg)
		{
			case WARNING:
				color = "\u001B[43m";
				txt = "[WARNING]";
				break;
			case ERROR:
				color = "\u001B[41m";
				txt = "[ERROR]";
				break;
			case SUCCESS:
				color = "\u001B[42m";
				txt = "[SUCCESS]";
				break;
				default:
					color = "\u001B[40m";
					txt = "[MESSAGE]";
		}
		System.out.println(color + "\u001B[37m " + txt + "[" + name + "]:"+ (System.nanoTime() - startTime) / 100000 + "ms - " + s + " \u001B[33m" + highlight + "\u001B[0m");
	}
	
	public static void log(String s, Message msg, String name)
	{
		log(s, "", msg, name);
	}
	public static void logS(String s, String name)
	{
		log(s, "", Message.SUCCESS, name);
	}
	
	public static void log(String s, String name)
	{
		log(s, "", Message.MESSAGE, name);
	}
	
	public static void log(double i, String name)
	{
		log(Double.toString(i), "", Message.MESSAGE, name);
	}
	
	public static double lerp(double a, double b, double t)
	{
		t = Math.min(t, 1);
		t = Math.max(t, 0);
		return((b - a) * t) + a;
	}
	
	public static Point lerpPoint(Point a, Point b, double t)
	{
		int x = (int) lerp(a.x, b.x, t);
		int y = (int) lerp(a.y, b.y, t);
		return new Point(x, y);
	}
	
	public static String[] loadInfo(String file, String splitter, String[] identifiers)
	{
		String[] data = new String[identifiers.length];
		String[] info = file.split(splitter);
		boolean next = false;
		int iden = -1;
		for(String s : info)
		{
			if(s.isBlank())
				continue;
			
			s = s.trim();
			
			if(next && iden >= 0)
			{
				data[iden] = s;
				next = false;
				continue;
			}
			
			for(int i = 0; i < identifiers.length; i++)
			{
				if(s.compareTo(identifiers[i]) == 0)
				{
					iden = i;
					next = true;
					break;
				}
				else
				{
					iden = -1;
				}
			}
			
		}
		
		return data;
	}
}
