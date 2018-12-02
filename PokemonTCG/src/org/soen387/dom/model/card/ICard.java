package org.soen387.dom.model.card;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface ICard extends IDomainObject<Long>{

	public abstract long getDeckId();
	
	public abstract void setDeckId(long deckId);
	
	public abstract long getCardId();
	
	public abstract void setCardId(long cardId);
	
	public abstract String getType();
	
	public abstract void setType(String type);
	
	public abstract String getName();
	
	public abstract void setName(String name);
	
	public abstract String getBasic();
	
	public abstract void setBasic(String basic);
}
