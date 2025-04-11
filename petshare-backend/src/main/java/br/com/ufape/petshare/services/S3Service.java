package br.com.ufape.petshare.services;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class S3Service {

    private final AmazonS3 amazonS3;
    
    @Value("${aws.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String formatFileName(String prefix, String originalFilename) {
		String extension = "";
		if (originalFilename != null && originalFilename.contains(".")) {
	        extension = originalFilename.substring(originalFilename.lastIndexOf("."));
	    }
		return prefix + "-" + UUID.randomUUID() + System.currentTimeMillis() + extension.toLowerCase();
	}
    
    public String uploadFile(InputStream inputStream, long size, String fileName, String contentType) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(contentType);
            metadata.setContentLength(size);
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName, inputStream, metadata);
            amazonS3.putObject(request);
            return "File uploaded successfully: " + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error uploading file";
        }
    }
    
    public String deleteFile(String filename) {
        try {
            amazonS3.deleteObject(bucketName, filename);
            return "File uploaded successfully: " + filename;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting file";
        }
    }

    public S3Object downloadFile(String fileName) {
        return amazonS3.getObject(bucketName, fileName);
    }
}