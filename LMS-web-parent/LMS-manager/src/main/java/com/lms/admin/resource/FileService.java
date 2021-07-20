package com.lms.admin.resource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Value("D:\\Project Document\\Mock-Project\\mock-project\\LMS-web-parent\\LMS-manager\\src\\main\\resources\\upload")
    public String uploadDir;

    public void uploadFile(MultipartFile file) throws Exception {

        try {
        	System.out.println(uploadDir);
            Path copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Could not store file " + file.getOriginalFilename()
                + ". Please try again!");
        }
    }
}
