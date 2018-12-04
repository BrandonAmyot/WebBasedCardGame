package org.soen387.dom.model.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;

public class ChallengeFactory {
	public static Challenge createNew(long id, long challenger, long challengee, int status, long challengerDeckId) throws SQLException, MissingMappingException, MapperException {
        Challenge result = new Challenge(id, challenger, challengee, status, challengerDeckId);
        UoW.getCurrent().registerNew(result);
        return result;
    }
    
    public static Challenge createClean(long id, long challenger, long challengee, int status, long challengerDeckId) throws SQLException, MissingMappingException, MapperException {
        Challenge result = new Challenge(id, challenger, challengee, status, challengerDeckId);
        UoW.getCurrent().registerClean(result);
        return result;
    }
}
