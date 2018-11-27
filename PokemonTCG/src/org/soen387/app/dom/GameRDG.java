package org.soen387.app.dom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.soen387.app.pc.DBCon;

public class GameRDG {
	
	private long id;
	private long playerA;
	private long playerB;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPlayerA() {
		return playerA;
	}
	public void setPlayerA(long playerA) {
		this.playerA = playerA;
	}
	public long getPlayerB() {
		return playerB;
	}
	public void setPlayerB(long playerB) {
		this.playerB = playerB;
	}
	
	public GameRDG(long id, long playerA, long playerB) {
		super();
		this.id = id;
		this.playerA = playerA;
		this.playerB = playerB;
	}
	
	public static List<GameRDG> findAll() throws SQLException {
		List<GameRDG> games = new ArrayList<GameRDG>();
		Connection con = DBCon.myCon.get();
		String query = "SELECT * FROM Game;";
		PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			games.add(new GameRDG(rs.getLong("id"), rs.getLong("playerA"), rs.getLong("playerB")));
		}
		rs.close();
		ps.close();

		return games;		
	}
	
	public static GameRDG find(long id) throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "SELECT id, playerA, playerB FROM Game WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		GameRDG game = null;
		if(rs.next()) {
			game = new GameRDG(rs.getLong("id"), rs.getLong("playerA"), rs.getLong("playerB"));
			rs.close();
			ps.close();
		} else {
			return null;
		}
		return game;
	}
	
	public int insert() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "INSERT INTO Game VALUES (?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setLong(2, playerA);
		ps.setLong(3, playerB);
		return ps.executeUpdate();
	}
	public int update() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "UPDATE Game SET playerA=?, playerB=? WHERE id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.getPlayerA());
		ps.setLong(2, this.getPlayerB());
		ps.setLong(3, this.getId());
	
		return ps.executeUpdate();
	}
	public int delete() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "DELETE FROM Game WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.getId());
		return ps.executeUpdate();
		
	}

	public static void truncateTable() throws SQLException {
		Connection con = DBCon.myCon.get();
		Statement update = con.createStatement();
		update.execute("TRUNCATE Game;");
		
	}
}
