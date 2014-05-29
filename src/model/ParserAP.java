package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParserAP
{
	public void loadAllApDocumentByDocList()
	{
		try
		{
			Stemmer stemmer = new Stemmer();
			BufferedReader inputF = new BufferedReader(new FileReader("./bin/document/ap/doclist.txt"));
			String line = null;
			while ((line = inputF.readLine()) != null)
			{
				ApDocument documentTest = this.loadApDocument(line.split(" ")[1]);
				if (documentTest != null)
				{
					StringTokenizer tokens = new StringTokenizer(documentTest.getText(), " ''``;,.\n\t\r");

					while (tokens.hasMoreElements())
					{
						String token = tokens.nextToken();
						stemmer.stemmerWord(token, line.split(" ")[1]);
					}
				}
			}
			inputF.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void loadAllApDocumentByFolder()
	{
		Stemmer stemmer = new Stemmer();
		File repertoire = new File("./bin/document/ap/");
		File[] fichiesrTxt = repertoire.listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(File repertoire, String nomFichier)
			{
				return nomFichier.startsWith("AP");
			}
		});
		ArrayList<String> listeFichierTxt = new ArrayList<String>();
		for (File fichier : fichiesrTxt)
		{
			listeFichierTxt.add(fichier.getName());
			ApDocument apDocument = null;

			try
			{
				String fileContent = "<DOCS>" + getFileContent(fichier.getPath()) + "</DOCS>";
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder;
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = null;
				try
				{
					doc = dBuilder.parse(new InputSource(new StringReader(fileContent)));
					doc.getDocumentElement().normalize();
					NodeList docsList = doc.getElementsByTagName("DOC");
					Node node = null;
					int i = 0;
					while ((node = docsList.item(i)) != null)
					{
						apDocument = new ApDocument();
						NodeList nodeElements = node.getChildNodes();
						Node nodeElement = null;
						int j = 0;
						while ((nodeElement = nodeElements.item(j)) != null)
						{
							switch (nodeElement.getNodeName().toUpperCase().trim())
							{
							case "DOCNO":
								apDocument.addDocNo(nodeElement.getTextContent().trim());
								break;
							case "FILEID":
								apDocument.addFileId(nodeElement.getTextContent().trim());
								break;
							case "FIRST":
								apDocument.addFirst(nodeElement.getTextContent().trim());
								break;
							case "SECOND":
								apDocument.addSecond(nodeElement.getTextContent().trim());
								break;
							case "HEAD":
								apDocument.addHead(nodeElement.getTextContent().trim());
								break;
							case "BYLINE":
								apDocument.addByLine(nodeElement.getTextContent().trim());
								break;
							case "DATELINE":
								apDocument.addDateLine(nodeElement.getTextContent().trim());
								break;
							case "TEXT":
								apDocument.addText(nodeElement.getTextContent().trim());
								break;
							case "NOTE":
								apDocument.addNote(nodeElement.getTextContent().trim());
								break;
							default:
								break;
							}
							j++;
						}

						StringTokenizer tokens = new StringTokenizer(apDocument.getText(), " ''``;,.\n\t\r");

						while (tokens.hasMoreElements())
						{
							String token = tokens.nextToken();
							stemmer.stemmerWord(token, apDocument.getDocNo());
						}

						i++;
					}
				} catch (SAXException | IOException e)
				{
					System.err.println("Erreur de parsage : " + e.getMessage());
				}
			} catch (ParserConfigurationException e)
			{
				System.err.println("Erreur : " + e.getMessage());
			}
		}
	}

	public ApDocument loadApDocument(String documentName)
	{
		ApDocument apDocument = null;

		String fileName = documentName.split("-")[0];
		try
		{
			String fileContent = "<DOCS>" + getFileContent("./bin/document/ap/" + fileName) + "</DOCS>";
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = null;
			try
			{
				doc = dBuilder.parse(new InputSource(new StringReader(fileContent)));
				doc.getDocumentElement().normalize();
				NodeList docsList = doc.getElementsByTagName("DOC");
				Node node = null;
				int i = 0;
				boolean registerDocument = false;
				while ((node = docsList.item(i)) != null)
				{
					NodeList nodeElements = node.getChildNodes();
					Node nodeElement = null;
					int j = 0;
					while ((nodeElement = nodeElements.item(j)) != null)
					{
						if (nodeElement.getNodeName().toUpperCase().trim().equals("DOCNO") && nodeElement.getTextContent().trim().equals(documentName))
						{
							registerDocument = true;
							apDocument = new ApDocument();
							apDocument.addDocNo(nodeElement.getTextContent().trim());
						} else if (registerDocument)
						{
							switch (nodeElement.getNodeName().toUpperCase().trim())
							{
							case "FILEID":
								apDocument.addFileId(nodeElement.getTextContent().trim());
								break;
							case "FIRST":
								apDocument.addFirst(nodeElement.getTextContent().trim());
								break;
							case "SECOND":
								apDocument.addSecond(nodeElement.getTextContent().trim());
								break;
							case "HEAD":
								apDocument.addHead(nodeElement.getTextContent().trim());
								break;
							case "BYLINE":
								apDocument.addByLine(nodeElement.getTextContent().trim());
								break;
							case "DATELINE":
								apDocument.addDateLine(nodeElement.getTextContent().trim());
								break;
							case "TEXT":
								apDocument.addText(nodeElement.getTextContent().trim());
								break;
							case "NOTE":
								apDocument.addNote(nodeElement.getTextContent().trim());
								break;
							default:
								break;
							}
						}
						j++;
					}
					i++;
					if (registerDocument)
					{
						break;
					}
				}
			} catch (SAXException | IOException e)
			{
				System.err.println("Erreur de parsage : " + e.getMessage());
			}
		} catch (ParserConfigurationException e)
		{
			System.err.println("Erreur : " + e.getMessage());
		}
		return apDocument;
	}

	public static String getFileContent(String path)
	{
		try
		{
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			return new String(encoded);
		} catch (IOException e)
		{
			System.err.println("Error reading file : " + e.getMessage());
			return null;
		}

	}

	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		ParserAP parser = new ParserAP();
		// parser.loadAllApDocumentByDocList();
		parser.loadAllApDocumentByFolder();
		long end = System.currentTimeMillis();
		System.out.println("Temps de chargement : " + ((end - start)/1000.0)+"s");
		System.out.println(Stemmer.getDiction().size());
		Set<String> key = Stemmer.getDiction().keySet();
		for (String string : key)
		{
			System.out.println(string + " -> " + Stemmer.getDiction().get(string));
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
