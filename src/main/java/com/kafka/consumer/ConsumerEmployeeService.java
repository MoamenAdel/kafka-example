package com.kafka.consumer;

import org.springframework.stereotype.Service;

@Service
public interface ConsumerEmployeeService {

	public void consumeRich(Object employee);
	
	public void consumeNormal(Object employee);
}
