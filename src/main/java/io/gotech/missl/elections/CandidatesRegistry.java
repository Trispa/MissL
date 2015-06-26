package io.gotech.missl.elections;

public interface CandidatesRegistry
{
	
	public boolean contains(Candidate candidate);
	public void add(Candidate candidate);
	
}
