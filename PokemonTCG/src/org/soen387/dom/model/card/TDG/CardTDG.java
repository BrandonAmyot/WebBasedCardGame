package org.soen387.dom.model.card.TDG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soen387.dom.model.card.Card;

public class CardTDG {
	public static final String BASE = "Card";
	public static final String TABLE_NAME = DbRegistry.getTablePrefix()+BASE;
	public static final String TRUNCATE_TABLE = "TRUNCATE TABLE " + TABLE_NAME + ";";
	public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME + ";";
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
			+ "deckId long, "
			+ "cardId long, "
			+ "type VARCHAR(5), "
			+ "name VARCHAR(25)";
	
	public static void createTable() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		Statement update = con.createStatement();
		update.execute(CREATE_TABLE);
	}
	
	public static void dropTable() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		Statement update = con.createStatement();
		update.execute(TRUNCATE_TABLE);
		update = con.createStatement();
		update.execute(DROP_TABLE);
	}
	
	public static List<Card> viewDeck(Long deckId) throws SQLException {
		List<Card> deck = new ArrayList<Card>();
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Card WHERE deckId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			deck.add(new Card(rs.getLong("deckId"), rs.getLong("cardId"), rs.getString("type"), rs.getString("name")));
		}
		rs.close();
		ps.close();
		return deck;
	}
	
	public static Card find(Long deckId, Long cardId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Card WHERE deckId=? AND cardId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ps.setLong(2, cardId);
		ResultSet rs = ps.executeQuery();
		Card card = null;
		
		if(rs.next()) {
			card = new Card(rs.getLong("deckId"), rs.getLong("cardId"), rs.getString("type"), rs.getString("name"));
			rs.close();
			ps.close();
		} else {
			return null;
		}
		return card;
	}
	
	public static void insert(long deckId, long cardId, String type, String name) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "INSERT INTO Card VALUES (?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ps.setLong(2, cardId);
		ps.setString(3, type);
		ps.setString(4, name);
		ps.executeUpdate();
		return;
	}
	public static void update(long deckId, long cardId, String type, String name) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "UPDATE Card SET type=?, name=?, WHERE deckId=? AND cardId=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, type);
		ps.setString(2, name);
		ps.setLong(3, deckId);
		ps.setLong(4, cardId);
		ps.executeUpdate();
		return;
	}
	public static void delete(long deckId, long cardId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "DELETE FROM Card WHERE deckId=? AND cardId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ps.setLong(2, cardId);
		ps.executeUpdate();
		return;
		
	}
}
