package com.javamagazine.cloud.votamais.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.javamagazine.cloud.votamais.model.Candidate;
import com.javamagazine.cloud.votamais.service.VotePublisher;
import com.javamagazine.cloud.votamais.service.VoteService;

@Path("/vote")
public class VoteController {

	@Inject
	VoteService voteService;

	@Inject
	VotePublisher votePublisher;

	@POST
	public void vote(@QueryParam("candidate") String candidate) {
		votePublisher.publishVote(candidate);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Candidate> partial() {
		return voteService.partial();
	}
}
