package io.gotech.missl.elections.candidates;

public class CandidateNumber
{
	private int number;
	
	public CandidateNumber(int number) {
		this.number = number;
	}
	
	public boolean equals(CandidateNumber anotherCandidateNumber) {
		return this.number == anotherCandidateNumber.number;
	}
}
