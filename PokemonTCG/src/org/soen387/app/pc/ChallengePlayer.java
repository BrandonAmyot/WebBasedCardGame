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
 * Servlet implementation class ChallengePlayer
 */
@WebServlet("/ChallengePlayer")
public class ChallengePlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengePlayer() {
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
			request.setAttribute("message", "You must be logged in to challenge a player.");
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
		doGet(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		Long challenger = (Long)request.getSession(true).getAttribute("userid");
		Long challengee = Long.parseLong(request.getParameter("player"));
		
		try {
			UserRDG gee = UserRDG.find(challengee);
			
			if(challenger == null) {
				request.setAttribute("message", "You must be logged in to challenge a player.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			if(challenger == challengee) {
				request.setAttribute("message", "You cannot challenge yourself to a match.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			if(gee == null) {
				request.setAttribute("message", "The player you are trying to challenge doesn't seem to exist.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			if(CardRDG.viewDeck(challenger).isEmpty() || CardRDG.viewDeck(challengee).isEmpty()) {
				request.setAttribute("message", "You must both upload a deck before a challenge can be made.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			
			ChallengeRDG challenge = new ChallengeRDG(ChallengeRDG.getNewChallengeId(), challenger, challengee, 0);
			challenge.insert();
			
			request.setAttribute("message", "You have successfully challenged player " + challengee + " to a match!");
			request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
