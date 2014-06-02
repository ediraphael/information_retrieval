package model;

import java.util.ArrayList;

public class DataReferency
{
	public int occurency;
	public ArrayList<WordPosition> wordPositions;
	public double weight;

	public DataReferency(int occurency, ArrayList<WordPosition> wordPositions)
	{
		this.occurency = occurency;
		this.wordPositions = wordPositions;
		this.weight = 0;
	}

	public DataReferency(int occurency, WordPosition wordPosition)
	{
		this.occurency = occurency;
		this.wordPositions = new ArrayList<WordPosition>();
		this.wordPositions.add(wordPosition);
		this.weight = 0;
	}

	public ArrayList<WordPosition> getWordPositions()
	{
		return wordPositions;
	}

	public void setWordPositions(ArrayList<WordPosition> wordPositions)
	{
		this.wordPositions = wordPositions;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
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
		return " occurency= " + occurency + ", position :" + wordPositions;
	}
}
