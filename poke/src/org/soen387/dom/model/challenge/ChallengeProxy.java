package org.soen387.dom.model.challenge;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.dom.model.challenge.Mappers.ChallengeMapper;

public class ChallengeProxy extends DomainObjectProxy<Long, Challenge> implements IChallenge{

	protected ChallengeProxy(Long id) {
		super(id);
	}
	
	@Override
	protected Challenge getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return ChallengeMapper.find(id);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getVersion() {
		return getInnerObject().getVersion();
	}

	@Override
	public void setVersion(long new_version) {
		getInnerObject().setVersion(new_version);
		
	}

	@Override
	public Long getId() {
		return getInnerObject().getId();
	}

	@Override
	public void setId(long id) {
		getInnerObject().setId(id);
		
	}

	@Override
	public long getChallenger() {
		return getInnerObject().getChallenger();
	}

	@Override
	public void setChallenger(long challenger) {
		getInnerObject().setChallenger(challenger);
		
	}

	@Override
	public long getChallengee() {
		return getInnerObject().getChallengee();
	}

	@Override
	public void setChallengee(long challengee) {
		getInnerObject().setChallengee(challengee);
	}

	@Override
	public int getStatus() {
		return getInnerObject().getStatus();
	}

	@Override
	public void setStatus(int status) {
		getInnerObject().setStatus(status);
	}

}
