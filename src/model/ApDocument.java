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
	private String note;
	private String unk;

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
		this.note = "";
		this.unk = "";
	}

	public ApDocument(String docNo, String fileId, String first, String second, String head, String byLine, String dateLine, String text, String note, String unk)
	{
		this.docNo = docNo;
		this.fileId = fileId;
		this.first = first;
		this.second = second;
		this.head = head;
		this.byLine = byLine;
		this.dateLine = dateLine;
		this.text = text;
		this.note = note;
		this.unk = unk;
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

	public void addNote(String note)
	{
		this.note += note + " ";
	}

	public void addUnk(String unk)
	{
		this.unk += unk + " ";
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
		return fileId.trim();
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	public String getFirst()
	{
		return first.trim();
	}

	public void setFirst(String first)
	{
		this.first = first;
	}

	public String getSecond()
	{
		return second.trim();
	}

	public void setSecond(String second)
	{
		this.second = second;
	}

	public String getHead()
	{
		return head.trim();
	}

	public void setHead(String head)
	{
		this.head = head.trim();
	}

	public String getByLine()
	{
		return byLine.trim();
	}

	public void setByLine(String byLine)
	{
		this.byLine = byLine;
	}

	public String getDateLine()
	{
		return dateLine.trim();
	}

	public void setDateLine(String dateLine)
	{
		this.dateLine = dateLine;
	}

	public String getText()
	{
		return text.trim();
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getNote()
	{
		return note.trim();
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getUnk()
	{
		return unk.trim();
	}

	public void setUnk(String unk)
	{
		this.unk = unk;
	}

	@Override
	public String toString()
	{
		return this.docNo + "\n" + this.fileId + "\n" + this.first + "\n" + this.second + "\n" + this.head + "\n" + this.byLine + "\n" + this.unk + "\n" + this.dateLine + "\n" + this.text + "\n" + this.note + "\n";
	}
}
