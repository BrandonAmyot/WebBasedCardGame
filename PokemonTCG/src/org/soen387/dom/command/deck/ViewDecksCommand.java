package org.soen387.dom.command.deck;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.model.deck.Deck;
import org.soen387.dom.model.deck.Mappers.DeckInputMapper;

public class ViewDecksCommand extends ValidatorCommand {

	@SetInRequestAttribute
	public List<Deck> listOfDecks;
	
	public ViewDecksCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		Long userId = (Long) helper.getSessionAttribute("CurrentUserId");

		try {
			listOfDecks = DeckInputMapper.findAll(userId);
		}
		catch(Exception e) {
			throw new CommandException(e);
		}
	}

}
