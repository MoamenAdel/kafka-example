package com.kafka.kafkaexample;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class KafkaExampleApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	String documentRoot = "";

	@Test
	public void test_handleFileUpload() throws Exception {
		String fileName = "test.csv";
		MockMultipartFile sampleFile = new MockMultipartFile("file", fileName, "text/plain",
				"This is the file content".getBytes());

		MockMultipartHttpServletRequestBuilder multipartRequest = MockMvcRequestBuilders.multipart("/files/upload");

		mockMvc.perform(multipartRequest.file(sampleFile)).andExpect(status().isAccepted());

	}

}
