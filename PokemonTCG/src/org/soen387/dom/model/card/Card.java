package org.soen387.dom.model.card;
import org.dsrg.soenea.domain.DomainObject;

public class Card extends DomainObject<Long> implements ICard {
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
	
	public Card(long deckId, long cardId, String type, String name) {
		super(cardId);
		this.deckId = deckId;
		this.cardId = cardId;
		this.type = type;
		this.name = name;
	}
}
