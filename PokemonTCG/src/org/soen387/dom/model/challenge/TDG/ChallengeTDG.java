package org.soen387.dom.model.challenge.TDG;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soen387.dom.model.challenge.Challenge;

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
	
	public static List<Challenge> findAll() throws SQLException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Challenge;";
		PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			challenges.add(new Challenge(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status")));
		}
		rs.close();
		ps.close();

		return challenges;		
	}
	
	public static Challenge find(long id) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT id, challenger, challengee, status FROM Challenge WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		Challenge challenge = null;
		if(rs.next()) {
			challenge = new Challenge(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status"));
			rs.close();
			ps.close();
		} else {
			return null;
		}
		return challenge;
	}
	public static List<Challenge> findOpenByChallenger(long challenger) throws SQLException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Challenge WHERE challenger=? AND status=0;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challenger);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			challenges.add(new Challenge(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status")));
		}
		rs.close();
		ps.close();	
		return challenges;
	}
	public static List<Challenge> findOpenByChallengee(long challengee) throws SQLException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Challenge WHERE challengee=? AND status=0;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challengee);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			challenges.add(new Challenge(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status")));
		}
		rs.close();
		ps.close();	
		return challenges;
	}
	public static List<Challenge> findAllOpen() throws SQLException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		Connection con = DbRegistry.getDbConnection();
		String query = "SELECT * FROM Challenge WHERE status=0;";
		PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			challenges.add(new Challenge(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status")));
		}
		rs.close();
		ps.close();	
		return challenges;
	}
	public static void insert(long id, long challenger, long challengee, int status) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "INSERT INTO Challenge VALUES (?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, challenger);
		ps.setLong(3, challengee);
		ps.setInt(4, status);
		ps.executeUpdate();
		return;
	}
	public static void update(long id, long challenger, long challengee, int status) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "UPDATE Challenge SET challenger=?, challengee=?, status=? WHERE id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challenger);
		ps.setLong(2, challengee);
		ps.setInt(3, status);
		ps.setLong(4, id);
		ps.executeUpdate();
		return;
	}
	public static void delete(long id, long challenger, long challengee, int status) throws SQLException {
		Connection con = DbRegistry.getDbConnection();
		String query = "DELETE FROM Challenge WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.executeUpdate();
		return;
	}
	public static long getNewChallengeId() throws SQLException{
		if(Challenge.getCurrentId() == 0) {
			Connection con = DbRegistry.getDbConnection();
			String query = "SELECT MAX(id) AS highestId FROM Challenge";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Challenge.setCurrentId((int)rs.getLong("highestId"));
				rs.close();
				ps.close();
			}
			Challenge.incrementCurrentId();
		}
		else
			Challenge.incrementCurrentId();			
		return Challenge.getCurrentId();
	}
}
