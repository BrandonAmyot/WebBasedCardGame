package org.soen387.dom.model.challenge.Mappers;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.dom.model.challenge.Challenge;
import org.soen387.dom.model.challenge.ts.ChallengeTDG;


public class ChallengeOutputMapper extends GenericOutputMapper<Long, Challenge>{
	
	@Override
	public void insert(Challenge c) throws MapperException {
		try {
			ChallengeOutputMapper.insertStatic(c);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	@Override
	public void update(Challenge c) throws MapperException {
		try {
			ChallengeOutputMapper.updateStatic(c);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	@Override
	public void delete(Challenge c) throws MapperException {
		try {
			ChallengeOutputMapper.deleteStatic(c);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	public static void insertStatic(Challenge c) throws SQLException {
		ChallengeTDG.insert(c.getId(), c.getVersion(), c.getChallenger(), c.getChallengee(), c.getStatus(), c.getChallengerDeckId());
	}
	public static void updateStatic(Challenge c) throws SQLException, LostUpdateException {
		int update = ChallengeTDG.update(c.getId(), c.getVersion(), c.getChallenger(), c.getChallengee(), c.getStatus(), c.getChallengerDeckId());
		if (update == 0)
			throw new LostUpdateException("Lost update editing challenge with id " + c.getId());
	}
	public static void deleteStatic(Challenge c) throws SQLException, LostUpdateException {
		int update = ChallengeTDG.delete(c.getId());
		if (update == 0)
			throw new LostUpdateException("Lost deleting editing challenge with id " + c.getId());
	}
}
