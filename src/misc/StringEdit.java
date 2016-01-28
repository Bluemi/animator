package misc;

import java.util.LinkedList;

public class StringEdit
{
	private String text;

	public StringEdit()
	{
		text = "";
	}

	public StringEdit(String text)
	{
		this.text = text;
	}

	// Counts the number of occurences of "substring"
	public int count(String substring)
	{
		int occurences = 0;
		// Alle Elemente von text durchgehen
		for (int i = 0; i < text.length(); i++)
		{
			if (text.startsWith(substring, i))
			{
				occurences++;
			}
		}
		return occurences;
	}

	public void deleteTo(int index)
	{
		text = text.substring(index);
	}

	public void deleteTo(String substring)
	{
		deleteTo(indexOf(substring) + substring.length());
	}

	// Returnt alles vor dem index
	public String getTo(int index)
	{
		return text.substring(0, index-1);
	}

	public String getTo(String substring)
	{
		int index = indexOf(substring) + 1;
		if (index == 0)
			return new String();
		return getTo(indexOf(substring)+1);
	}

	public int indexOf(String substring)
	{
		return text.indexOf(substring);
	}

	// Unterteilt den Text in Partitionen
	public String[] getStringListWithHyphen(String hyphen)
	{
		String textBackup = text;
		int length = count(hyphen);
		LinkedList<String> list = new LinkedList<String>();

		for (int i = 0; i < length; i++)
		{
			list.add(getTo(hyphen));
			deleteTo(hyphen);
		}
		list.add(text);

		text = textBackup;

		String[] stringList = new String[list.size()];
		for (int i = 0; i < stringList.length; i++)
		{
			stringList[i] = list.get(i);
		}
		return stringList;
	}

	// Unterteilt den Text in Partitionen außer sie sind "mit Quotations eingeklammert"
	public String[] getStringListWithHyphenQuotation(String hyphen)
	{
		String textBackup = text;
		String cache;
		int length = count(hyphen);
		LinkedList<String> list = new LinkedList<String>();

		for (int i = 0; i < length; i++)
		{
			if (text.startsWith("\""))
			{
				deleteTo(1);
				list.add(getTo("\""));
				deleteTo("\"");
			}
			list.add(getTo(hyphen));
			deleteTo(hyphen);
		}
		list.add(text);

		// alle Elemte die "" sind löschen
		int j = 0;
		while (j < list.size())
		{
			if (list.get(j).equals(""))
				list.remove(j);
			else
				j++;
		}

		text = textBackup;

		String[] stringList = new String[list.size()];
		for (int i = 0; i < stringList.length; i++)
		{
			stringList[i] = list.get(i);
		}
		return stringList;
	}

	public String toString() { return text; }
}
