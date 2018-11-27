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
 * Servlet implementation class ListChallenges
 */
@WebServlet("/ListChallenges")
public class ListChallenges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListChallenges() {
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
		doGet(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			Long id = (Long)request.getSession(true).getAttribute("userid");
			if(id == null) {
				request.setAttribute("message", "You must be logged in to view challenges.");
				request.getRequestDispatcher("/WEB-INF/jsp/fail.jsp").forward(request, response);
			}

			List<ChallengeRDG> listOfChallenges = ChallengeRDG.findAll();
			if(listOfChallenges.isEmpty()) {
				request.setAttribute("message", "There are no challenges proposed between players.");
				request.getRequestDispatcher("WEB-INF/jsp/ListChallenges.jsp").forward(request, response);
			}
			else {
				request.setAttribute("list", listOfChallenges);
				request.getRequestDispatcher("WEB-INF/jsp/ListChallenges.jsp").forward(request, response);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
