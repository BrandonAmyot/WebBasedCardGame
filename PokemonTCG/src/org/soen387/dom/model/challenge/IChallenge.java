package org.soen387.dom.model.challenge;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IChallenge extends IDomainObject<Long>{
	
	public abstract Long getId();
	
	public abstract void setId(long id);
	
	public abstract long getChallenger();
	
	public abstract void setChallenger(long challenger);
	
	public abstract long getChallengee();
	
	public abstract void setChallengee(long challengee);
	
	public abstract int getStatus();
	
	public abstract void setStatus(int status);
	
	public abstract long getChallengerDeckId();
	
	public abstract void setChallengerDeckId(long challengerDeckId);
	
}
