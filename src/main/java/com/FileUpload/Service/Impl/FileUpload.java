package com.FileUpload.Service.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUpload {

		
//	public final String DIR="D:\\4. STS Development\\FileUploadAPI\\src\\main\\resources\\static\\image";
	
	public final String DIR=new ClassPathResource("static/image").getFile().getAbsolutePath();
	
	public FileUpload() throws Exception{
		
	}

	public boolean FileUploads(MultipartFile file) throws IOException{
		boolean stats=false;
			InputStream is=file.getInputStream();
			byte[] data= new byte[is.available()];
			is.read(data);
			
			FileOutputStream fos=new FileOutputStream(DIR + File.separator + file.getOriginalFilename()); 
			fos.write(data);
			fos.flush();
			fos.close();
			stats=true;
//		Can use above code or below code 	
//			Files.copy(file.getInputStream(), Paths.get(DIR + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			
			return stats;
	}

	
	

}
