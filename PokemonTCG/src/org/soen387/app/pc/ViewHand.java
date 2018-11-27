package org.soen387.app.pc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.dom.GameRDG;

/**
 * Servlet implementation class ViewHand
 */
@WebServlet("/ViewHand")
public class ViewHand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewHand() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			Long gameId = Long.parseLong(request.getParameter("game"));
			Long id = (Long)request.getSession(true).getAttribute("userid");
			
			if(id == null) {
				request.setAttribute("message", "You must be logged in to view a hand.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
				return;
			}

			GameRDG game = GameRDG.find(gameId);
			if(game == null) {
				request.setAttribute("message", "That game does not seem to exist.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			else if(game.getPlayerA() != id || game.getPlayerB() != id) {
				request.setAttribute("message", "You cannot view the hand of a player in different game.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			else {
				request.setAttribute("game", game);
				request.getRequestDispatcher("WEB-INF/jsp/viewHand.jsp").forward(request, response);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
