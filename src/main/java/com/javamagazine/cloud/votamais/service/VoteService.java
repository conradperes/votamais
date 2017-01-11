package com.javamagazine.cloud.votamais.service;

import java.util.List;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.javamagazine.cloud.votamais.model.Candidate;

@Singleton
public class VoteService {

	@PersistenceContext(name = "votePU")
	EntityManager em;
	
	public List<Candidate> partial() {
		return em.createQuery("SELECT c FROM Candidate c", Candidate.class).getResultList();
	}

	@Transactional
	public void saveVote(String candidateName) {
		Candidate candidate = em.find(Candidate.class, candidateName);
		if (candidate == null) {
			candidate = new Candidate();
			candidate.setName(candidateName);
		}
		candidate.setVotes(candidate.getVotes() + 1);
		em.persist(candidate);
	}
}
