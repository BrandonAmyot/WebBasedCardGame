package org.soen387.dom.model.deck.Mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.model.card.Card;
import org.soen387.dom.model.card.ts.CardTDG;
import org.soen387.dom.model.deck.Deck;
import org.soen387.dom.model.deck.ts.DeckTDG;

public class DeckOutputMapper extends GenericOutputMapper<Long, Deck> {

	@Override
	public void insert(Deck d) throws MapperException {
		try {
			DeckOutputMapper.insertStatic(d);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void update(Deck d) throws MapperException {
		try {
			DeckOutputMapper.updateStatic(d);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void delete(Deck d) throws MapperException {
		try {
			DeckOutputMapper.deleteStatic(d);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	public static void insertStatic(Deck d) throws SQLException {
		DeckTDG.insert(d.getDeckId(), d.getUserId());
	}
	public static void updateStatic(Deck d) throws SQLException, LostUpdateException  {
		int update = DeckTDG.update(d.getDeckId(), d.getUserId());
		if(update == 0)
			throw new LostUpdateException("Lost update editing card with id " + d.getDeckId());
	}
	public static void deleteStatic(Deck d) throws SQLException, LostUpdateException {
		int update = DeckTDG.delete(d.getDeckId(), d.getUserId());
		if(update == 0)
			throw new LostUpdateException("Lost update editing card with id " + d.getDeckId());
	}

}
