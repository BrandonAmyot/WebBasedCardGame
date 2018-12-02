package org.soen387.dom.model.deck.ts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class DeckTDG {
	public static final String BASE = "Deck";
	public static final String TABLE_NAME = DbRegistry.getTablePrefix()+BASE;
	public static final String TRUNCATE_TABLE = "TRUNCATE TABLE " + TABLE_NAME + ";";
	public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME + ";";
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
			+ "deckId long, "
			+ "userId long)";
	
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
	
	public static int insert(long deckId, long userId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "INSERT INTO " + TABLE_NAME + " VALUES (?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ps.setLong(2, userId);
		
		return ps.executeUpdate();
	}
	public static int update(long deckId, long userId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "UPDATE " + TABLE_NAME + " SET userId=? WHERE deckId=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, userId);
		ps.setLong(2, deckId);
		
		return ps.executeUpdate();
	}
	public static int delete(long deckId, long userId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "DELETE FROM " + TABLE_NAME + " WHERE deckId=? AND userId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ps.setLong(1, userId);
		
		return ps.executeUpdate();
	}
}
