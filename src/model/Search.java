package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.swing.DefaultListModel;

public class Search
{
	private String query;
	private HashMap<String, Double> result;

	public Search(String query, HashMap<String, Double> result)
	{
		this.query = query;
		this.result = result;
	}

	public Search(String query)
	{
		this.query = query;
		this.result = new HashMap<String, Double>();
	}

	static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map)
	{
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>()
		{
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2)
			{
				return e1.getValue().compareTo(e2.getValue());
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByReverseValues(Map<K, V> map)
	{
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(new Comparator<Map.Entry<K, V>>()
		{
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2)
			{
				return e2.getValue().compareTo(e1.getValue());
			}
		});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	public void execute()
	{
		ArrayList<String> stemmerQuerry = stemmerQuery();
		HashMap<String, Double> docValue = new HashMap<String, Double>();
		for (String wordQuerry : stemmerQuerry)
		{
			if (Dictionary.getElements().containsKey(wordQuerry))
			{
				HashMap<String, DataReferency> wordDictionary = Dictionary.getElements().get(wordQuerry);
				Set<String> docNoList = wordDictionary.keySet();
				for (String docNo : docNoList)
				{
					if (docValue.containsKey(docNo))
					{
						docValue.put(docNo, docValue.get(docNo) + wordDictionary.get(docNo).getWeight());
					} else
					{
						docValue.put(docNo, wordDictionary.get(docNo).getWeight());
					}
				}
			}
		}
		this.result = docValue;
		System.out.println(entriesSortedByReverseValues(docValue));
		System.out.println(this.getListResultOrdered());
		System.out.println(docValue.size());
	}

	public ArrayList<String> stemmerQuery()
	{
		Stemmer stemmer = new Stemmer();
		StringTokenizer tokens = new StringTokenizer(this.query, " ''``;,.\n\t\r");
		ArrayList<String> stemmerQuerry = new ArrayList<String>();
		while (tokens.hasMoreTokens())
		{
			String token = tokens.nextToken();
			stemmerQuerry.add(stemmer.stemmerWord(token));
		}
		System.out.println(stemmerQuerry);
		return stemmerQuerry;
	}

	public DefaultListModel<String> getListResultOrdered()
	{
		DefaultListModel<String> returnList = new DefaultListModel<String>();
		for (Map.Entry<String, Double> entry : entriesSortedByReverseValues(this.result))
		{
			returnList.addElement(entry.getKey());
		}
		return returnList;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public HashMap<String, Double> getResult()
	{
		return result;
	}

	public void setResult(HashMap<String, Double> result)
	{
		this.result = result;
	}
}
