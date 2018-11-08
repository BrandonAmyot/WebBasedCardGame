package org.soen387.app.pc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRDG {
	
	private static long currentID = 0;
	
	private long id;
	private int version;
	private String user;
	private String pass;
	
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public UserRDG(long id, int version, String user, String pass) {
		super();
		this.id = id;
		this.version = version;
		this.user = user;
		this.pass = pass;
	}
	
	public static List<UserRDG> findAll() throws SQLException {
		List<UserRDG> p = new ArrayList<UserRDG>();
		Connection con = DBCon.myCon.get();
		String query = "SELECT id, version, user, pass FROM User;";
		PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {	
			p.add(new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("user"), rs.getString("pass")));
		}
		rs.close();
		ps.close();

		return p;		
	}
	
	public static UserRDG find(long id) throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "SELECT id, version, user, pass FROM User WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		UserRDG p = null;
		if(rs.next()) {
			p = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("user"), rs.getString("pass"));
			rs.close();
			ps.close();
		} else {
			return null;
		}
		
		return p;
	}
	public static UserRDG find(String user) throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "SELECT id, version, username, password FROM User WHERE username=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, user);
		ResultSet rs = ps.executeQuery();
		UserRDG p = null;
		if(rs.next()) {
			p = new UserRDG(rs.getLong("id"), rs.getInt("version"), rs.getString("user"), rs.getString("pass"));
			rs.close();
			ps.close();
		} else {
			return null;
		}
		
		return p;
		
	}
	public int insert() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "INSERT INTO User VALUES (?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
		ps.setInt(2, version);
		ps.setString(3, user);
		ps.setString(4, pass);
		return ps.executeUpdate();
	}
	
	public int delete() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "DELETE FROM User WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, id);
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
	
}
