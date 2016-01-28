package panels.console;

import java.awt.event.KeyEvent;
import java.lang.StringBuffer;

import core.Animator;
import drawobjects.DrawObject;
import misc.Debug;
import panels.Panel;
import panels.console.Command;
import view.Cam;

public class Console extends Panel
{
	private Cam handledCam;
	private DrawObject handledDrawObject;
	private String preText; // Text, der in der Commandozeile steht und NICHT bearbeitet werden kann
	private String commandLine; // Text, der in der Commandozeile steht und bearbeitet werden kann

	private Animator animator; // Damit die Commands auf den Animator zugreifen können. Idee wäre auch Singleton

	public Console(Animator animator)
	{
		this.animator = animator;
		preText = "$ ";
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
		Command command = Command.getCommandByText(text);
		if (command != null)
		{
			command.execute(this, Command.getParams(text));
		}
		else
		{
			if (text.indexOf(" ")  == -1) // Position von Space
			{
				write("command \"" + text + "\" not found!");
			}
			else
			{
				write("command \"" + text.substring(0, text.indexOf(" ")) + "\" not found!");
			}
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

	// Setter
	public void setHandledCam(Cam cam)
	{
		handledCam = cam;
	}

	public void setHandledObject(DrawObject drawObject)
	{
		handledDrawObject = drawObject;
	}

	// Getter
	public Animator getAnimator() { return animator; } // Damit die Commands auf den Animator zugreifen können
	@Override protected boolean isTopAligned() { return false; }
	private String getWriteableLine() { return getText().get(0); }
	public Cam getHandledCam() { return handledCam; }
}
