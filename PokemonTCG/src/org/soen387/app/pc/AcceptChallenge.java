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
 * Servlet implementation class AcceptChallenge
 */
@WebServlet("/AcceptChallenge")
public class AcceptChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptChallenge() {
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
		Long id = (Long)request.getSession(true).getAttribute("userid");
		if(id == null) {
			request.setAttribute("message", "You must be logged in to accept a challenge.");
			request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
		}
		
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
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) {
		Long challengeId = Long.parseLong(request.getParameter("player"));
		
		try {
			ChallengeRDG challenge = ChallengeRDG.find(challengeId);
			
			if(challenge == null) {
				request.setAttribute("message", "There is no open challenge with id: " + challengeId + ".");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			challenge.setStatus(4);
			challenge.update();
			
			request.setAttribute("message", "Accepted challenge between" + challenge.getChallenger() + " and " + challenge.getChallengee() + ".");
			request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
