package model;

public class ApDocument
{
	private String docNo;
	private String fileId;
	private String first;
	private String second;
	private String head;
	private String byLine;
	private String dateLine;
	private String text;

	public ApDocument()
	{
		this.docNo = "";
		this.fileId = "";
		this.first = "";
		this.second = "";
		this.head = "";
		this.byLine = "";
		this.dateLine = "";
		this.text = "";
	}

	public ApDocument(String docNo, String fileId, String first, String second, String head, String byLine, String dateLine, String text)
	{
		this.docNo = docNo;
		this.fileId = fileId;
		this.first = first;
		this.second = second;
		this.head = head;
		this.byLine = byLine;
		this.dateLine = dateLine;
		this.text = text;
	}

	public void addDocNo(String docNo)
	{
		this.docNo += docNo + " ";
	}

	public void addFileId(String fileId)
	{
		this.fileId += fileId + " ";
	}

	public void addFirst(String first)
	{
		this.first += first + " ";
	}

	public void addSecond(String second)
	{
		this.second += second + " ";
	}

	public void addHead(String head)
	{
		this.head += head + " ";
	}

	public void addByLine(String byLine)
	{
		this.byLine += byLine + " ";
	}

	public void addDateLine(String dateLine)
	{
		this.dateLine += dateLine + " ";
	}

	public void addText(String text)
	{
		this.text += text + " ";
	}

	public String getDocNo()
	{
		return docNo;
	}

	public void setDocNo(String docNo)
	{
		this.docNo = docNo;
	}

	public String getFileId()
	{
		return fileId;
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	public String getFirst()
	{
		return first;
	}

	public void setFirst(String first)
	{
		this.first = first;
	}

	public String getSecond()
	{
		return second;
	}

	public void setSecond(String second)
	{
		this.second = second;
	}

	public String getHead()
	{
		return head;
	}

	public void setHead(String head)
	{
		this.head = head;
	}

	public String getByLine()
	{
		return byLine;
	}

	public void setByLine(String byLine)
	{
		this.byLine = byLine;
	}

	public String getDateLine()
	{
		return dateLine;
	}

	public void setDateLine(String dateLine)
	{
		this.dateLine = dateLine;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

}
