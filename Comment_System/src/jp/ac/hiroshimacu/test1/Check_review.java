package jp.ac.hiroshimacu.test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Check_review
 */
@WebServlet("/Check_review")
public class Check_review extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_review() {
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
        out.println("<html>");
        out.println("<head>");
        out.println("<title>お客様の声</title>");
        out.println("</head>");
        out.println("<body>");
        
        out.println("お客様のコメント一覧です。<br>");
        
        Connection conn =null;
        String url = "jdbc:postgresql://localhost/commentdb";
        String user = "postgres";
        String password = "kiokutuioku";
        try{
        	//繝��繧ｿ繝吶�繧ｹ縺ｫ蟇ｾ縺吶ｋ蜃ｦ逅�
        
        	Class.forName("org.postgresql.Driver").newInstance();
        	conn = DriverManager.getConnection(url,user,password);
        	Statement stmt = conn.createStatement();
        	String sql = "select * from comment";
        	 ResultSet rs = stmt.executeQuery(sql);
        	 
        	 String tableHTML = "<table border=1>";
        	 tableHTML += "<tr bgcolor=\"000080\"><td><font color=\"white\">ID</font></td>" + "<td nowrap><font color=\"white\">コメント</font></td>" + "<td colspan=2><font color=\"white\">日時</font></td>";

        	
        	 while(rs.next()){
	                int code = rs.getInt("id");
	                String comment = rs.getString("commenttext");
	                String date = rs.getString("date");
	            /*    out.println("<p>");
	                out.println("No:" + code);
	                out.println("日時" + date);
	                out.println(comment);
	                out.println("</p>");
	            */
	                tableHTML += "<tr><td align=\"right\" bgcolor=\"A0A0A0\"><b>" + code + "</b></td>" + "<td nowrap>" + comment + "</td><td>" + date + "</td></tr>";
        	 }
        			
	                tableHTML += "</table>";
	                out.println(tableHTML);
	                
	                
        	 
        	 
        	 
	            rs.close();
	            stmt.close();
        
        }catch (ClassNotFoundException e){
            out.println("ClassNotFoundException:" + e.getMessage());
        }catch (SQLException e){
            out.println("SQLException:" + e.getMessage());
        }catch (Exception e){
            out.println("Exception:" + e.getMessage());
        }finally{
            try{
                if (conn != null){
                    conn.close();
                }
            }catch (SQLException e){
                out.println("SQLException:" + e.getMessage());
            }
        }

        out.println("</body>");
        out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}
}
