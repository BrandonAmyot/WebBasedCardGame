package org.soen387.dom.model.deck;

import org.dsrg.soenea.domain.DomainObject;

public class Deck extends DomainObject<Long> implements IDeck {
	private long deckId;
	private long userId;
	
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
	
	
	public Deck(long deckId, long userId) {
		super(deckId);
		this.deckId = deckId;
		this.userId = userId;
	}
	

}
