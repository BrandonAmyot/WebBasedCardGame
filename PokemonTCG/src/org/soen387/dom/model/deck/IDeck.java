package org.soen387.dom.model.deck;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IDeck extends IDomainObject<Long> {
	
	public abstract long getDeckId();
	
	public abstract void setDeckId(long deckId);
	
	public abstract long getUserId();
	
	public abstract void setUserId(long userId);
	
}
