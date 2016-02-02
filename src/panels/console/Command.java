package panels.console;

import java.util.LinkedList;

import core.Animator;
import misc.Debug;
import misc.StringEdit;

public abstract class Command
{
	public static final String TAB = "       ";

	public boolean itsMe(String text)
	{
		return text.equals(getName()) || text.startsWith(getName() + " ");
	}

	public static String[] getParams(String text)
	{
		StringEdit stringEdit = new StringEdit(text);

		if (text.indexOf(" ") == -1) // In diesem Fall gibt es keine Parameter
		{
			return new String[0];
		}

		// Wenn es doch Parameter gibt
		String[] list = stringEdit.getStringListWithHyphenQuotation(" ");
		String[] list2 = new String[list.length-1];

		for (int i = 0; i < list2.length; i++)
		{
			list2[i] = list[i+1];
		}
		return list2;
	}

	// Löscht den Namen aus dem übergebenen Text, sodass nur noch die Parameter zurückgegeben werden
	private String deleteName(String text)
	{
		if (text.equals(getName()))
		{
			return "";
		}
		else if (text.startsWith(getName() + " "))
		{
			return text.substring(getName().length());
		}
		else
		{
			Debug.warn("Command.deleteName(): Name to delete not found");
		}
		return text;
	}

	public abstract String getName();
}
