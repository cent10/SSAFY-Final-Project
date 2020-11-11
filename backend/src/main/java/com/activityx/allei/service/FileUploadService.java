package com.activityx.allei.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	public int fileUpload(int id, MultipartFile file, int type) throws Exception;
}
