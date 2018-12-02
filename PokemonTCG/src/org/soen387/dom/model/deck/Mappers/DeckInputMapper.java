package org.soen387.dom.model.deck.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.model.card.Card;
import org.soen387.dom.model.card.Mappers.CardInputMapper;
import org.soen387.dom.model.deck.Deck;
import org.soen387.dom.model.deck.ts.DeckFinder;

public class DeckInputMapper {
	public static Deck find(Long deckId) throws SQLException, MapperException {
		try {
			Deck deck = null;
			ResultSet rs = DeckFinder.find(deckId);
			if(IdentityMap.has(deckId, Deck.class)) {
				deck = IdentityMap.get(deckId, Deck.class);
			}
			else {				
				deck = buildSingleDeck(rs);
			}
			return deck;
		} catch(SQLException e) {
			throw new MapperException(e);
		}
	}
	
	public static List<Deck> findAll(Long userId) throws SQLException, MapperException {
		try {
			ResultSet rs = DeckFinder.findAll(userId);
			List<Deck> decks = null;
			while(rs.next()) {
				long deckId = rs.getLong("deckId");
				Deck d = null;
				
				if(IdentityMap.has(deckId, Deck.class)) {
					d = IdentityMap.get(deckId, Deck.class);
				}
				else {
					d = buildSingleDeck(rs);
					UoW.getCurrent().registerClean(d);
				}
				decks.add(d);
			}
			return decks;
		} catch(SQLException e) {
			throw new MapperException(e);
		}
	}

	private static Deck buildSingleDeck(ResultSet rs) 
			throws SQLException, ObjectRemovedException, DomainObjectNotFoundException, MapperException {
		long deckId = rs.getLong("deckId");
		Deck d = null;
		if(IdentityMap.has(deckId, Deck.class)) {
			d = IdentityMap.get(deckId, Deck.class);
		}
		else {
			d = new Deck(rs.getLong("deckId"),
					rs.getLong("userId"));
		}
		return d;
	}
}
