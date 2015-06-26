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
		if(this.candidatesRegistry.contains(candidate)) {
			this.registry.registerVote(voter, candidate);
		}
		else {
			throw new NotRegisteredException("Candidate is not registered for this election");
		}
		
	}

	@Override
	public void registerCandidate(Candidate candidate) {
		if(candidate.isFemale()){
			this.candidatesRegistry.add(candidate);
		}
		else
			throw new BadCandidateSexException("Candidate is not a Female So can't registred in Miss Election");
	}

}
