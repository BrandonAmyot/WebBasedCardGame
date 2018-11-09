package org.soen387.app.pc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChallengeRDG {
	private static long currentID = 0;
	
	private long id;
	private long challenger;
	private long challengee;
	private int status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getChallenger() {
		return challenger;
	}
	public void setChallenger(long challenger) {
		this.challenger = challenger;
	}
	public long getChallengee() {
		return challengee;
	}
	public void setChallengee(long challengee) {
		this.challengee = challengee;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public ChallengeRDG(long id, long challenger, long challengee, int status) {
		super();
		this.id = id;
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
	}
	
	public static List<ChallengeRDG> findAll() throws SQLException {
		List<ChallengeRDG> challenges = new ArrayList<ChallengeRDG>();
		Connection con = DBCon.myCon.get();
		String query = "SELECT * FROM Challenge;";
		PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			challenges.add(new ChallengeRDG(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status")));
		}
		rs.close();
		ps.close();

		return challenges;		
	}
	
	public static ChallengeRDG find(long id) throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "SELECT id, challenger, challengee, status FROM Challenge WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		ChallengeRDG challenge = null;
		if(rs.next()) {
			challenge = new ChallengeRDG(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status"));
			rs.close();
			ps.close();
		} else {
			return null;
		}
		return challenge;
	}
	public static List<ChallengeRDG> findOpenByChallenger(long challenger) throws SQLException {
		List<ChallengeRDG> challenges = new ArrayList<ChallengeRDG>();
		Connection con = DBCon.myCon.get();
		String query = "SELECT * FROM Challenge WHERE challenger=? AND status=1;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challenger);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			challenges.add(new ChallengeRDG(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status")));
		}
		rs.close();
		ps.close();	
		return challenges;
	}
	public static List<ChallengeRDG> findOpenByChallengee(long challengee) throws SQLException {
		List<ChallengeRDG> challenges = new ArrayList<ChallengeRDG>();
		Connection con = DBCon.myCon.get();
		String query = "SELECT * FROM Challenge WHERE challengee=? AND status=1;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, challengee);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			challenges.add(new ChallengeRDG(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status")));
		}
		rs.close();
		ps.close();	
		return challenges;
	}
	public static List<ChallengeRDG> findAllOpen() throws SQLException {
		List<ChallengeRDG> challenges = new ArrayList<ChallengeRDG>();
		Connection con = DBCon.myCon.get();
		String query = "SELECT * FROM Challenge WHERE status=1;";
		PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			challenges.add(new ChallengeRDG(rs.getLong("id"), rs.getLong("challenger"), rs.getLong("challengee"), rs.getInt("status")));
		}
		rs.close();
		ps.close();	
		return challenges;
	}
	public int insert() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "INSERT INTO User VALUES (?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, challenger);
		ps.setLong(3, challengee);
		ps.setInt(4, status);
		return ps.executeUpdate();
	}
/*	public int update() throws SQLException {
		Connection con = DBCon.myCon.get();
	
	
		return ps.executedUpdate();
	}*/
	public int delete() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "DELETE FROM User WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.getId());
		return ps.executeUpdate();
		
	}
	public static long getNewChallengeId() throws SQLException{
		if(currentID == 0) {
			Connection con = DBCon.myCon.get();
			String query = "SELECT MAX(id) AS highestId FROM Challenge";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				currentID = rs.getLong("highestId");
				rs.close();
				ps.close();
			}
			currentID += 1;
		}
		else
			currentID += 1;			
		return currentID;
	}
	
}
