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

	private void executeEnd(ArrayList<String> stemmerQuerry, HashMap<String, Double> docValue)
	{
		if (stemmerQuerry.size() >= 2)
		{
			for (String docNo : docValue.keySet())
			{
				ArrayList<String> stemmerQuerryCopy = new ArrayList<String>(stemmerQuerry);
				for (String wordQuerry : stemmerQuerry)
				{
					if (!Dictionary.getElements().containsKey(wordQuerry) || !Dictionary.getElements().get(wordQuerry).containsKey(docNo))
					{
						stemmerQuerryCopy.remove(wordQuerry);
					}
				}

				if (stemmerQuerryCopy.size() > 1)
				{
					double ecart = Double.MAX_VALUE;
					String wordQuerry = stemmerQuerryCopy.get(0);
					stemmerQuerryCopy.remove(wordQuerry);
					for (WordPosition wordPositionNext : Dictionary.getElements().get(wordQuerry).get(docNo).getWordPositions())
					{
						ArrayList<String> stemmerQuerryCopy2 = new ArrayList<String>(stemmerQuerryCopy);
						ecart = Math.min(recursiveQuerySearch(stemmerQuerryCopy2, docNo, wordPositionNext), ecart);
					}
					docValue.put(docNo, docValue.get(docNo) + (((ecart == 0 ? 1.0 : 1.0 / ecart)) * (((stemmerQuerryCopy.size() + 1.0) * (stemmerQuerryCopy.size() + 1.0)) / stemmerQuerry.size()) * 50));
					System.out.println("stemmerQuery size : " + stemmerQuerry.size() + ", copy size : " + stemmerQuerryCopy.size() + 1);
					System.out.println("gain : " + (((ecart == 0 ? 1.0 : 1.0 / ecart)) * (((stemmerQuerryCopy.size() + 1.0) * (stemmerQuerryCopy.size() + 1.0)) / stemmerQuerry.size()) * 50));
					System.out.println(docNo + "->" + ecart + ", value : " + docValue.get(docNo));
				}
			}
		}

		this.result = docValue;
		System.out.println(entriesSortedByReverseValues(docValue));
		System.out.println(docValue.size());
	}

	public void executeIntersection()
	{
		ArrayList<String> stemmerQuerry = stemmerQuery();
		HashMap<String, Double> docValue = new HashMap<String, Double>();
		boolean firstOne = true;
		ArrayList<String> docNoRetain = new ArrayList<String>();

		for (String wordQuerry : stemmerQuerry)
		{
			if (Dictionary.getElements().containsKey(wordQuerry))
			{
				Set<String> docNoList = Dictionary.getElements().get(wordQuerry).keySet();
				if (firstOne)
				{
					firstOne = false;
					docNoRetain.addAll(docNoList);
				}else
				{
					docNoRetain.retainAll(docNoList);
				}
			}
		}

		for (String wordQuerry : stemmerQuerry)
		{
			if (Dictionary.getElements().containsKey(wordQuerry))
			{
				HashMap<String, DataReferency> wordDictionary = Dictionary.getElements().get(wordQuerry);
				for (String docNo : docNoRetain)
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
		executeEnd(stemmerQuerry, docValue);
	}

	public void executeUnion()
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
		executeEnd(stemmerQuerry, docValue);
	}

	public double recursiveQuerySearch(ArrayList<String> stemmerQuerry, String docNo, WordPosition wordPosition)
	{
		double ecart = Double.MAX_VALUE;

		String wordQuerry = stemmerQuerry.get(0);
		ArrayList<String> stemmerQuerryCopy = new ArrayList<String>(stemmerQuerry);
		stemmerQuerryCopy.remove(wordQuerry);

		for (WordPosition wordPositionNext : Dictionary.getElements().get(wordQuerry).get(docNo).getWordPositions())
		{
			ecart = Math.min(ecart, Math.abs((wordPositionNext.getPosition() + (wordPosition.getBalise() != wordPositionNext.getBalise() ? 50 : 0)) - wordPosition.getPosition() - 1));
		}
		if (stemmerQuerryCopy.size() > 2)
		{
			double minEcart = Double.MAX_VALUE;
			for (WordPosition wordPositionNext : Dictionary.getElements().get(wordQuerry).get(docNo).getWordPositions())
			{
				minEcart = Math.min(recursiveQuerySearch(stemmerQuerryCopy, docNo, wordPositionNext), minEcart);
			}
			ecart += minEcart;
		}
		return ecart;
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
