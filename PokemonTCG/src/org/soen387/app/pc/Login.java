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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(javax.servlet.ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBCon.makeCon();
    };

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			DBCon.myCon.set(DriverManager.getConnection("jdbc:mysql://localhost/amyot_brandon?"
					+"user=amyot_brandon&password=mberfrab&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true"));
			
			processRequest(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			Connection con = DBCon.myCon.get();
			DBCon.myCon.remove();
			try {con.close();} catch(Exception e) {}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		
		if(username==null || username.isEmpty() || password==null || password.isEmpty()) {
			request.setAttribute("message", "Please enter both a username and a password.");
			request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
		}
		else {
			try {
				UserRDG u = UserRDG.find(username, password);
				if(u != null) {
					long id = u.getId();
					request.getSession(true).setAttribute("userid", id);
					request.setAttribute("message", "User '" + username + "' has been successfully logged in.");
					request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);								
				}
				else {	
					request.setAttribute("message", "I do not recognize that username and password combination.");
					request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
				}					
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
