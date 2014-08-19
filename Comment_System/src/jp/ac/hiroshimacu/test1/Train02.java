package jp.ac.hiroshimacu.test1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Train02
 */
@WebServlet("/Train02")
public class Train02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JSP_BASE = null;
	private Object document;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Train02() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		ServletRequest req = null;
		String action = request.getParameter("password");
		
		String forward = null;
		if(action.equals("password")) {
			String url = "/tomcat_test/Train01";
			response.sendRedirect(url);
			//forward = JSP_BASE + "login.jsp";
			
		}else{
			//throw new ServletException("不正なリクエストです");
			String url = "/tomcat_test/login2.jsp";
			//String url =　でそのプログラムに飛ぶ
			response.sendRedirect(url);
			
			
		}
	}
	}
	

