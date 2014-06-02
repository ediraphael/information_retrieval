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

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import view.LoadView;

public class ParserAP
{
	
	//TODO delete après la fin des devs de l'interface
	public void loadAllApDocumentByFolder()
	{
		Dictionary.reset();
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
							if (!(nodeElement instanceof Text))
							{
								Balise.valueOf(nodeElement.getNodeName().toUpperCase().trim()).initApDocument(apDocument, nodeElement.getTextContent().trim());
							}
							j++;
						}
						Balise.stemmerApDocument(apDocument);

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
	
	
	
	
	public void loadAllApDocumentByFolder(JProgressBar pBar, JLabel labelFileInProgress)
	{
		int nbFile;
		int nbFileLoad;
		
		Dictionary.reset();
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
		nbFile=fichiesrTxt.length;
		nbFileLoad=0;
		for (File fichier : fichiesrTxt)
		{
			labelFileInProgress.setText(fichier.toString());
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
							if (!(nodeElement instanceof Text))
							{
								Balise.valueOf(nodeElement.getNodeName().toUpperCase().trim()).initApDocument(apDocument, nodeElement.getTextContent().trim());
							}
							j++;
						}
						Balise.stemmerApDocument(apDocument);

						i++;
					}
					nbFileLoad++;
					pBar.setValue((nbFileLoad*100)/nbFile);
					System.out.println((nbFileLoad*100)/nbFile);
				}
				catch (SAXException | IOException e)
				{
					System.err.println("Erreur de parsage : " + e.getMessage());
				}
			} 
			catch (ParserConfigurationException e)
			{
				System.err.println("Erreur : " + e.getMessage());
			}
		}
		LoadView.loadlingTerminated();
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
							Balise.getBaliseByRepresentation(nodeElement.getTextContent().trim()).initApDocument(apDocument, nodeElement.getTextContent().trim());
						} 
						else if (registerDocument)
						{
							Balise.getBaliseByRepresentation(nodeElement.getTextContent().trim()).initApDocument(apDocument, nodeElement.getTextContent().trim());
						}
						j++;
					}
					i++;
					if (registerDocument)
					{
						break;
					}
				}
			} 
			catch (SAXException | IOException e)
			{
				System.err.println("Erreur de parsage : " + e.getMessage());
			}
		} 
		catch (ParserConfigurationException e)
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
		} 
		catch (IOException e)
		{
			System.err.println("Error reading file : " + e.getMessage());
			return null;
		}

	}

	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		Stemmer stemmer = new Stemmer();
		ParserAP parser = new ParserAP();
		// parser.loadAllApDocumentByDocList();
		//parser.loadAllApDocumentByFolder();
		long end = System.currentTimeMillis();
		System.out.println("Temps de chargement : " + ((end - start) / 1000.0) + "s");
		System.out.println(Dictionary.getElements().size());

		Search search = new Search("Document will discuss allegations, or measures being taken against, corrupt public officials of any governmental jurisdiction worldwide.");
		search.execute();
		// Set<String> keys = Dictionary.getElements().keySet();
		// for (String string : keys)
		// {
		// System.out.println(string + "->" + Dictionary.getElements().get(string));
		// try
		// {
		// Thread.sleep(1000);
		// } catch (InterruptedException e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}
}
