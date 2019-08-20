package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.MailService;

public class LoginController extends HttpServlet {

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
		MailService service = MailService.getInstance();
		try {
			boolean check = service.checkLogin(user);
			if (check) {
				req.getSession().setAttribute("logged", user);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			} else {
				req.setAttribute("user", user);
				req.setAttribute("error", service.getMsg());
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		MailService.getInstance();
	}

}
