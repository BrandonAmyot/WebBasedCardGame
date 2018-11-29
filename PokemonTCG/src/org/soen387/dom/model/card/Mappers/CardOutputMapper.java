package org.soen387.dom.model.card.Mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.soen387.dom.model.card.Card;
import org.soen387.dom.model.card.TDG.CardTDG;

public class CardOutputMapper extends GenericOutputMapper<Long, Card>{

	@Override
	public void insert(Card c) throws MapperException {
		try {
			CardTDG.insert(c.getDeckId(), c.getCardId(), c.getType(), c.getName());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	@Override
	public void update(Card c) throws MapperException {
		try {
			CardTDG.update(c.getDeckId(), c.getCardId(), c.getType(), c.getName());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	@Override
	public void delete(Card c) throws MapperException {
		try {
			CardTDG.delete(c.getDeckId(), c.getCardId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
}
