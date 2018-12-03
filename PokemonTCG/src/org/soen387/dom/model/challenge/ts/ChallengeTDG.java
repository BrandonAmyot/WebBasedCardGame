package org.soen387.dom.model.challenge.ts;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeTDG {
	public static final String BASE = "Challenge";
	public static final String TABLE_NAME = DbRegistry.getTablePrefix()+BASE;
	public static final String TRUNCATE_TABLE = "TRUNCATE TABLE " + TABLE_NAME + ";";
	public static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME + ";";
	public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
			+ "id long, "
			+ "challenger long, "
			+ "challengee long, "
			+ "status int);";
	
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

	public static int insert(long id, long challenger, long challengee, int status) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, challenger);
		ps.setLong(3, challengee);
		ps.setInt(4, status);
		return ps.executeUpdate();
	}
	public static int update(long id, long challenger, long challengee, int status) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "UPDATE " + TABLE_NAME + " SET challenger=?, challengee=?, status=? WHERE id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challenger);
		ps.setLong(2, challengee);
		ps.setInt(3, status);
		ps.setLong(4, id);
		return ps.executeUpdate();
	}
	public static int delete(long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "DELETE FROM " + TABLE_NAME + " WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		return ps.executeUpdate();
	}
	public static long getMaxId() throws SQLException {
		return UniqueIdFactory.getMaxId(BASE, "id");
	}
}