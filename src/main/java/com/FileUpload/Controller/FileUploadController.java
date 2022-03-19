package com.FileUpload.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.FileUpload.Service.Impl.FileUpload;




@RestController
public class FileUploadController {
	
	@Autowired
	private FileUpload fileUploadService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> Upload(@RequestParam("file") MultipartFile file ){
			
		
		try {
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kindly upload the file");
			}
			
//			if(!file.getContentType().equals("image/jpeg")) {
//				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG images are accepted");
//			}
			
			boolean result=fileUploadService.FileUploads(file);
				if(result) {
					return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
				}
		
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
	}
}
