package model;

import java.util.ArrayList;

public class DataReferency
{
	public int occurency;
	public ArrayList<Integer> wordPositions;

	public DataReferency(int occurency, ArrayList<Integer> wordPositions)
	{
		this.occurency = occurency;
		this.wordPositions = wordPositions;
	}

	public DataReferency(int occurency, int wordPosition)
	{
		this.occurency = occurency;
		this.wordPositions = new ArrayList<Integer>();
		this.wordPositions.add(wordPosition);
	}

	public int getOccurency()
	{
		return occurency;
	}

	public void setOccurency(int occurency)
	{
		this.occurency = occurency;
	}

	@Override
	public String toString()
	{
		return "occurency=" + occurency;
	}
}
