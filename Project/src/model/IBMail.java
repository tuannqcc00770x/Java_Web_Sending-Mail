package model;

public class IBMail {
	String from;
	String to;
	String date;
	String title;
	String content;
	int id;
	public IBMail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IBMail(String from, String to, String date, String title, String content, int id) {
		super();
		this.from = from;
		this.to = to;
		this.date = date;
		this.title = title;
		this.content = content;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
