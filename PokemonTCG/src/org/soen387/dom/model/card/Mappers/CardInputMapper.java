package org.soen387.dom.model.card.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.model.card.Card;
import org.soen387.dom.model.card.ts.CardFinder;


public class CardInputMapper {
	public static List<Card> viewDeck(Long deckId) throws SQLException, MapperException {
		try {
			ResultSet rs = CardFinder.viewDeck(deckId);
			List<Card> deck = new ArrayList<Card>();			
			deck = buildDeck(rs);
			return deck;
		}
		catch(SQLException e) {
			throw new MapperException(e);
		}
	}
	public static Card find(Long deckId, Long cardId) throws SQLException, MapperException {
		try {
			ResultSet rs = CardFinder.find(deckId, cardId);
			Card card = buildCard(rs);
			return card;
		}
		catch(SQLException e) {
			throw new MapperException(e);
		}
	}
	
	public static List<Card> buildDeck(ResultSet rs) 
			throws SQLException, ObjectRemovedException, DomainObjectNotFoundException {
		List<Card> deck = new ArrayList<Card>();			
		while(rs.next()) {
			long cardId = rs.getLong("cardId");
			Card c = null;
			
			if(IdentityMap.has(cardId, Card.class)) {
				c = IdentityMap.get(cardId, Card.class);
			}
			else {
				c = buildCard(rs);
				UoW.getCurrent().registerClean(c);
			}
			deck.add(c);
		}
		return deck;
	}
	private static Card buildCard(ResultSet rs) throws SQLException {
		Card card = new Card(rs.getLong("deckId"),
				rs.getLong("cardId"),
				rs.getString("type"),
				rs.getString("name"));
		return card;
	}
}
