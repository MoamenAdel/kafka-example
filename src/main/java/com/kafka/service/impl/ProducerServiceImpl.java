package com.kafka.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.kafka.model.Employee;
import com.kafka.service.ProducerService;

@Service
public final class ProducerServiceImpl implements ProducerService {
	private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

	private final KafkaTemplate<String, String> kafkaTemplate;

	public ProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(Object message, String topic) {
		if (message instanceof Employee) {

//			logger.info(String.format("$$$$ ========> Producing message: %s", message.toString()));

			ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic, ((Employee)message).toString());

			future.addCallback(new ListenableFutureCallback<>() {
				@Override
				public void onFailure(Throwable ex) {
					logger.info("Unable to send message=[ {} ] due to : {}", message, ex.getMessage());
				}

				@Override
				public void onSuccess(SendResult<String, String> result) {
					logger.info("Sent message=[ {} ] with offset=[ {} ]", message, result.getRecordMetadata().offset());
				}
			});

		}
	}
}
