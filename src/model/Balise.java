package model;

import java.util.StringTokenizer;

public enum Balise
{
	DOCNO("DOCNO")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addDocNo(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.DOCNO, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getDocNo();
		}
	},
	FILEID("FILEID")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addFileId(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.FILEID, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getFileId();
		}
	},
	FIRST("FIRST")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addFirst(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.FIRST, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getFirst();
		}
	},
	SECOND("SECOND")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addSecond(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.SECOND, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getSecond();
		}
	},
	HEAD("HEAD")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addHead(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.HEAD, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getHead();
		}
	},
	BYLINE("BYLINE")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addByLine(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.BYLINE, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getByLine();
		}
	},
	DATELINE("DATELINE")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addDateLine(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.DATELINE, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getDateLine();
		}
	},
	TEXT("TEXT")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addText(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.TEXT, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getText();
		}
	},
	NOTE("NOTE")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addNote(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.NOTE, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getNote();
		}
	},
	UNK("UNK")
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addUnk(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			stemmer.stemmerWord(token, docNo, 1, new WordPosition(Balise.UNK, wordPosition));
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getUnk();
		}
	},
	INCONU("#TEXT")
	{
		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return "";
		}
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

	public String getApDocumentTextContent(ApDocument apDocument)
	{
		return null;
	}

	public void initApDocument(ApDocument apDocument, String textContent)
	{

	}

	public void stemmerTextContent(String textContent)
	{

	}

	public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
	{
		System.out.println("ici");
	}

	public void stemmerApDocumentPart(Stemmer stemmer, String docNo, String textContent)
	{
		StringTokenizer tokens = new StringTokenizer(textContent, " ''``;,.\n\t\r");
		int wordPosition = 1;
		while (tokens.hasMoreElements())
		{
			String token = tokens.nextToken();
			stemmerApDocumentPartWord(stemmer, token, docNo, wordPosition);
			wordPosition++;
		}
	}

	public static void stemmerApDocument(ApDocument apDocument)
	{
		Stemmer stemmer = new Stemmer();
		for (Balise balise : Balise.values())
		{
			balise.stemmerApDocumentPart(stemmer, apDocument.getDocNo(), balise.getApDocumentTextContent(apDocument));
		}
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
