package org.soen387.dom.model.card.Mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.model.card.Card;
import org.soen387.dom.model.card.ts.CardTDG;

public class CardOutputMapper extends GenericOutputMapper<Long, Card>{

	@Override
	public void insert(Card c) throws MapperException {
		try {
			CardOutputMapper.insertStatic(c);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	@Override
	public void update(Card c) throws MapperException {
		try {
			CardOutputMapper.updateStatic(c);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	@Override
	public void delete(Card c) throws MapperException {
		try {
			CardOutputMapper.deleteStatic(c);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	public static void insertStatic(Card c) throws SQLException {
		CardTDG.insert(c.getDeckId(), c.getCardId(), c.getType(), c.getName(), c.getBasic());
	}
	public static void updateStatic(Card c) throws SQLException, LostUpdateException {
		int update = CardTDG.update(c.getDeckId(), c.getCardId(), c.getType(), c.getName(), c.getBasic());
		if(update == 0)
			throw new LostUpdateException("Lost update editing card with id " + c.getCardId());
	}
	public static void deleteStatic(Card c) throws SQLException, LostUpdateException {
		int update = CardTDG.delete(c.getDeckId(), c.getCardId());
		if(update == 0)
			throw new LostUpdateException("Lost update deleting card with id " + c.getCardId());
	}
}
