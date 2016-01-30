package panels.console;

import java.awt.event.KeyEvent;
import java.lang.StringBuffer;

import core.Animator;
import drawobjects.DrawObject;
import misc.Debug;
import panels.Panel;
import panels.console.commands.*;
import view.Cam;

public class Console extends Panel
{
	public static final String VOID_PRETEXT = "$ ";

	private Cam handledCam;
	private DrawObject handledDrawObject;
	private String preText; // Text, der in der Commandozeile steht und NICHT bearbeitet werden kann
	private String commandLine; // Text, der in der Commandozeile steht und bearbeitet werden kann

	private Animator animator; // Damit die Commands auf den Animator zugreifen können. Idee wäre auch Singleton

	public Console(Animator animator)
	{
		this.animator = animator;
		preText = VOID_PRETEXT;
		commandLine = "";
		getText().addFirst("");
		updateLine();
	}

	public void keyPressed(KeyEvent keyEvent)
	{
		StringBuffer line = new StringBuffer(commandLine);

		switch (keyEvent.getExtendedKeyCode())
		{
			case KeyEvent.VK_SHIFT:
				break;
			case KeyEvent.VK_CONTROL:
				break;
			case KeyEvent.VK_ALT:
				break;
			case 10: // Enter
				write(preText + line.toString());
				execute(line.toString());
				line.delete(0, line.length()); // auf "" setzen
				break;
			case KeyEvent.VK_BACK_SPACE:
				if (line.length() > 0)
					line.deleteCharAt(line.length()-1);
				break;
			default:
				line.insert(line.length(), keyEvent.getKeyChar());
				break;
		}

		commandLine = line.toString();
		updateLine();
	}

	private void updateLine()
	{
		getText().removeFirst();
		getText().addFirst(preText + commandLine);
	}

	private void execute(String text)
	{
		GlobalCommand globalcommand = GlobalCommand.getCommandByText(text);
		if (globalcommand != null)
		{
			globalcommand.execute(this, Command.getParams(text));
			return;
		}
		boolean commandFound = false;

		switch(getHandleModi())
		{
			case 0:
			{
				BasicCommand basiccommand = BasicCommand.getCommandByText(text);
				if (basiccommand != null)
				{
					basiccommand.execute(this, Command.getParams(text));
					commandFound = true;
				}
				break;
			}
			case 1: // Cam handled
			{
				CamCommand camcommand = CamCommand.getCommandByText(text);
				if (camcommand != null)
				{
					camcommand.execute(this, Command.getParams(text), getHandledCam());
					commandFound = true;
				}
				break;
			}
			case 2: // Object handled
			{
				ObjectCommand objectcommand = ObjectCommand.getCommandByText(text);
				if (objectcommand != null)
				{
					objectcommand.execute(this, Command.getParams(text), getHandledObject());
					commandFound = true;
				}
				break;
			}
		}

		// Fehlermeldung printen : Command not found
		if (!commandFound)
		{
			if (text.indexOf(" ")  == -1) // Wenn es kein Space gibt in dieser Zeile
				write("command \"" + text + "\" not found!");
			else
				write("command \"" + text.substring(0, text.indexOf(" ")) + "\" not found!");
		}
	}

	public void write(String text)
	{
		getText().add(1, text); // 1 ist die Stelle an der dieser Text eingefügt wird
	}

	public void reset()
	{
		getText().clear();
		getText().add("");
	}

	// setter
	public void setHandledCam(Cam cam)
	{
		handledCam = cam;
		if (handledCam == null)
		{
			preText = VOID_PRETEXT;
		}
		else
		{
			preText = cam.getName() + VOID_PRETEXT;
		}
	}
	public void handleObject(DrawObject drawObject)
	{
		handledDrawObject = drawObject;
		if (handledDrawObject == null)
			preText = VOID_PRETEXT;
		else
			preText = handledDrawObject.getName() + VOID_PRETEXT;
	}

	// getter
	private int getHandleModi()
	{
		if (getHandledCam() != null)
		{
			return 1; // eine cam wird gehandelt
		}
		if (getHandledObject() != null)
		{
			return 2; // Ein Object wird gehandelt
		}
		return 0; // nichts ist gehandelt
	}
	public Animator getAnimator() { return animator; } // Damit die Commands auf den Animator zugreifen können
	@Override protected boolean isTopAligned() { return false; }
	private String getWriteableLine() { return getText().get(0); }
	public Cam getHandledCam() { return handledCam; }
	public DrawObject getHandledObject() { return handledDrawObject; }
}
