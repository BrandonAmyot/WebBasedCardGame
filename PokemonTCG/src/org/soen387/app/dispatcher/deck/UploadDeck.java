package org.soen387.app.dispatcher.deck;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.command.deck.UploadDeckCommand;

public class UploadDeck extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		UploadDeckCommand c = new UploadDeckCommand(myHelper);
		try {
			Long userId = (Long) myRequest.getSession(true).getAttribute("CurrentUserId");
			myRequest.getSession(true).setAttribute("userId", userId);
			c.execute();
			try {
				UoW.getCurrent().commit();
				myHelper.setRequestAttribute("message", "Successfully uploaded a deck!");
				forward("/WEB-INF/jsp/success.jsp"); 
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
				myHelper.setRequestAttribute("message", e.getMessage());
				forward("/WEB-INF/jsp/fail.jsp");
			}
		} catch (CommandException e) {
			e.printStackTrace();
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/fail.jsp");
		}
		
	}

}
