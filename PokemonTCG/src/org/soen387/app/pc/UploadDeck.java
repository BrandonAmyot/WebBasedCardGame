package org.soen387.app.pc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.soen387.app.dom.CardRDG;

/**
 * Servlet implementation class UploadDeck
 */
@WebServlet("/UploadDeck")
public class UploadDeck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadDeck() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = (Long)request.getSession(true).getAttribute("userid");
		if(id == null) {
			request.setAttribute("message", "You must be logged in to upload a deck.");
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

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String cards = request.getParameter("deck");
		String[] deck = cards.split("\n");
		Long deckId = (Long)request.getSession(true).getAttribute("userid");
		
		if(deck.length != 40) {
			request.setAttribute("message", "You must upload a deck of 40 cards.");
			request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
		}
		else {
			CardRDG card = null;
			for(int i = 0; i < deck.length; i++) {
				String line = deck[i];
				String type = line.substring(0, 1);
				String name = line.substring(2, line.length()-1);
				
				card = new CardRDG(deckId, i+1, type, name);
				card.insert();
			}
			request.setAttribute("message", "Deck successfully uploaded.");
			request.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(request, response);
		}
	}

}
