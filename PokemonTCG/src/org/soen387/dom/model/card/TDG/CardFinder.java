package org.soen387.dom.model.card.TDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class CardFinder {
	public static ResultSet viewDeck(Long deckId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Card WHERE deckId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	
	public static ResultSet find(Long deckId, Long cardId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Card WHERE deckId=? AND cardId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ps.setLong(2, cardId);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
}
