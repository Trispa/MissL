package io.gotech.missl.elections.candidates;




public interface CandidatesRegistry
{
	public boolean containsCandidateWithNumber(CandidateNumber candidateNumber);
	public void add(Candidate candidate);	
}
