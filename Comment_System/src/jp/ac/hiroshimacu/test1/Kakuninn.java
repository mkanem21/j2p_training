package jp.ac.hiroshimacu.test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Kakuninn
 */
@WebServlet("/Kakuninn")
public class Kakuninn extends HttpServlet {
	int count;
	private static final long serialVersionUID = 1L;
	  String me;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kakuninn() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() throws ServletException{
    	count=0;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer sb = new StringBuffer();
        count++;
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>確認</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<form action=\"/Comment_System/Kakuninn\" method=\"post\">");
        sb.append("入力内容を確認してください<br>");
        sb.append(request.getParameter("message"));
        me=request.getParameter("message");
        sb.append("<br><input type=\"submit\" value=\"投稿\"><br>");
        sb.append("</form>");
        sb.append("</body>");
        sb.append("</html>");
        out.println(sb);  
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        String url = "jdbc:postgresql://localhost/commentdb";
        String user = "postgres";
        String password = "password";
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, password);
           
            Statement stmt = conn.createStatement();
            String date =getCurrentDateString();
            String sql = "insert into comment (id,commenttext,date) values ("+count+",'"+me+"','"+date+"')";
            int num = stmt.executeUpdate(sql);
            System.out.println(num);
            sql = "select * from comment";
            ResultSet rs = stmt.executeQuery(sql);

            
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
                    response.sendRedirect("http://localhost:8080/Comment_System/Thanks");
                }
            }catch (SQLException e){
                out.println("SQLException:" + e.getMessage());
            }
        }
		
	}
	public String getCurrentDateString()
	
	{
	
	    //==== 現在時刻を取得 ====//
	
	    Date date = new Date();
	
	 
	
	    //==== 表示形式を設定 ====//
	
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	 
	
	    return sdf.format(date);
	
	}


}
