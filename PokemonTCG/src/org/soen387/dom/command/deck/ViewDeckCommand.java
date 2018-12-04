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
	
//	@Source
//	public long deckId;
	
	@SetInRequestAttribute
	public List<Card> deckOfCards;
	
	public ViewDeckCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		long deckId = (Long) helper.getSessionAttribute("deckId");
		
		try {
			long userId = (Long) helper.getSessionAttribute("CurrentUserId");
			Deck requestedDeck = DeckInputMapper.find(deckId);
			
			if(requestedDeck == null) {
				throw new CommandException("Cannot view a deck that doesn't exist.");
			}
			if(requestedDeck.getUserId() != userId) {
				throw new CommandException("Cannot view a deck that is not yours");
			}
			
			deckOfCards = CardInputMapper.viewDeck(deckId);
			for(int i=0; i<deckOfCards.size(); i++) {
				System.out.println(deckOfCards.get(i).getName());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}
	

}
