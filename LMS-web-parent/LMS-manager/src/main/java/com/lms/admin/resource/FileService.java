package com.lms.admin.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    public String uploadDir;

    public void uploadFile(MultipartFile file) throws Exception {

        try {
            uploadDir = System.getProperty("user.dir") + "\\src\\main\\resources\\upload";
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

    public void copyFile(String url) throws Exception {

        File file = new File("C:\\Users\\Admin\\Desktop\\MockProject\\mock-project\\LMS-web-parent\\LMS-manager\\src\\main\\resources\\upload\\" + url);
        System.out.println(file.toString());
        File copy = new File("C:\\Users\\Admin\\Desktop\\MockProject\\mock-project\\LMS-web-parent\\LMS-user\\src\\main\\resources\\upload\\" + url);

        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(copy);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            System.out.println(inputStream.available());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int i = 0;
        try {
            while ((i = inputStream.read()) != -1) {
                outputStream.write(i);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
