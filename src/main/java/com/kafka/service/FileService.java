package com.kafka.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {

	public void handleFile( MultipartFile file);
}
