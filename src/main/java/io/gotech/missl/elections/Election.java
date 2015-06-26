package io.gotech.missl.elections;

public interface Election {
	
	public void vote(Voter voter, Candidate candidate);
	public void registerCandidate (Candidate candidate);
	
}