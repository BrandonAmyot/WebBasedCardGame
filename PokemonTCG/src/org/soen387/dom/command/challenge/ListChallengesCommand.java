package org.soen387.dom.command.challenge;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.model.challenge.Challenge;
import org.soen387.dom.model.challenge.Mappers.ChallengeInputMapper;

public class ListChallengesCommand extends ValidatorCommand {
	
	@SetInRequestAttribute
	public List<Challenge> listOfChallenges;
	
	public ListChallengesCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			Long userId = (Long) helper.getSessionAttribute("CurrentUserId");
			listOfChallenges = ChallengeInputMapper.findByUser(userId);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
		
	}
}
