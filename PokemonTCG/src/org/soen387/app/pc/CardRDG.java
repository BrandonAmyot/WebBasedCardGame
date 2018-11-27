package org.soen387.app.pc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRDG {
	
	private static long currentID = 0;
	
	private long deckId;
	private long cardId;
	private String type;
	private String name;
	
	public long getDeckId() {
		return deckId;
	}
	public void setDeckId(long deckId) {
		this.deckId = deckId;
	}
	public long getCardId() {
		return cardId;
	}
	public void setCardId(long cardId) {
		this.cardId = cardId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public CardRDG(long deckId, long cardId, String type, String name) {
		super();
		this.deckId = deckId;
		this.cardId = cardId;
		this.type = type;
		this.name = name;
	}
	
	public static List<CardRDG> viewDeck(long deckId) throws SQLException {
		List<CardRDG> deck = new ArrayList<CardRDG>();
		Connection con = DBCon.myCon.get();
		String query = "SELECT type, name FROM Card WHERE deckId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			deck.add(new CardRDG(rs.getLong("deckId"), rs.getLong("cardId"), rs.getString("type"), rs.getString("name")));
			rs.close();
			ps.close();
		}
		return deck;
	}
	
	public int insert() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "INSERT INTO Card VALUES (?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, deckId);
		ps.setLong(2, cardId);
		ps.setString(3, type);
		ps.setString(4, name);
		return ps.executeUpdate();
	}
/*	public int update() throws SQLException {
		Connection con = DBCon.myCon.get();
	
	
		return ps.executedUpdate();
	}*/
	public int delete() throws SQLException {
		Connection con = DBCon.myCon.get();
		String query = "DELETE FROM Card WHERE deckId=? AND cardId=?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setLong(1, this.getDeckId());
		ps.setLong(2, this.getCardId());
		return ps.executeUpdate();
		
	}
	public static long getNewCardId() throws SQLException{
		if(currentID < 40) {
			currentID += 1;
		}
		else {
			currentID = 1;			
		}
		return currentID;
	}
	
}
