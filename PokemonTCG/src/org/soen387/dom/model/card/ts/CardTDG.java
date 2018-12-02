package org.soen387.dom.model.card.ts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class CardTDG {
	public static final String BASE = "Card";
	public static final String TABLE_NAME = DbRegistry.getTablePrefix()+BASE;
	public static final String TRUNCATE_TABLE = "TRUNCATE TABLE " + TABLE_NAME + ";";
	public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME + ";";
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
			+ "deckId long, "
			+ "cardId long, "
			+ "type VARCHAR(5), "
			+ "name VARCHAR(25),"
			+ "basic VARCHAR(25));";
	
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
	
	public static int insert(long deckId, long cardId, String type, String name, String basic) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "INSERT INTO Card VALUES (?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ps.setLong(2, cardId);
		ps.setString(3, type);
		ps.setString(4, name);
		ps.setString(5, basic);
		
		return ps.executeUpdate();
	}
	public static int update(long deckId, long cardId, String type, String name, String basic) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "UPDATE Card SET type=?, name=?, basic =? WHERE deckId=? AND cardId=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, type);
		ps.setString(2, name);
		ps.setString(3, basic);
		ps.setLong(4, deckId);
		ps.setLong(5, cardId);
		
		return ps.executeUpdate();
	}
	public static int delete(long deckId, long cardId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "DELETE FROM Card WHERE deckId=? AND cardId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ps.setLong(2, cardId);
		
		return ps.executeUpdate();
	}
}
