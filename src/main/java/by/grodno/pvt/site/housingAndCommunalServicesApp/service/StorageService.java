package by.grodno.pvt.site.housingAndCommunalServicesApp.service;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import by.grodno.pvt.site.housingAndCommunalServicesApp.dto.Avatar;

public interface StorageService {

	void store(Integer id, MultipartFile file) throws IOException;

	Avatar getFile(Integer id);
}
