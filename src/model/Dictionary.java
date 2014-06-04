package model;

import java.util.HashMap;
import java.util.HashSet;

public class Dictionary
{
	private static HashMap<String, HashMap<String, DataReferency>> elements;
	private static HashSet<String> stopWords;

	public Dictionary()
	{
		elements = new HashMap<String, HashMap<String, DataReferency>>();
		stopWords = new HashSet<String>();
	}

	public static void addWord(String word, String docID, int ratio, WordPosition wordPosition)
	{
		docID = docID.trim();
		if (!Dictionary.getElements().containsKey(word))
		{
			Dictionary.getElements().put(word, new HashMap<String, DataReferency>());
			Dictionary.getElements().get(word).put(docID, new DataReferency(1 * ratio, wordPosition));
		} else
		{
			if (!Dictionary.getElements().get(word).containsKey(docID))
			{
				Dictionary.getElements().get(word).put(docID, new DataReferency(1 * ratio, wordPosition));
			} else
			{
				Dictionary.getElements().get(word).get(docID).occurency = Dictionary.getElements().get(word).get(docID).occurency + 1 * ratio;
				Dictionary.getElements().get(word).get(docID).wordPositions.add(wordPosition);
			}
		}
	}

	public static void reset()
	{
		elements = new HashMap<String, HashMap<String, DataReferency>>();
	}

	public static HashSet<String> getStopWords()
	{
		return stopWords;
	}

	public static void setStopWords(HashSet<String> stopWords)
	{
		Dictionary.stopWords = stopWords;
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
