package org.soen387.dom.model.deck;

import java.util.List;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.dom.model.card.Card;

public class Deck extends DomainObject<Long> implements IDeck {
	private long deckId;
	private long userId;
	private long version;
	private List<Card> deck;
	
	public long getDeckId() {
		return deckId;
	}
	public void setDeckId(long deckId) {
		this.deckId = deckId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	
	public Deck(long deckId, long userId, long version, List<Card> deck) {
		super(deckId);
		this.deckId = deckId;
		this.userId = userId;
		this.version = version;
		this.deck = deck;
	}
	

}
