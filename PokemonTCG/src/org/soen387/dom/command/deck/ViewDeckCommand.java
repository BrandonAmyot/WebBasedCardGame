package org.soen387.dom.command.deck;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.model.card.Card;
import org.soen387.dom.model.card.Mappers.CardInputMapper;
import org.soen387.dom.model.deck.Deck;
import org.soen387.dom.model.deck.Mappers.DeckInputMapper;

public class ViewDeckCommand extends ValidatorCommand {
	
	@Source
	public long deck;
	
	@SetInRequestAttribute
	public List<Card> deckOfCards;
	
	@SetInRequestAttribute
	public long id;
	
	public ViewDeckCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			id = deck;
			Deck userDeck = DeckInputMapper.find(id);
			if(userDeck == null) {
				throw new CommandException("Cannot view a deck that doesn't exist.");
			}
			deckOfCards = CardInputMapper.viewDeck(id);
		}
		catch(Exception e) {
			throw new CommandException(e);
		}
	}
	

}
