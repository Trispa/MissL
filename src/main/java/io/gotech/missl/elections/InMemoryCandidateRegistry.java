package io.gotech.missl.elections;

import java.util.ArrayList;

public class InMemoryCandidateRegistry implements CandidatesRegistry {

	private ArrayList<Candidate> listOfCandidate = new ArrayList<Candidate>();
	@Override
	public boolean isCandidateInRegistry(Candidate candidate) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public InMemoryCandidateRegistry(ArrayList<Candidate> listOfCandidate){
		
		this.listOfCandidate = listOfCandidate;
	}
	

	@Override
	public void addCandidateInRegistry(Candidate candidate) {
		// TODO Auto-generated method stub
		if(this.isCandidateInRegistry(candidate)){
			throw  new CandidateAlreadyInRegistryException ("le candidat est deja present dans le registre de l'éléction");
		}
		else{
		this.listOfCandidate.add(candidate);
		}
	}

}
