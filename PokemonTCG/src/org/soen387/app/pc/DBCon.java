package org.soen387.app.pc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.soen387.app.dom.CardRDG;
import org.soen387.app.dom.ChallengeRDG;
import org.soen387.app.dom.UserRDG;

public class DBCon {

	public static ThreadLocal<Connection> myCon;
	public static final String CONN_STRING = "jdbc:mysql://localhost/amyot_brandon?"
			+"user=amyot_brandon&password=mberfrab&characterEncoding=UTF-8&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true";
	
    
    public static synchronized void makeCon() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	if(DBCon.myCon==null) DBCon.myCon = new ThreadLocal<Connection>();
    }
	
	public static void main(String[] args) throws SQLException {
		makeCon();
		DBCon.myCon.set(DriverManager.getConnection(CONN_STRING));
		
		UserRDG.truncateTable();
		CardRDG.truncateTable();
		ChallengeRDG.truncateTable();
		System.out.println("Database reset!");
	}

}
