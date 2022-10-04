package com.kafka.service.impl;

import java.io.InputStreamReader;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kafka.model.Employee;
import com.kafka.service.FileService;
import com.kafka.service.ProducerService;
import com.opencsv.CSVReader;

@Service
public final class FileServiceImpl implements FileService {
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	private static final String RICH_TOPIC = "RICH_TOPIC";
	private static final String NORMAL_TOPIC = "NORMAL_TOPIC";

	@Autowired
	ProducerService producerService;

	public void handleFile(MultipartFile file) {

		try {
			Reader inputReader = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReader(inputReader);

			String[] nextLine;
			while ((nextLine = csvReader.readNext()) != null) {
				Employee emp = new Employee();
				emp.setNationalId(nextLine[0]);
				emp.setName(nextLine[1]);
				emp.setAmount(nextLine[2]);
				System.out.println(emp.toString());
				if (Double.parseDouble(emp.getAmount()) > 1000) {
					logger.info("Sending record with national Id {} to {}", emp.getNationalId(), RICH_TOPIC);
					emp.setAmount((Double.parseDouble(emp.getAmount()) / 20) + "$");
					producerService.sendMessage(emp, RICH_TOPIC);
				} else {
					logger.info("Sending record with national Id {} to {}", emp.getNationalId(), NORMAL_TOPIC);
					producerService.sendMessage(emp, NORMAL_TOPIC);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
