package com.javamagazine.cloud.votamais.service.local;

import javax.annotation.Resource;
import javax.inject.Singleton;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;

import com.javamagazine.cloud.votamais.service.VotePublisher;

@Singleton
public class VotePublisherLocal implements VotePublisher {

	@Resource(name = "java:comp/DefaultJMSConnectionFactor")
	ConnectionFactory connectionFactory;

	@Resource(name = "java:/jms/queue/ExpiryQueue")
	Destination queue;

	@Override
	public void publishVote(String candidateName) {
		try (JMSContext context = connectionFactory.createContext();) {
			context.createProducer().send(queue, candidateName);
		}
	}
}
