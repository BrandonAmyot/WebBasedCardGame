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
 * Servlet implementation class RefuseChallenge
 */
@WebServlet("/RefuseChallenge")
public class RefuseChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefuseChallenge() {
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = (Long)request.getSession(true).getAttribute("userid");
		if(id == null) {
			request.setAttribute("message", "You must be logged in to refuse a challenge.");
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

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		Long challengeId = Long.parseLong(request.getParameter("challenge"));
		Long id = (Long)request.getSession(true).getAttribute("userid");
		
		try {
			List<ChallengeRDG> openChallenges = ChallengeRDG.findAllOpen();
			
			if(openChallenges.isEmpty()) {
				request.setAttribute("message", "No challenges have been proposed between players.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			for(int i = 0; i < openChallenges.size(); i++) {
				if(openChallenges.get(i).getId() == challengeId) {
					if(openChallenges.get(i).getChallenger() == id) {
						openChallenges.get(i).setStatus(2);
						openChallenges.get(i).update();
						request.setAttribute("message", "Challenge withdrawn between players: " + id + " and " + openChallenges.get(i).getChallengee() + ".");
						request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
						break;
					}
					else if(openChallenges.get(i).getChallengee() == id) {
						openChallenges.get(i).setStatus(1);
						openChallenges.get(i).update();
						request.setAttribute("message", "Challenge refused between players: " + id + " and " + openChallenges.get(i).getChallengee() + ".");
						request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
					}
					else {
						request.setAttribute("message", "You cannot refuse a challenge made to another player.");
						request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
					}
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
