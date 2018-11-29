package org.soen387.dom.command.deck;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;

public class UploadDeckCommand extends ValidatorCommand {

	@Source
	public String deck;
//	
//	@Source
//	public String[] deck;
	@Source
	public Long id;
	
//	@Source
//	public Long deckId;
	
	@SetInRequestAttribute
	public IUser currentUser;
	
	public UploadDeckCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			try {
				currentUser = UserInputMapper.find(helper.getSessionId());
				if(currentUser == null) {
					String message = "You must be logged in to upload a deck!";
					throw new CommandException(message);					
				}
			} catch(MapperException e) {}
			String[] deckOfCards = deck.split("\n");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

}
