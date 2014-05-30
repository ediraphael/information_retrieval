package model;

import java.util.HashMap;

public class Dictionary
{
	private static HashMap<String, HashMap<String, Integer>> elements;

	public Dictionary()
	{
		elements = new HashMap<String, HashMap<String, Integer>>();
	}

	public static void reset()
	{
		elements = new HashMap<String, HashMap<String, Integer>>();
	}

	public static HashMap<String, HashMap<String, Integer>> getElements()
	{
		return elements;
	}

	public static void setElements(HashMap<String, HashMap<String, Integer>> elements)
	{
		Dictionary.elements = elements;
	}
}
