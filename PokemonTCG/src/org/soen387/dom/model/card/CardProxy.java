package org.soen387.dom.model.card;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.model.card.Mappers.CardInputMapper;

public class CardProxy extends DomainObjectProxy<Long, Card> implements ICard{

	protected CardProxy(Long id) {
		super(id);
	}
	
	@Override
	protected Card getFromMapper(Long cardId) throws MapperException, DomainObjectCreationException {
		try {
			Long deckId = this.getDeckId();
			return CardInputMapper.find(deckId, cardId);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getDeckId() {
		return getInnerObject().getDeckId();
	}

	@Override
	public void setDeckId(long deckId) {
		getInnerObject().setDeckId(deckId);
	}

	@Override
	public long getCardId() {
		return getInnerObject().getCardId();
	}

	@Override
	public void setCardId(long cardId) {
		getInnerObject().setCardId(cardId);		
	}

	@Override
	public String getType() {
		return getInnerObject().getName();
	}

	@Override
	public void setType(String type) {
		getInnerObject().setType(type);
	}

	@Override
	public String getName() {
		return getInnerObject().getName();
	}

	@Override
	public void setName(String name) {
		getInnerObject().setName(name);
	}

}
