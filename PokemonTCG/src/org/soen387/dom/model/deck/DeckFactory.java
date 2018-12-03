package org.soen387.dom.model.deck;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;

public class DeckFactory {
	public static Deck createNew(long deckId, long userId, String type, String name, String basic) throws SQLException, MissingMappingException, MapperException {
        Deck result = new Deck(deckId, userId);
        UoW.getCurrent().registerNew(result);
        return result;
    }
    
    public static Deck createClean(long deckId, long userId, String type, String name, String basic) throws SQLException, MissingMappingException, MapperException {
        Deck result = new Deck(deckId, userId);
        UoW.getCurrent().registerClean(result);
        return result;
    }
}
