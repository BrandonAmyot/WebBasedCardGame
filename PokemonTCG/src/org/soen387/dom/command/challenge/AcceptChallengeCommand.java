package org.soen387.dom.command.challenge;

import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.dom.model.challenge.Challenge;
import org.soen387.dom.model.challenge.ChallengeFactory;
import org.soen387.dom.model.challenge.Mappers.ChallengeInputMapper;
import org.soen387.dom.model.challenge.Mappers.ChallengeOutputMapper;

public class AcceptChallengeCommand extends ValidatorCommand {

	@Source
	public long deck;
	
	@Source
	public long version;
	
	public AcceptChallengeCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {
		Long challengee = (Long) helper.getSessionAttribute("CurrentUserId");
		long challengeId = (Long) helper.getSessionAttribute("challengeId");
		try {
			List<Challenge> openChallenges = ChallengeInputMapper.findOpenByChallengee(challengee);
			
			if(openChallenges.isEmpty()) {
				throw new Exception("No challenges made between players.");
			}
			for(int i = 0; i < openChallenges.size(); i++) {
				if(openChallenges.get(i).getId() == challengeId) {
					if(openChallenges.get(i).getChallenger() == challengee) {
						throw new Exception("You cannot accept a challenge you made.");
					}
					else if(openChallenges.get(i).getChallengee() == challengee) {
						Challenge c = new Challenge(challengeId, openChallenges.get(i).getVersion()+1, openChallenges.get(i).getChallenger(), challengee, 3, openChallenges.get(i).getChallengerDeckId());
						ChallengeOutputMapper.updateStatic(c);
//						ChallengeFactory.createNew(challengeId, openChallenges.get(i).getVersion()+1, openChallenges.get(i).getChallenger(), challengee, 3, openChallenges.get(i).getChallengerDeckId());
						
						break;
					}
					else {
						throw new Exception("You cannot accept a challenge made to another player.");
					}
				}
			}			
		}
		catch(Exception e) {
			throw new CommandException(e);
		}
		
	}

}
