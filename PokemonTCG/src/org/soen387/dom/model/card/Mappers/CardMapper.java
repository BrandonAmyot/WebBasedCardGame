package org.soen387.dom.model.card.Mappers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.soen387.dom.model.card.Card;
import org.soen387.dom.model.card.TDG.CardTDG;


public class CardMapper {
	public static List<Card> viewDeck(Long deckId) throws SQLException {
		List<Card> deck = new ArrayList<Card>();
		deck = CardTDG.viewDeck(deckId);
		
		return deck;
	}
	public static Card find(Long deckId, Long cardId) throws SQLException {
		return CardTDG.find(deckId, cardId);
	}
	
	public static void insert(long deckId, long cardId, String type, String name) throws SQLException {
		CardTDG.insert(deckId, cardId, type, name);
		
		return;
	}
	public static void update(long deckId, long cardId, String type, String name) throws SQLException {
		CardTDG.update(deckId, cardId, type, name);
		return;
	}
	public static void delete(long deckId, long cardId) throws SQLException {
		CardTDG.delete(deckId, cardId);
		return;
	}
}
