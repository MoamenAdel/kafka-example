package com.kafka.service;

import org.springframework.stereotype.Service;

@Service
public interface ProducerService {

	public void sendMessage(Object message, String topic);
}
