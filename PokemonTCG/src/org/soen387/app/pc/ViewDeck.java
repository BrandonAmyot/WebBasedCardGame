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

import org.soen387.app.dom.CardRDG;

/**
 * Servlet implementation class ViewDeck
 */
@WebServlet("/ViewDeck")
public class ViewDeck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDeck() {
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
			Long id = (Long)request.getSession(true).getAttribute("userid");
			if(id == null) {
				request.setAttribute("message", "You must be logged in to view a deck.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}

			List<CardRDG> deck = CardRDG.viewDeck(id);
			if(deck.isEmpty()) {
				request.setAttribute("message", "There is no deck uploaded.");
				request.getRequestDispatcher("WEB-INF/jsp/fail.jsp").forward(request, response);
			}
			else {
				request.setAttribute("deck", deck);
				request.setAttribute("id", id);
				request.getRequestDispatcher("WEB-INF/jsp/viewDeck.jsp").forward(request, response);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
