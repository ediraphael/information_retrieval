package model;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Search
{
	private String query;
	private ArrayList<String> result;

	public Search(String query, ArrayList<String> result)
	{
		this.query = query;
		this.result = result;
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
		return stemmerQuerry;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public ArrayList<String> getResult()
	{
		return result;
	}

	public void setResult(ArrayList<String> result)
	{
		this.result = result;
	}
}
