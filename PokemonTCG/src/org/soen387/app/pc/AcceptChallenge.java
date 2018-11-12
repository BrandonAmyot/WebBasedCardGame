package org.soen387.app.pc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.dom.ChallengeRDG;

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
			DBCon.myCon.set(DriverManager.getConnection(DBCon.CONN_STRING));

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
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		Long challengeId = Long.parseLong(request.getParameter("challenge"));
		Long challengee = (Long)request.getSession(true).getAttribute("userid");
		
		try {
			List<ChallengeRDG> openChallenges = ChallengeRDG.findOpenByChallengee(challengee);
			
			if(challengee == null) {
				request.setAttribute("message", "You must be logged in to accept a challenge.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			else {
				if(openChallenges.isEmpty()) {
					request.setAttribute("message", "No challenges have been proposed between players.");
					request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
				}
				for(int i = 0; i < openChallenges.size(); i++) {
					if(openChallenges.get(i).getId() == challengeId) {
						if(openChallenges.get(i).getChallenger() == challengee) {
							request.setAttribute("message", "You cannot accept a challenge you made.");
							request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
						}
						else if(openChallenges.get(i).getChallengee() == challengee) {
							openChallenges.get(i).setStatus(3);
							openChallenges.get(i).update();
							request.setAttribute("message", "Challenge accepted between players: " + openChallenges.get(i).getChallenger() + " and " + challengee + ".");
							request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
						}
						else {
							request.setAttribute("message", "You cannot accept a challenge made to another player.");
							request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
						}
					}
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
