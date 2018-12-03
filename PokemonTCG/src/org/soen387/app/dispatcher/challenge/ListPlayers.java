package org.soen387.app.dispatcher.challenge;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.dom.command.challenge.ListPlayersCommand;

public class ListPlayers extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ListPlayersCommand c = new ListPlayersCommand(myHelper);
		try {
			c.execute();
			myRequest.getSession(true).setAttribute("list", c.listOfUsers);
			forward("/WEB-INF/jsp/listPlayers.jsp");
		} catch(CommandException e) {
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
