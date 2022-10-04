package com.kafka.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kafka.service.FileService;

@RestController
@RequestMapping("file")
public final class FileController {
	private final FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping(value = "/upload")
	public ResponseEntity<Void> uploadFile(@RequestBody() MultipartFile file) {
		fileService.handleFile(file);

		return ResponseEntity.accepted().build();
	}
}