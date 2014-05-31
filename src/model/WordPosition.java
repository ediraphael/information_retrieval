package model;

public class WordPosition
{
	private Balise balise;
	private int position;

	public WordPosition(Balise balise, int position)
	{
		this.balise = balise;
		this.position = position;
	}

	public Balise getBalise()
	{
		return balise;
	}

	public void setBalise(Balise balise)
	{
		this.balise = balise;
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

}
