package org.soen387.dom.command.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;
import org.soen387.dom.model.challenge.ChallengeFactory;
import org.soen387.dom.model.challenge.ts.ChallengeTDG;
import org.soen387.dom.model.deck.Deck;
import org.soen387.dom.model.deck.Mappers.DeckInputMapper;

public class ChallengePlayerCommand extends ValidatorCommand {

	@Source
	public long deck;
	
	
	public ChallengePlayerCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		Long challenger = (Long) helper.getSessionAttribute("CurrentUserId");
		long challengee = (Long) helper.getSessionAttribute("challengee");

		try {
			User challengeeUser = UserInputMapper.find(challengee);
			Deck challengerDeck = DeckInputMapper.find(deck);

			if(challenger == challengee) {
				throw new CommandException ("You cannot challenge yourself to a match");
			}
			if(challengeeUser == null) {
				throw new CommandException ("The player you are trying to challenge doesn't seem to exist.");
			}
			if(challengerDeck == null) {
				throw new CommandException ("You must both upload a deck before a challenge can be made.");
			}
			
			ChallengeFactory.createNew(ChallengeTDG.getMaxId(), challenger, challengee, 0, challengerDeck.getDeckId());
		}
		catch(SQLException | MapperException e) {
			throw new CommandException(e);
		}
	}
}
