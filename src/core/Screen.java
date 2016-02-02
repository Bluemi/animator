package core;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.Color;
import java.awt.Point;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.Frame;

import core.Main;
import core.Animator;
import misc.JFrameListener;
import misc.math.Position;
import misc.math.Size;

public class Screen extends Canvas
{
	public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(); // bildschirmbreite
	public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(); // bildschirmhöhe
	public static Color backgroundColor = new Color(7, 14, 14);

	private static JFrame frame; // fenster
	private static Screen instance; // singleton-instanz
	private static Graphics g; // graphics auf die BufferStrategy bs
	private static BufferStrategy bs; // backbuffer
	private static Animator animator;

	// ausgeführt von Main.init()
	public static void init(Animator animator)
	{
		Screen.animator = animator;
		instance = new Screen("Animator"); // erstellung der singleton-instanz
	}

	private Screen(String caption)
	{
		frame = new JFrame(caption); // erstellung des fensters
		frame.add(this); // diese Screen instanz zum fenster hinzufügen

		// Cursor
		BufferedImage cursorImage = new BufferedImage(16,16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0,0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);
		setCursor(blankCursor);

		frame.setUndecorated(true);
		frame.setResizable(false);

		// Fullscreen
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.getAvailableFontFamilyNames();
		GraphicsDevice d = ge.getDefaultScreenDevice();
		if (d.isFullScreenSupported()) 
		{
			//frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			d.setFullScreenWindow(frame);
		}
		frame.setVisible(true);
		requestFocusInWindow();  // das fenster wird fokusiert
	}

	// called by Main.render() in a fixed rate
	public static void render()
	{
		bs = get().getBufferStrategy(); // setzt bs auf die BufferStrategy der singleton-instanz
		if (bs == null) // falls es noch keine BufferStrategy gibt
		{
			get().createBufferStrategy(3); // erstelle sie
			return; // und beende die render funktion
		} // falls wir nun eine bufferstrategy haben
		g = bs.getDrawGraphics(); // setze g auf deren graphics
		g.setColor(backgroundColor); // setze farbe
		g.fillRect(0, 0, WIDTH, HEIGHT); // fülle den bildschirm
		animator.render();
		g.dispose(); // dispose die graphics
		bs.show(); // flip den buffer
	}

	// returt die cursor position oder null falls Maus außerhalb des fensters ist
	public static Position getCursorPosition()
	{
		Point position = get().getMousePosition();
		if (position == null) // wenn die maus außerhalb des fensters ist
		{
			return null; // return null
		} // falls sie im fenster ist
		return new Position(position.x, position.y); // returne die maus-position
	}

	public static Graphics g() { return g; } // returnt die graphics, gebraucht zum rendern
	public static Screen get() { return instance; } // returnt die singleton-instance
	public static Size getScreenSize() { return new Size(WIDTH, HEIGHT); } // returnt die fenstergröße (= bildschirmgröße)
	public static void setBackgroundColor(Color color)
	{
		backgroundColor = color;
	}
}
