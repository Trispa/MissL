package io.gotech.missl.elections;

public class MissElection implements Election
{
	private ElectionRegistry registry;
	private CandidatesRegistry candidatesRegistry;
	
	public MissElection(ElectionRegistry electionRegistry, CandidatesRegistry candidatesRegistry) {
		this.registry = electionRegistry;
		this.candidatesRegistry = candidatesRegistry;
	}
	
	@Override
	public void vote(Voter voter, Candidate candidate)
	{
		if(candidatesRegistry.contains(candidate)) {
			registry.registerVote(voter, candidate);
		}
		else {
			throw new NotRegisteredException("Candidate is not registered for this election");
		}
		
	}

}
