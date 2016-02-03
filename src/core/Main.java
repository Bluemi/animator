package core;

import java.util.Timer;
import java.util.TimerTask;
import java.util.LinkedList;
import java.util.HashMap;
import java.lang.StringBuffer;

import core.Animator;
import misc.Debug;
import misc.StringEdit;
import misc.math.Vec3D;
import misc.math.Angle;
import misc.math.Point;
import misc.Randomizer;
import panels.console.Command;

public class Main
{
	public static final int FRAME_INTERVAL = 35; // intervall in dem tick() und render() ausgeführt werden

	private Animator animator;

	public static void main(String args[])
	{
		new Main().run(); // neue Main-instanz erstellen und deren run funktion ausführen
	}

	private void test()
	{
	}

	private void run()
	{
		test();
		init(); // initalisieren

		new Timer().scheduleAtFixedRate(new TimerTask() // wiederhole alle <FRAME_INTERVAL> ms
		{
			@Override
			public void run()
			{
				tick(); // tick
				render(); // render
			}
		}, FRAME_INTERVAL, FRAME_INTERVAL);
	}

	private void init()
	{
		animator = new Animator();
		Debug.init();
		Screen.init(animator); // init Screen
		Screen.get().addMouseMotionListener(animator);
		Screen.get().addKeyListener(animator);
	}

	public static void quit()
	{
		Debug.note("closed properly"); // beende das programm
		System.exit(0);
	}

	private void tick()
	{
		animator.tick();
	}

	private void render()
	{
		Screen.render(); // rendere die menuList auf den Screen
	}
}
