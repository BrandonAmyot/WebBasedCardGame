package org.soen387.dom.model.challenge;

import org.dsrg.soenea.domain.DomainObject;

public class Challenge extends DomainObject<Long> implements IChallenge{
	
	private long id, challenger, challengee;
	private int status;
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getChallenger() {
		return challenger;
	}
	public void setChallenger(long challenger) {
		this.challenger = challenger;
	}
	public long getChallengee() {
		return challengee;
	}
	public void setChallengee(long challengee) {
		this.challengee = challengee;
	};
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Challenge(long id, long challenger, long challengee, int status) {
		super(id);
		this.id = id;
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
	}
	
}
