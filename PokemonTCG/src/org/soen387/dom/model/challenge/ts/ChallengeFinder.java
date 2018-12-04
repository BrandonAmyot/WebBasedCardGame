package org.soen387.dom.model.challenge.ts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeFinder {
	public static ResultSet findAll() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM " + ChallengeTDG.TABLE_NAME + ";";
		PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();

		return rs;		
	}
	
	public static ResultSet find(long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT id, challenger, challengee, status FROM " + ChallengeTDG.TABLE_NAME + " WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	public static ResultSet findOpenByChallenger(long challenger) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM " + ChallengeTDG.TABLE_NAME + " WHERE challenger=? AND status=0;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challenger);
		ResultSet rs = ps.executeQuery();
		
		return rs;
	}
	public static ResultSet findOpenByChallengee(long challengee) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM " + ChallengeTDG.TABLE_NAME + " WHERE challengee=? AND status=0;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challengee);
		ResultSet rs = ps.executeQuery();
			
		return rs;
	}
	public static ResultSet findByUser(long userId) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM " + ChallengeTDG.TABLE_NAME + " WHERE challenger=? OR challengee=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, userId);
		ps.setLong(2, userId);
		ResultSet rs = ps.executeQuery();
			
		return rs;
	}
	public static ResultSet findAllOpen() throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM " + ChallengeTDG.TABLE_NAME + " WHERE status=0;";
		PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
		
		return rs;
	}
}
