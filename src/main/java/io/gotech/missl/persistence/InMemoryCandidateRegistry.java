package io.gotech.missl.persistence;

import io.gotech.missl.elections.Candidate;
import io.gotech.missl.elections.CandidateAlreadyRegistredException;
import io.gotech.missl.elections.CandidatesRegistry;

import java.util.List;

public class InMemoryCandidateRegistry implements CandidatesRegistry {

	private List<Candidate> listOfCandidate;
	
	public InMemoryCandidateRegistry(List<Candidate> listOfCandidate){
		this.listOfCandidate = listOfCandidate;
	}
	
	@Override
	public boolean contains(Candidate candidate) {
		return this.listOfCandidate.contains(candidate);
	}
	
	
	

	@Override
	public void add(Candidate candidate) {

		if(this.contains(candidate)){
			throw  new CandidateAlreadyRegistredException ("Candidate is already in election registry");
		}
		else{
			this.listOfCandidate.add(candidate);
		}
	}

}
