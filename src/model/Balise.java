package model;

public enum Balise
{
	DOCNO("DOCNO"),
	FILEID("FILEID"),
	FIRST("FIRST"),
	SECOND("SECOND"),
	HEAD("HEAD"),
	BYLINE("BYLINE"),
	DATELINE("DATELINE"),
	TEXT("TEXT"),
	NOTE("NOTE");

	private String representation;

	public Balise getBaliseByRepresentation(String representation)
	{
		for (Balise balise : Balise.values())
		{
			if (balise.getRepresentation().equals(representation))
			{
				return balise;
			}
		}
		return null;
	}

	private Balise(String representation)
	{
		this.representation = representation;
	}

	public String getRepresentation()
	{
		return representation;
	}

	public void setRepresentation(String representation)
	{
		this.representation = representation;
	}
}
