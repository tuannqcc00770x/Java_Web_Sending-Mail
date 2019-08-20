package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("LoginFilter destroy()");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException{
		// TODO Auto-generated method stub
		boolean accept = false;
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		if (session != null) {
			if (session.getAttribute("logged") != null) {
				accept = true;
			}
		}
		if (!accept) {
			request.setAttribute("msg", "Please login first!");
			request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		if (chain != null) chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("LoginFilter init()");
	}

}
