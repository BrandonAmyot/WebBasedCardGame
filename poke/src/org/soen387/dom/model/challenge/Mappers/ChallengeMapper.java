package org.soen387.dom.model.challenge.Mappers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.soen387.dom.model.challenge.Challenge;
import org.soen387.dom.model.challenge.TDG.ChallengeTDG;


public class ChallengeMapper {
	public static List<Challenge> findAll() throws SQLException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		ChallengeTDG.findAll();

		return challenges;
	}
	
	public static Challenge find(long id) throws SQLException {
		Challenge challenge;
		challenge = ChallengeTDG.find(id);

		return challenge;

	}
	public static List<Challenge> findOpenByChallenger(long challenger) throws SQLException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		ChallengeTDG.findOpenByChallenger(challenger);
	
		return challenges;
	}
	public static List<Challenge> findOpenByChallengee(long challengee) throws SQLException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		ChallengeTDG.findOpenByChallengee(challengee);
	
		return challenges;
	}
	public static List<Challenge> findAllOpen() throws SQLException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		ChallengeTDG.findAllOpen();

		return challenges;
	}
	public static void insert(long id, long challenger, long challengee, int status) throws SQLException {
		ChallengeTDG.insert(id, challenger, challengee, status);
		return;
	}
	public static void update(long id, long challenger, long challengee, int status) throws SQLException {
		ChallengeTDG.update(id, challenger, challengee, status);
		return;
	}
	public static void delete(long id, long challenger, long challengee, int status) throws SQLException {
		ChallengeTDG.delete(id, challenger, challengee, status);
		return;
	}
}
