package br.com.ufape.petshare.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.ufape.petshare.facade.PetShare;

@RestController
@RequestMapping("/s3")
public class S3Controller {

	@Autowired
	private PetShare facade;

	// Upload a file to S3
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		return facade.uploadFile (file, file.getOriginalFilename());
	}
	/*
	@DeleteMapping("/delete/{fileName}")
	public String uploadFile(@PathVariable String fileName) throws IOException {
		return s3Service.deleteFile(fileName);
	}

	// Download a file from S3
	@GetMapping("/download/{fileName}")
	public String downloadFile(@PathVariable String fileName) {
		try {
			return s3Service.downloadFile(fileName).getKey();
		} catch (AmazonS3Exception e) {
			throw new ObjectNotFoundException("Arquivo não encontrado :" + fileName);
		}
	}
	*/
}