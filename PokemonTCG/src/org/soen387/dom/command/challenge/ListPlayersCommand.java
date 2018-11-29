package org.soen387.dom.command.challenge;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;

public class ListPlayersCommand extends ValidatorCommand {

	@SetInRequestAttribute
	public List<IUser> listOfUsers;
	
	public ListPlayersCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		try {
			listOfUsers = UserInputMapper.findAll();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new CommandException(e);
		}
	}

}
