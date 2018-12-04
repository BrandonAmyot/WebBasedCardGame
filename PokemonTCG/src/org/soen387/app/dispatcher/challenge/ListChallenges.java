package org.soen387.app.dispatcher.challenge;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.dom.command.challenge.ListChallengesCommand;

public class ListChallenges extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ListChallengesCommand c = new ListChallengesCommand(myHelper);
		
		try {
			c.execute();
			myRequest.getSession(true).setAttribute("list", c.listOfChallenges);
			forward("/WEB-INF/jsp/listChallenges.jsp");
		} catch(CommandException e) {
			forward("/WEB-INF/jsp/fail.jsp");
		}
		
	}

}
