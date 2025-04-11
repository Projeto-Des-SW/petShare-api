package br.com.ufape.petshare.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		return facade.uploadFile (file, file.getOriginalFilename());
	}
	
	@DeleteMapping("/delete/{fileName}")
	public String uploadFile(@PathVariable String fileName) throws IOException {
		return facade.deleteFile(fileName);
	}
}