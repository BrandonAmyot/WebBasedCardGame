package org.soen387.app.pc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static Map<String, String>registeredMap = new HashMap<String, String>(); 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			DBCon.myCon.set(DriverManager.getConnection("jdbc:mysql://localhost/amyot_brandon?"
					+"user=amyot_brandon&password=mberfrab&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true"));

			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			if(user==null || user.isEmpty() || pass==null || pass.isEmpty() ) {
				request.setAttribute("message", "Please enter both a username and a password.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
			} else if(registeredMap.containsKey(user)) {
				request.setAttribute("message", "That user has already registered.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
			} else {
				registeredMap.put(user, pass);
				request.setAttribute("message", "That user has been successfully registered.");
				request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);
			}
			
			UserRDG u = new UserRDG(UserRDG.getNewUserId(), 1, user, pass);
			u.insert();
			
//			request.getSession(true).setAttribute("userid", u.getId());
//			request.setAttribute("message", "User '" + user + "' has been successfully registered.");
//			request.getRequestDispatcher("success.jsp").forward(request, response);
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
		doGet(request, response);
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		if(user==null || user.isEmpty() || pass==null || pass.isEmpty()) {
			request.setAttribute("message", "Please enter both a username and a password.");
			request.getRequestDispatcher("failure.jsp").forward(request, response);
		}
		else {
			try {
				UserRDG u = UserRDG.findByUsername(user);
				if(u != null) {
					request.setAttribute("message", "That user has already registered.");
					request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
				}
				else {
					u = new UserRDG(UserRDG.getNewUserId(), 1, user, pass);
					u.insert();	
					long id = u.getId();
					request.getSession(true).setAttribute("userid", id);
					request.setAttribute("message", "User '" + user + "' has been successfully registered.");
					request.getRequestDispatcher("success.jsp").forward(request, response);									
				}					
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
