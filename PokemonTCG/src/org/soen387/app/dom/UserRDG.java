package org.soen387.app.dom;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.soen387.app.pc.DBCon;

public class UserRDG {

	private static long currentID = 0;

	private long id;
	private int version;
	private String username;
	private String password;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public UserRDG(long id, int version, String username, String password) {
		super();
		this.id = id;
		this.version = version;
		this.username = username;
		this.password = password;
	}

	public static List<UserRDG> findAll() throws SQLException {
		List<UserRDG> users = new ArrayList<UserRDG>();
		Connection con = DBCon.myCon.get();
		String query = "SELECT * FROM User;";
		PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			users.add(new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password")));
		}
		rs.close();
		ps.close();

		return users;
	}

	public static UserRDG find(long id) throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "SELECT id, version, username, password FROM User WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		UserRDG user = null;
		if(rs.next()) {
			user = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
			rs.close();
			ps.close();
		} else {
			return null;
		}
		return user;
	}
	public static UserRDG find(String username) throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "SELECT id, version, username, password FROM User WHERE username=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		UserRDG user = null;
		if(rs.next()) {
			user = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
			rs.close();
			ps.close();
		} else {
			return null;
		}
		return user;
	}
	public static UserRDG find(String username, String password) throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "SELECT * FROM User WHERE username=? AND password=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		UserRDG user = null;
		if(rs.next()) {
			user = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("username"), rs.getString("password"));
			rs.close();
			ps.close();
		} else {
			return null;
		}
		return user;
	}
	public int insert() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "INSERT INTO User VALUES (?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setInt(2, version);
		ps.setString(3, username);
		ps.setString(4, password);
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
	public static long getNewUserId() throws SQLException{
		if(currentID == 0) {
			Connection con = DBCon.myCon.get();
			String query = "SELECT MAX(id) AS highestId FROM User";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				currentID = rs.getLong("highestId");
				rs.close();
				ps.close();
			}
			currentID += 1;
		}
		else {
			currentID += 1;
		}

		return currentID;
	}
	public static void truncateTable() throws SQLException {
		Connection con = DBCon.myCon.get();
		Statement update = con.createStatement();
		update.execute("TRUNCATE User;");

	}

}
