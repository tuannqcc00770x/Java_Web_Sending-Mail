package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;
import service.MailService;

public class SignupController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("utf-8");
		User user = new User(req.getParameter("username"), req.getParameter("password"));
		try {
			ArrayList<String> listErr = MailService.getInstance().checkSignup(user);
			//if error exists, return signup page include list errors, user registered info
			if (!listErr.isEmpty()) {
				req.setAttribute("errors", listErr);
				req.setAttribute("user", user);
				req.getRequestDispatcher("/signup.jsp").forward(req, resp);
			} else {// if error not exist, forward to login page and save user info
				UserDAO dao = new UserDAO();
				dao.addUser(user);
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
