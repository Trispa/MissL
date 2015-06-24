package io.gotech.missl.elections;

public interface CandidatesRegistry
{
	
	public boolean isCandidateInRegistry(Candidate candidate);
	public void addCandidateInRegistry(Candidate candidate);
	
}
