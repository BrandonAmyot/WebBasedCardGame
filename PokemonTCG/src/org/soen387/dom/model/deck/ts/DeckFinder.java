package org.soen387.dom.model.deck.TS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class DeckFinder {
	public static ResultSet find(Long deckId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Deck WHERE deckId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
	public static ResultSet findAll(Long userId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Deck WHERE userId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, userId);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
}
