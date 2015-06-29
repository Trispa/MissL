package io.gotech.missl.elections.candidates;

import io.gotech.missl.users.UserId;


public interface CandidatesRegistry
{
	public boolean containsCandidateWithNumber(CandidateNumber candidateNumber);
	public void add(Candidate candidate);	
}
