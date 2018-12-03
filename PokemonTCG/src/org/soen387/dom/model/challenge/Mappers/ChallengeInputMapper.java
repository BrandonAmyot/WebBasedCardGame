package org.soen387.dom.model.challenge.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.uow.UoW;
import org.soen387.dom.model.challenge.Challenge;
import org.soen387.dom.model.challenge.ts.ChallengeFinder;

public class ChallengeInputMapper {
	public static List<Challenge> findAll() throws SQLException, MapperException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		ResultSet rs = ChallengeFinder.findAll();
		while(rs.next()) {
			Challenge c = null;
			long id = rs.getLong("id");
			
			if(IdentityMap.has(id, Challenge.class)) {
				c = IdentityMap.get(id, Challenge.class);
			}
			else {
				c = new Challenge(rs.getLong("id"),
						rs.getLong("challenger"),
						rs.getLong("challengee"),
						rs.getInt("status"));
				UoW.getCurrent().registerClean(c);
			}
			challenges.add(c);
		}

		return challenges;
	}
	
	public static Challenge find(long id) throws SQLException, MapperException {
		ResultSet rs = ChallengeFinder.find(id);
		Challenge c = null;	
		
		if(IdentityMap.has(id, Challenge.class)) {
			c = IdentityMap.get(id, Challenge.class);
		}
		else {
			c = new Challenge(rs.getLong("id"),
					rs.getLong("challenger"),
					rs.getLong("challengee"),
					rs.getInt("status"));
			UoW.getCurrent().registerClean(c);
		}

		return c;
	}
	public static List<Challenge> findOpenByChallenger(long challenger) throws SQLException, MapperException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		ResultSet rs = ChallengeFinder.findOpenByChallenger(challenger);
		
		while(rs.next()) {
			Challenge c = null;
			long id = rs.getLong("id");
			
			if(IdentityMap.has(id, Challenge.class)) {
				c = IdentityMap.get(id, Challenge.class);
			}
			else {
				c = new Challenge(rs.getLong("id"),
						rs.getLong("challenger"),
						rs.getLong("challengee"),
						rs.getInt("status"));
				UoW.getCurrent().registerClean(c);
			}
			challenges.add(c);
		}

		return challenges;
	}
	public static List<Challenge> findOpenByChallengee(long challengee) throws SQLException, MapperException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		ResultSet rs = ChallengeFinder.findOpenByChallengee(challengee);
		
		while(rs.next()) {
			Challenge c = null;
			long id = rs.getLong("id");
			
			if(IdentityMap.has(id, Challenge.class)) {
				c = IdentityMap.get(id, Challenge.class);
			}
			else {
				c = new Challenge(rs.getLong("id"),
						rs.getLong("challenger"),
						rs.getLong("challengee"),
						rs.getInt("status"));
				UoW.getCurrent().registerClean(c);
			}
			challenges.add(c);
		}

		return challenges;
	}
	public static List<Challenge> findAllOpen() throws SQLException, MapperException {
		List<Challenge> challenges = new ArrayList<Challenge>();
		ResultSet rs = ChallengeFinder.findAllOpen();
		
		while(rs.next()) {
			Challenge c = null;
			long id = rs.getLong("id");
			
			if(IdentityMap.has(id, Challenge.class)) {
				c = IdentityMap.get(id, Challenge.class);
			}
			else {
				c = new Challenge(rs.getLong("id"),
						rs.getLong("challenger"),
						rs.getLong("challengee"),
						rs.getInt("status"));
				UoW.getCurrent().registerClean(c);
			}
			challenges.add(c);
		}

		return challenges;
	}

}
