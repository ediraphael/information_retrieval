package model;

public class DataReferency
{
	public int occurency;

	public DataReferency(int occurency)
	{
		this.occurency = occurency;
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
