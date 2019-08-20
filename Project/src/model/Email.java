package model;

public class Email {
	String to;
	String cc;
	String title;
	String content;
	
	public Email(String to, String cc, String title, String content) {
		super();
		this.to = to;
		this.cc = cc;
		this.title = title;
		this.content = content;
	}
	
	public Email() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
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
