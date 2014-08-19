package jp.ac.hiroshimacu.test1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Nyuuryoku
 */
@WebServlet("/Nyuuryoku")
public class Nyuuryoku extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 String karime="";  
     int f=0;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Nyuuryoku() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
        String message = "";
        out.println(createHTML());    
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
		String message = "";
        message = request.getParameter("message");
        if (message == null || message.length() == 0){
        		f=1;
        }else{
        	 f=0;
        	 request.setAttribute("Message", message);
        	 
        	 response.sendRedirect("http://localhost:8080/Comment_System/Kakuninn");
        }
        out.println(createHTML());    
		out.close();
	}
	protected String createHTML(){
        StringBuffer sb = new StringBuffer();
       
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>入力</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<form action=\"/Comment_System/Kakuninn\" method=\"get\">");
        sb.append("ご意見を入力してください<br>");
        sb.append("<br><textarea name=\"message\" rows=\"10\"cols=\"50\"></textarea><br>");
        sb.append("<input type=\"submit\" value=\"確認\"><br>");
        if(f==1){
        	sb.append("エラー：文章を入力してください<br>");
        }
        sb.append("</form>");
        sb.append(karime);
        sb.append("</body>");
        sb.append("</html>");
        
        return (new String(sb));
    }
}
