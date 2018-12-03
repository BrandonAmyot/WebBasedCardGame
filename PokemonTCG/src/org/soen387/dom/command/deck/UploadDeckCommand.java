package org.soen387.dom.command.deck;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.model.card.Card;
import org.soen387.dom.model.card.CardFactory;
import org.soen387.dom.model.card.Mappers.CardOutputMapper;
import org.soen387.dom.model.deck.Deck;
import org.soen387.dom.model.deck.DeckFactory;
import org.soen387.dom.model.deck.Mappers.DeckOutputMapper;
import org.soen387.dom.model.deck.ts.DeckTDG;

public class UploadDeckCommand extends ValidatorCommand {

	@Source
	public String deck;
	
	public UploadDeckCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			long userId = (long) helper.getSessionAttribute("userId");
			long deckId = DeckTDG.getMaxId();
			String[] deckOfCards = deck.split("\n");
			
			if(deckOfCards.length != 40) {
				throw new CommandException("You must upload a deck of 40 cards.");
			}
			else {
				String[] line = new String[3];
				for(int i = 0; i < deckOfCards.length; i++) {
					line = deckOfCards[i].split(" ");
					String type = line[0];
					String name = line[1];
					String basic = "";
					if(line.length == 3) {
						basic = line[2];
					}
					CardFactory.createNew(deckId, i, type, name, basic);
				}
				DeckFactory.createNew(deckId, userId);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

}
