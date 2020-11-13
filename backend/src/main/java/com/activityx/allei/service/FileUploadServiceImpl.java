package com.activityx.allei.service;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.activityx.allei.dao.ShopDao;
import com.activityx.allei.dto.ShopDto;

@Service
public class FileUploadServiceImpl implements FileUploadService{
//	@Autowired
//	FileUploadDao fileUploadDao;
	
	@Autowired
	ShopDao shopDao;

	// C:\Users\multicampus\Desktop
//	String fileUploadRealPath = "C:" + File.separator + "Users" + File.separator + "multicampus" + File.separator + "Desktop";
	
	// AWS
	// /home/ubuntu
	String fileUploadRealPath = 
			File.separator + "home" + File.separator + "ubuntu" + File.separator + "deploy" + File.separator + "dist" + File.separator + "img";
	
	String uploadFolder = "";
	
	@Override
	public int fileUpload(int id, MultipartFile file, int type) throws Exception{
		int ret = -1;
		
		if (type == 0) {	// img 등록
			uploadFolder = "activityx_shop_img";
		} else if (type == 1) {	// imgDesc 등록
			uploadFolder = "activityx_shop_imgDesc";
		}
		
		File uploadDir = new File(fileUploadRealPath + File.separator + uploadFolder);
		if (!uploadDir.exists()) uploadDir.mkdir();
		
//		System.out.println("file : " + file);
		
		if( file != null ) {

			// Not Random File Name
			String originalFileName = file.getOriginalFilename();
			
			// Random Fild Id & Name
			UUID uuid = UUID.randomUUID();
			
			//file extention
			String extension = FilenameUtils.getExtension(file.getOriginalFilename()); // vs FilenameUtils.getBaseName()
			String savingFileName = uuid + "." + extension;
			
//			System.out.println("uploadDir : " + uploadDir);
//			System.out.println("savingFileName : " + savingFileName);
			
			File saveFile = new File(uploadDir, savingFileName); 
			file.transferTo(saveFile);

//			System.out.println("ID : " + id );
			
			// For DB Table Stuff..
			ShopDto shopDto = new ShopDto();
			shopDto.setId(id);
			switch (type) {
			case 0:	// img
				shopDto.setImg(savingFileName);
				shopDao.updateImg(shopDto);
				break;
			case 1:	// imgDesc
				shopDto.setImgDesc(savingFileName);
				shopDao.updateImgDesc(shopDto);
			}
			
			ret = 1;
		}
		
		return ret;
	}
}
