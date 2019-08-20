package service;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import dao.UserDAO;
import model.Email;
import model.User;

public class MailService {
	
	public static MailService instance;
	public String msg;
	public ArrayList<String> listErr;
	final String username = "cc00770xprj321x@gmail.com";
	final String password = "funixcc00770x";
	
	//create instance to call method
	public static MailService getInstance() {
		if (instance == null) return new MailService();
		else {
			return instance;
		}
	}
	
	//send an email
	public void sendEmail(Email email) {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			    return new PasswordAuthentication(username, password);
			   }
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse(email.getTo()));
	        message.setRecipients(Message.RecipientType.CC,
	                InternetAddress.parse(email.getCc()));
	        message.setSubject(email.getTitle());

	        message.setContent(email.getContent(), "text/html; charset=utf-8");

	        Transport.send(message);
			
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	//check conditions to login
	public boolean checkLogin(User user) throws Exception {
		msg = "";
		UserDAO dao = new UserDAO();
		ArrayList<User> list = dao.getAllUsers();
		if (list.isEmpty()) {
			msg = "There is no user in data!" + "<br>" + "Please signup!";
			return false;
		}
		for (User u : list) {
			if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
				return true;
			}
		}
		msg = "Wrong username or password";
		return false;
	}
	
	//get errors if exist when signup
	public ArrayList<String> checkSignup(User user) throws Exception {
		listErr = new ArrayList<>();
		UserDAO dao = new UserDAO();
		ArrayList<User> users = dao.getAllUsers();
		for (User u : users) {
			if (u.getUsername().equals(user.getUsername())) {
				listErr.add("Username already exist!");
				return listErr;
			}
		}
		
		if (user.getUsername().matches(".*[$#@%^&*]+.*")) {
			listErr.add("Username must not contains $#@%^&*");
		}
		if (user.getUsername().length() <= 6 || user.getUsername().length() > 100) {
			listErr.add("Username must contains at least 7 characters and no longer than 100 characters");
		}
		if (!user.getPassword().matches(".*[A-Z]+.*")) {
			listErr.add("Password must contains at least 1 uppercase character");
		}
		if (!user.getPassword().matches(".*[0-9]+.*")) {
			listErr.add("Password must contains at least 1 numbers");
		}
		if (!user.getPassword().matches(".*[$#@%^&*]+.*")) {
			listErr.add("Password must contains at least 1 of the following characters: $#@%^&*");
		}
		if (user.getPassword().length() <= 8 || user.getPassword().length() > 100) {
			listErr.add("Password must contains at least 9 characters and no longer than 100 characters");
		}
		return listErr;
	}
	
	//get errors if exist when sending mail
	public ArrayList<String> checkMail(Email email) throws Exception {
		listErr = new ArrayList<>();
		if (email.getTo().length() == 0) {
			listErr.add("TO must be filled");
		}
		if (email.getTitle().length() == 0) {
			listErr.add("Title must be filled");
		}
		if (email.getContent().length() == 0) {
			listErr.add("Content must be filled");
		}
		return listErr;
	}
	
	//add error when catch Invalid Addresses
	public ArrayList<String> checkRuntime(){
		listErr = new ArrayList<>();
		listErr.add("Invalid Addresses!");
		return listErr;
	}
	
	//set content except <p> tag
	public void setContent(Email email) {
		String content = email.getContent();
		if (content.length() > 0) email.setContent(content.substring(3, content.length()-6));
	}
	
	public String getMsg() {
		return msg;
	}

	public ArrayList<String> getListErr() {
		return listErr;
	}
	
	
}
