package GFX;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Engine.Object.Component;
import Engine.Object.GameObjectInfo;
import Game.Player;
import Utility.Util;

public class Assets 
{
	static Map<String, Sprite> assets = new HashMap<String, Sprite>();
	
	
	
	static void addGridSheet(BufferedImage sheet, Dimension sheetSize, Dimension cellSize, String[] names, int res)
	{
		for(int y = 0; y < sheetSize.height; y += cellSize.height)
		{
			for(int x = 0; x < sheetSize.width; x += cellSize.width)
			{
				int i = ((((y / cellSize.height) - 1) * (sheetSize.width / cellSize.width)) + (x / cellSize.width)) - 1;
				if(i > names.length)
					return;
				addImage(new Sprite(crop(sheet, x, y, cellSize.width, cellSize.height), res, names[i]));
			}
		}
	}
	
	static void addImage(Sprite img)
	{
		assets.put(img.name, img);
	}
	

	static BufferedImage crop(BufferedImage sheet, int x, int y, int width, int height)
	{
		return sheet.getSubimage(x, y, width, height);
	}
	
	static Sprite get(String key)
	{
		return assets.get(key);
	}
	
	public enum Type
	{
		STATIC,
		SHEET,
		ANIMATION
	}
	
	/*
	 * Format:
	 * Image - fileName.png : imageName : resolution;
	 * Sheet - sheetName.png : resolution : sheetWidth : sheetHeight : imgCellWidth : imgCellHeight : imgName1, imgName2;
	 */
	public static void init() throws Exception
	{
		Util.log("Intializing assets...", "", Util.Message.MESSAGE, "ASSETS");
		String[] config = Util.loadFileAsString("res/config/assets.cfg").split(";");
		for(String s : config)
		{
			if(s.isBlank())
				continue;
			
			String[] spriteInfo = Util.loadInfo(s, ":", new String[] {"File", "Name", "Res"});
			BufferedImage img = null;
			Util.log("Loading image:\n", "File path: " + spriteInfo[0] + "\n"  +
										"Name: " + spriteInfo[1]  + "\n" +
										"Resoultion: " + spriteInfo[2]	 , Util.Message.MESSAGE, "ASSETS");

			img = ImageLoader.loadImage("/textures/" + spriteInfo[0]);
			addImage(new Sprite(img, Util.parseInt(spriteInfo[2]), spriteInfo[1]));
			Util.log("Loaded image", spriteInfo[1], Util.Message.MESSAGE, "ASSETS");
		}
		Util.logS("Intialized assets!", "ASSETS");
	}
}
