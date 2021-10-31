package com.karenhoffman.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.karenhoffman.api.entity.Image;
import com.karenhoffman.api.repository.ImageRepository;

@Service
public class ImageService {
	
	@Autowired
	ImageRepository imageRepository;
	
	public Image save(MultipartFile file) {
		if (file != null) {
			try {
				Image image = new Image();
				image.setMime(file.getContentType());
				image.setName(file.getName());
				image.setContent(file.getBytes());
				
				return imageRepository.save(image);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
		return null;
	}
}
