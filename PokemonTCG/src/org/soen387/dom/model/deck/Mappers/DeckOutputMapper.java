package org.soen387.dom.model.deck.Mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.soen387.dom.model.deck.Deck;
import org.soen387.dom.model.deck.ts.DeckTDG;

public class DeckOutputMapper extends GenericOutputMapper<Long, Deck> {

	@Override
	public void insert(Deck d) throws MapperException {
		try {
			DeckTDG.insert(d.getDeckId(), d.getUserId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void update(Deck d) throws MapperException {
		try {
			DeckTDG.update(d.getDeckId(), d.getUserId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void delete(Deck d) throws MapperException {
		try {
			DeckTDG.delete(d.getDeckId(), d.getUserId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

}
