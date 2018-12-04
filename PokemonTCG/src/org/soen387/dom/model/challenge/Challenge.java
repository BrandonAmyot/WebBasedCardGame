package org.soen387.dom.model.challenge;

import org.dsrg.soenea.domain.DomainObject;

public class Challenge extends DomainObject<Long> implements IChallenge{
	
	private long id, version, challenger, challengee, challengerDeckId;
	private int status;
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
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
	public long getChallengerDeckId() {
		return challengerDeckId;
	}
	public void setChallengerDeckId(long challengerDeckId) {
		this.challengerDeckId = challengerDeckId;
	}
	public Challenge(long id, long version, long challenger, long challengee, int status, long challengerDeckId) {
		super(id);
		this.id = id;
		this.version = version;
		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
		this.challengerDeckId = challengerDeckId;
	}
	
}
