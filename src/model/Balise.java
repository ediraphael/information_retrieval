package model;

import java.util.Set;
import java.util.StringTokenizer;

public enum Balise
{
	DOCNO("DOCNO", 5)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addDocNo(textContent.trim());
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.DOCNO, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getDocNo();
		}
	},
	FILEID("FILEID", 5)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addFileId(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.FILEID, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getFileId();
		}
	},
	FIRST("FIRST", 4)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addFirst(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.FIRST, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getFirst();
		}
	},
	SECOND("SECOND", 3)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addSecond(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.SECOND, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getSecond();
		}
	},
	HEAD("HEAD", 5)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addHead(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.HEAD, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getHead();
		}
	},
	BYLINE("BYLINE", 2)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addByLine(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.BYLINE, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getByLine();
		}
	},
	DATELINE("DATELINE", 2)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addDateLine(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.DATELINE, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getDateLine();
		}
	},
	TEXT("TEXT", 1)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addText(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.TEXT, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getText();
		}
	},
	NOTE("NOTE", 1)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addNote(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.NOTE, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getNote();
		}
	},
	UNK("UNK", 1)
	{
		public void initApDocument(ApDocument apDocument, String textContent)
		{
			apDocument.addUnk(textContent);
		}

		public void stemmerApDocumentPartWord(Stemmer stemmer, String token, String docNo, int wordPosition)
		{
			String wordStemmer = stemmer.stemmerWord(token);
			if (!(wordStemmer.equals("")))
			{
				if (!Dictionary.getStopWords().contains(wordStemmer))
				{
					Dictionary.addWord(wordStemmer, docNo.trim(), 1, new WordPosition(Balise.UNK, wordPosition));
				}
			}
		}

		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return apDocument.getUnk();
		}
	},
	INCONU("#TEXT", 0)
	{
		public String getApDocumentTextContent(ApDocument apDocument)
		{
			return "";
		}
	};

	private String representation;
	private double weightRatio;

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

	}

	public void stemmerApDocumentPart(Stemmer stemmer, String docNo, String textContent)
	{
		StringTokenizer tokens = new StringTokenizer(textContent, " ''``;,.\n\t\r");
		int wordPosition = 1;
		while (tokens.hasMoreElements())
		{
			String token = tokens.nextToken();
			if (!Dictionary.getStopWords().contains(token))
			{
				stemmerApDocumentPartWord(stemmer, token, docNo, wordPosition);
			}
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
		int maxOccurency = 0;
		Set<String> dictionaryKeys = Dictionary.getElements().keySet();
		for (String dictionaryKey : dictionaryKeys)
		{
			if (Dictionary.getElements().get(dictionaryKey).containsKey(apDocument.getDocNo()))
			{
				maxOccurency = Math.max(maxOccurency, Dictionary.getElements().get(dictionaryKey).get(apDocument.getDocNo()).getOccurency());
			}
		}

		for (String dictionaryKey : dictionaryKeys)
		{
			if (Dictionary.getElements().get(dictionaryKey).containsKey(apDocument.getDocNo()))
			{
				double calculateRatio = 0;
				for (WordPosition wordPosition : Dictionary.getElements().get(dictionaryKey).get(apDocument.getDocNo()).getWordPositions())
				{
					calculateRatio += wordPosition.getBalise().getWeightRatio();
				}
				Dictionary.getElements().get(dictionaryKey).get(apDocument.getDocNo()).setWeight(calculateRatio / maxOccurency);
				/*
				 * System.out.println(dictionaryKey + " occurence : " + Dictionary.getElements().get(dictionaryKey).get(apDocument.getDocNo()).getOccurency() + "  ratio: " + calculateRatio + " maxOcc:" + maxOccurency + " poid:" + calculateRatio / maxOccurency); if ((calculateRatio / maxOccurency) > 1) { try { Thread.sleep(5000); } catch (InterruptedException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
				 */
			}
		}

	}

	private Balise(String representation, double weightRatio)
	{
		this.representation = representation;
		this.weightRatio = weightRatio;
	}

	public String getRepresentation()
	{
		return representation;
	}

	public void setRepresentation(String representation)
	{
		this.representation = representation;
	}

	public double getWeightRatio()
	{
		return weightRatio;
	}

	public void setWeightRatio(double weightRatio)
	{
		this.weightRatio = weightRatio;
	}
}
