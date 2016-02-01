package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.awt.event.KeyEvent;

import core.Screen;
import misc.KeyPressListener;
import panels.console.Console;

public abstract class Panel implements KeyPressListener
{
	public static Font FONT = new Font("Ubuntu", Font.PLAIN, Console.TEXT_HEIGHT-2);
	public static final int WIDTH = 350;
	private static BufferedImage image;
	private static Graphics g;
	private LinkedList<String> text;
	public static final int TEXT_HEIGHT = 16; // Gibt an wie weit die Zeilen auseinander sind

	public Panel()
	{
		// Image
		image = new BufferedImage(WIDTH, Screen.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g = image.createGraphics();
		g.setColor(new Color(0,0,0,250));
		g.fillRect(0, 0, WIDTH, Screen.HEIGHT);
		int i = 0;
		g.setColor(Color.WHITE);
		g.drawLine(WIDTH, 0, WIDTH, Screen.HEIGHT);

		// Text
		text = new LinkedList<String>();
	}

	public void render()
	{
		// Image rendern
		Screen.g().drawImage(image, 0,0, null);

		// Text rendern
		Screen.g().setColor(Color.WHITE);
		Screen.g().setFont(FONT);
		if (isTopAligned())
		{
			for (int i = 0; i < getText().size(); i++)
			{
				Screen.g().drawString(getText().get(i), 4, i * TEXT_HEIGHT + TEXT_HEIGHT - 3);
			}
		}
		else
		{
			for (int i = 0; i < getText().size(); i++)
			{
				Screen.g().drawString(getText().get(i), 4, (Screen.HEIGHT - TEXT_HEIGHT * 2) - (i * TEXT_HEIGHT));
			}
		}
	}

	// Getter
	protected abstract boolean isTopAligned();
	protected LinkedList<String> getText() { return text; }
}
