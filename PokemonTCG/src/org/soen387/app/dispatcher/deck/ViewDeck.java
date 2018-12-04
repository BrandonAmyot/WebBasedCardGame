package org.soen387.app.dispatcher.deck;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.dom.command.deck.ViewDeckCommand;

public class ViewDeck extends Dispatcher {

	
	@Override
	public void execute() throws ServletException, IOException {
		ViewDeckCommand c = new ViewDeckCommand(myHelper);
		try {
			String requestURI = myRequest.getRequestURI();
			long deckId = Long.parseLong(requestURI.substring(requestURI.lastIndexOf('/') + 1));
			myRequest.getSession(true).setAttribute("deckId", deckId);
			
			c.execute();
		
			myRequest.getSession(true).setAttribute("deckOfCards", c.deckOfCards);
			forward("/WEB-INF/jsp/viewDeck.jsp");
		}
		catch(CommandException e) {
			e.printStackTrace();
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");			
		}
	}
}
