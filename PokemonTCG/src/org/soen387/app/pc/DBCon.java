package org.soen387.app.pc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBCon
 */
@WebServlet("/DBCon")
public class DBCon extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
	//We pass around the database connection in a threadlocal so 
	//we have an easier time accessing it elsewhere
	public static ThreadLocal<Connection> myCon;
       
    @Override
    public void init(javax.servlet.ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		makeCon();
    };
	
    
    public static synchronized void makeCon() {
    	if(DBCon.myCon==null) DBCon.myCon = new ThreadLocal<Connection>();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBCon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DBCon.myCon.set(DriverManager.getConnection("jdbc:mysql://localhost/amyot_brandon?"
					+"user=amyot_brandon&password=mberfrab&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true"));

			request.setAttribute("user", UserRDG.findAll());
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			Connection con = DBCon.myCon.get();
			DBCon.myCon.remove();
			try {con.close();} catch(Exception e){}
			
		}
		
		if(request.getParameter("mode") != null && request.getParameter("mode").equalsIgnoreCase("xml")) {
			request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
		}
	}

}
