package io.gotech.missl.elections;

import io.gotech.missl.users.User;

public class MissElection extends GeneralElection {
    public MissElection(ElectionProcess electionProcess) {
	super(electionProcess);
    }

    @Override
    protected void checkCandidateRequirements(User userToBecomeCandidate) {
	if (!userToBecomeCandidate.isFemale()) {
	    throw new BadCandidateSexException(
		    "Candidate is not a Female So can't registred in Miss Election");
	}
    }
}
