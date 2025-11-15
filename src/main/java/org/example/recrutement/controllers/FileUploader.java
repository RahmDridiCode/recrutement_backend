package org.example.recrutement.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@RestController
public class FileUploader {
    String imageFolder = "/mnt/DATA/my_projects/levelone_training/frontend/src/assets/images";

    @PostMapping("/files/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file , @RequestParam("userId") Long userId){
        System.out.println(file.getOriginalFilename());
        try {
            //String fileName = file.getOriginalFilename().replace(" ", "");
            String fileName = "image_"+userId;
            File fileObj = new File(imageFolder+"/"+fileName);
            byte[] fileBytes = file.getBytes();

            OutputStream os = new FileOutputStream(fileObj);

            os.write(fileBytes);
            System.out.println("Successfully"+ " byte inserted");

            os.close();
            return "File Uploaded Successfully";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }
}
