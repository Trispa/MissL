package io.gotech.missl.domain.elections;

import io.gotech.missl.domain.users.User;

import org.joda.time.DateTime;

public class MissElection extends GeneralElection {

    public MissElection(User creator, ElectionProcess electionProcess,
	    DateTime startDate, DateTime endDate) {
	super(creator, electionProcess, startDate, endDate);
    }

    @Override
    protected void checkCandidateRequirements(User userToBecomeCandidate) {
	if (!userToBecomeCandidate.isFemale()) {
	    throw new BadCandidateSexException(
		    "Candidate is not a Female So can't registred in Miss Election");
	}
    }

}
