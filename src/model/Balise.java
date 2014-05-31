package model;

public enum Balise
{
	DOCNO("DOCNO")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addDocNo(textContent);
		}
	},
	FILEID("FILEID")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addFileId(textContent);
		}
	},
	FIRST("FIRST")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addFirst(textContent);
		}
	},
	SECOND("SECOND")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addSecond(textContent);
		}
	},
	HEAD("HEAD")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addHead(textContent);
		}
	},
	BYLINE("BYLINE")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addByLine(textContent);
		}
	},
	DATELINE("DATELINE")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addDateLine(textContent);
		}
	},
	TEXT("TEXT")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addText(textContent);
		}
	},
	NOTE("NOTE")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addNote(textContent);
		}
	},
	UNK("UNK")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addNote(textContent);
		}
	},
	INCONU("#TEXT")
	{

	};

	private String representation;

	public static Balise getBaliseByRepresentation(String representation)
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

	public void initApDocument(ApDocument apDocument, String textContent)
	{

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
