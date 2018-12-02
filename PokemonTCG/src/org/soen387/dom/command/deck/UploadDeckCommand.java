package org.soen387.dom.command.deck;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.soen387.dom.model.card.Card;
import org.soen387.dom.model.card.Mappers.CardOutputMapper;

public class UploadDeckCommand extends ValidatorCommand {

	@Source
	public String deck;

	@SetInRequestAttribute
	public IUser currentUser;
	
	public UploadDeckCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
//			try {
//				currentUser = UserInputMapper.find(helper.getSessionId());
//				if(currentUser == null) {
//					String message = "You must be logged in to upload a deck!";
//					throw new CommandException(message);					
//				}
//			} catch(MapperException e) {}
			long userId = currentUser.getId();
			String[] deckOfCards = deck.split("\n");
			if(deckOfCards.length != 40) {
				throw new InstantiationException("You must upload a deck of 40 cards.");
			}
			else {
				String[] line = new String[3];
				for(int i = 0; i < deckOfCards.length; i++) {
					line = deckOfCards[i].split(" ");
					String type = line[0];
					String name = line[1];
					String basic = null;
					if(!line[2].isEmpty()) {
						basic = line[2];
					}
					Card card = new Card(userId, i, type, name, basic);
					CardOutputMapper.insertStatic(card);
//					UoW.getCurrent().registerClean(card);
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

}
