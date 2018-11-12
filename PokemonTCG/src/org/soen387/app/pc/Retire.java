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

import org.soen387.app.dom.GameRDG;

/**
 * Servlet implementation class Retire
 */
@WebServlet("/Retire")
public class Retire extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Retire() {
        super();
        // TODO Auto-generated constructor stub
    }

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
			request.setAttribute("message", "You must be logged in to retire from a game.");
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
		Long gameId = Long.parseLong(request.getParameter("game"));
		Long id = (Long)request.getSession(true).getAttribute("userid");
		
		try {
			List<GameRDG> games = GameRDG.findAll();
			
			if(games.isEmpty()) {
				request.setAttribute("message", "No games to retire from.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			for(int i = 0; i < games.size(); i++) {
				if(games.get(i).getId() == gameId) {
					if(games.get(i).getPlayerA() == id) {
						games.get(i).delete();
						request.setAttribute("message", "Player " + id + " has successfully withdrawn from game " + gameId + ".");
						request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
						break;
					}
					else if(games.get(i).getPlayerB() == id) {
						games.get(i).delete();
						request.setAttribute("message", "Player " + id + " has successfully withdrawn from game " + gameId + ".");
						request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
					}
					else {
						request.setAttribute("message", "You cannot retire from a game you are not participating in.");
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
