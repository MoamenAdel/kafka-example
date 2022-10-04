package com.kafka.consumer.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.consumer.ConsumerEmployeeService;

@Service
public final class ConsumerEmployeeServiceImpl implements ConsumerEmployeeService {
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd HH_mm_ss");
	private static final Logger logger = LoggerFactory.getLogger(ConsumerEmployeeServiceImpl.class);
	private static final String RICH_FILE_PATH = "D:/rich_employees_" + dtf.format(LocalDateTime.now()) + ".csv";
	private static final String NORMAL_FILE_PATH = "D:/normal_employees_" + dtf.format(LocalDateTime.now()) + ".csv";

	@KafkaListener(topics = "RICH_TOPIC", groupId = "group_id")
	public void consumeRich(Object object) {
		ConsumerRecord<String, String> rec = (ConsumerRecord<String, String>) object;
		try {
			FileWriter fw = new FileWriter(new File(RICH_FILE_PATH), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(rec.value() + "\n");
			logger.info("$$$$ => Rich Employee: {}", rec.value());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@KafkaListener(topics = "NORMAL_TOPIC", groupId = "group_id")
	public void consumeNormal(Object object) {

		ConsumerRecord<String, String> rec = (ConsumerRecord<String, String>) object;
		try {
			FileWriter fw = new FileWriter(new File(NORMAL_FILE_PATH), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.append(rec.value() + "\n");
			logger.info("$$$$ => Normal Employee: {}", rec.value());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
