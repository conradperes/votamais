package com.javamagazine.cloud.votamais.service.local;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.javamagazine.cloud.votamais.service.VoteService;

@MessageDriven(mappedName = "ExpiryQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExpiryQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class VotePublisherLocalMDB implements MessageListener {

	@Inject
	VoteService voteService;

	@Override
	public void onMessage(Message message) {
		try {
			voteService.saveVote(message.getBody(String.class));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
