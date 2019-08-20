package service;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import model.IBMail;
//reference from: http://www.itcuties.com/java/javamail-read-email/
//reference from http://www.coderpanda.com/reading-email-in-java-using-imap/
//reference from https://www.tutorialspoint.com/javamail_api/javamail_api_quick_guide.htm

public class InBoxService {
	
	public static InBoxService instance;
	public Message[] listInbox;
	int numInBox;
	int numPage;
	int numPerPage;
	final static String username = "cc00770xprj321x@gmail.com";
	final static String password = "funixcc00770x";
	
	//load inbox data by constructor
	public InBoxService() throws Exception {
		listInbox = loadMail();
		numInBox = listInbox.length;
		numPerPage = 5;
		if (numInBox % numPerPage == 0) {
			numPage = numInBox / numPerPage;
		} else {
			numPage = numInBox / numPerPage + 1;
		}
	}

	public int getNumInBox() {
		return numInBox;
	}

	public void setNumInBox(int numInBox) {
		this.numInBox = numInBox;
	}

	public int getNumPage() {
		return numPage;
	}

	public void setNumPage(int numPage) {
		this.numPage = numPage;
	}

		//create instance to call method
		public static InBoxService getInstance() throws Exception {
			if (instance == null) return new InBoxService();
			else {
				return instance;
			}
		}
	
	//get list mail by page index
	public ArrayList<IBMail> getMailByPage(int idx) throws Exception{
		ArrayList<IBMail> list = new ArrayList<>();
		int r = numInBox % numPerPage;
		int start = 0;
		int end = 0;
		String from = "";
		String to = "";
		String date = "";
		String title = "";
		String content = "";
		if (idx == numPage && r != 0) {
			 start = numInBox - numPerPage*(idx-1) - 1;
			 end = start - r + 1;
			 
		} else {
			start = numInBox - numPerPage*(idx-1) - 1;
			end = start - numPerPage + 1;
		}
		 for (int i = start; i > end -1; i--) {
			 from = listInbox[i].getFrom()[0].toString();
			 to = listInbox[i].getAllRecipients()[0].toString();
			 date = listInbox[i].getReceivedDate().toString();
			 title = listInbox[i].getSubject().toString();
			 content = getContent(listInbox[i]);
			 IBMail mail = new IBMail(from, to, date, title, content, i);
			 list.add(mail);
		 }
		return list;
	}
	
	
	
	//load mail by id
	public IBMail getMailById(int id, int idx) throws Exception {
		ArrayList<IBMail> list = getMailByPage(idx);
		for (IBMail mail : list) {
			if (id == mail.getId()) return mail;
		}
		return null;
	}
	
	
	//load inbox by imap
	public static Message[] loadMail() throws Exception {
		Properties connectionProperties = new Properties();
		//ArrayList<IBMail> list = new ArrayList<>();
		Session session = Session.getDefaultInstance(connectionProperties,null);
		Store store = session.getStore("imaps");
		String server = "imap.googlemail.com";
		store.connect(server, username, password);
		Folder inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_ONLY);
		Message[] messages = inbox.getMessages();
		//Message[] msg = new Message[messages.length];
//		for (int i=0; i < messages.length; i++) {
//			msg[i] = messages[messages.length-i-1];
//		}
		//return msg;
		return messages;
	}
	
	 public String getContent(Part part) throws Exception {

	        String result = "";

	        //check if the content is plain text
	        if (part.isMimeType("text/plain")) {
	        	//System.out.println("text/plain");
//	            result += (String) part.getContent();
	        } //check if the content has attachment
	        else if (part.isMimeType("multipart/*")) {
	        	//System.out.println("multipart");
	            Multipart multipart = (Multipart) part.getContent();
	            int count = multipart.getCount();
	            for (int i = 0; i < count; i++) {
	                result += getContent(multipart.getBodyPart(i));
	            }
	        } //check if the content is a nested message
	        else if (part.isMimeType("message/rfc822")) {
	        	//System.out.println("nested message");
	            result += getContent((Part) part.getContent());
	        } //check if the content is an inline image
	        else if (part.isMimeType("image/jpeg")) {
	        	//System.out.println("inline image");
	        } else if (part.getContentType().contains("image/")) {
	        	//System.out.println("image");
	        }
	        else {
	            Object o = part.getContent();
	            if (o instanceof String) {
	                result += (String) o;
	                //System.out.println("String");
	            } else if (o instanceof InputStream) {
//	            	System.out.println("Input stream");
//	            	InputStream is = (InputStream) o;
//	                int c;
//	                while ((c = is.read()) != -1)
//	                   System.out.write(c);
//	                is.close();
	            }
	            else {
	                //result += o.toString();
	                //System.out.println("Other");
	            }
	        }
	        return result;

	    }	
}
