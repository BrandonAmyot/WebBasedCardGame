package org.soen387.app.dispatcher.deck;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.dom.command.deck.ViewDecksCommand;

public class ViewDecks extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		ViewDecksCommand c = new ViewDecksCommand(myHelper);
		try {
			c.execute();
			
			myRequest.getSession(true).setAttribute("listOfDecks", c.listOfDecks);
			forward("/WEB-INF/jsp/viewDecks.jsp");
		}
		catch(CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");
		}
	}

}
