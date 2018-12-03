package org.soen387.dom.model.card;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;

public class CardFactory {
	
	public static Card createNew(long deckId, long cardId, String type, String name, String basic) throws SQLException, MissingMappingException, MapperException {
        Card result = new Card(deckId, cardId, type, name, basic);
        UoW.getCurrent().registerNew(result);
        return result;
    }
    
    public static Card createClean(long deckId, long cardId, String type, String name, String basic) throws SQLException, MissingMappingException, MapperException {
        Card result = new Card(deckId, cardId, type, name, basic);
        UoW.getCurrent().registerClean(result);
        return result;
    }
}
