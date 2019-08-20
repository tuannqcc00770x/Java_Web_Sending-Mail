package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Email;
import service.MailService;

public class SendingMailController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		String to = req.getParameter("to");
		String cc = req.getParameter("cc");
		String title = req.getParameter("title");
		String content = req.getParameter("editor1");
		Email email = new Email(to, cc, title, content);
		MailService.getInstance().setContent(email);
		try {
			ArrayList<String> listErr = MailService.getInstance().checkMail(email);
			if (!listErr.isEmpty()) {// if there are errors, come back to sendMail.jsp including errors
				req.setAttribute("errors", listErr);
				req.setAttribute("email", email);
				req.getRequestDispatcher("/sendMail.jsp").forward(req, resp);
			} else {//if there is no error, do sending mail
				MailService.getInstance().sendEmail(email);
				email.setTo("");
				email.setCc("");
				email.setTitle("");
				email.setContent("");
				req.setAttribute("success", "true");
				req.setAttribute("email", email);
				req.getRequestDispatcher("/sendMail.jsp").forward(req, resp);
			}
		} catch (java.lang.RuntimeException e) {
			ArrayList<String> listErr = MailService.getInstance().checkRuntime();
			req.setAttribute("errors", listErr);
			req.setAttribute("email", email);
			req.getRequestDispatcher("/sendMail.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
