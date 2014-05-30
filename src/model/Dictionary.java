package model;

import java.util.HashMap;

public class Dictionary
{
	private static HashMap<String, HashMap<String, DataReferency>> elements;

	public Dictionary()
	{
		elements = new HashMap<String, HashMap<String, DataReferency>>();
	}

	public static void addWord(String word, String docID, int ratio)
	{
		if (!Dictionary.getElements().containsKey(word))
		{
			Dictionary.getElements().put(word, new HashMap<String, DataReferency>());
			Dictionary.getElements().get(word).put(docID, new DataReferency(1 * ratio));
		} else
		{
			if (!Dictionary.getElements().get(word).containsKey(docID))
			{
				Dictionary.getElements().get(word).put(docID, new DataReferency(1 * ratio));
			} else
			{
				Dictionary.getElements().get(word).get(docID).occurency = Dictionary.getElements().get(word).get(docID).occurency + 1 * ratio;
			}
		}
	}

	public static void reset()
	{
		elements = new HashMap<String, HashMap<String, DataReferency>>();
	}

	public static HashMap<String, HashMap<String, DataReferency>> getElements()
	{
		return elements;
	}

	public static void setElements(HashMap<String, HashMap<String, DataReferency>> elements)
	{
		Dictionary.elements = elements;
	}
}
