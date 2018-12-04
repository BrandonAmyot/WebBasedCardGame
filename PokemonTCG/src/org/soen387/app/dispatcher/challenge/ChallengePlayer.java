package org.soen387.app.dispatcher.challenge;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.command.challenge.AcceptChallengeCommand;
import org.soen387.dom.command.challenge.ChallengePlayerCommand;

public class ChallengePlayer extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		String requestURI = myRequest.getRequestURI();
		String[] urlParams = requestURI.split("/");

		if(urlParams[5].equals("Challenge")) {
			try {
				ChallengePlayerCommand c = new ChallengePlayerCommand(myHelper);
				
				long challengee = Long.parseLong(urlParams[4]);
				myRequest.getSession(true).setAttribute("challengee", challengee);
				
				c.execute();
				
				try {
					UoW.getCurrent().commit();
					myHelper.setRequestAttribute("message", "Successfully challenged player to a match!");
					forward("/WEB-INF/jsp/success.jsp");				
				} catch(InstantiationException | IllegalAccessException | MapperException | SQLException e) {
					myHelper.setRequestAttribute("message", e.getMessage());
					forward("/WEB-INF/jsp/fail.jsp");
				}
			}
			catch(CommandException e) {
				myHelper.setRequestAttribute("message", e.getMessage());
				forward("/WEB-INF/jsp/fail.jsp");
			}
		}
		else if(urlParams[5].equals("Accept")) {
			try {
				AcceptChallengeCommand ac = new AcceptChallengeCommand(myHelper);
				
				long challengeId = Long.parseLong(urlParams[4]);
				myRequest.getSession(true).setAttribute("challengeId", challengeId);
				
				ac.execute();
				
				try {
					UoW.getCurrent().commit();
					myHelper.setRequestAttribute("message", "Successfully accepted challenge!");
					forward("/WEB-INF/jsp/success.jsp");				
				} catch(InstantiationException | IllegalAccessException | MapperException | SQLException e) {
					myHelper.setRequestAttribute("message", e.getMessage());
					forward("/WEB-INF/jsp/fail.jsp");
				}
			}
			catch(CommandException e) {
				myHelper.setRequestAttribute("message", e.getMessage());
				forward("/WEB-INF/jsp/fail.jsp");
			}
		}
		
	}

}
