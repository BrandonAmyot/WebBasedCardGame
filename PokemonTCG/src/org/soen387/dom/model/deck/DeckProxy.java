package org.soen387.dom.model.deck;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.model.deck.Mappers.DeckInputMapper;

public class DeckProxy extends DomainObjectProxy<Long, Deck> implements IDeck {

	protected DeckProxy(Long id) {
		super(id);
	}

	@Override
	protected Deck getFromMapper(Long deckId) throws MapperException, DomainObjectCreationException {
		try {
			deckId = this.getDeckId();
			return DeckInputMapper.find(deckId);
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
	public long getUserId() {
		return getInnerObject().getUserId();
	}

	@Override
	public void setUserId(long userId) {
		getInnerObject().setUserId(userId);		
	}

}
