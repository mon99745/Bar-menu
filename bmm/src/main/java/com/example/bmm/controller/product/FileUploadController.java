package com.example.bmm.controller.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    // TODO : 파일 업로드 적용 -> 상품 등록
    @Value("file.uploadDir")
    String uploadDir;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }

        try {
            // 파일 저장 경로 지정
            File dest = new File(uploadDir + file.getOriginalFilename());
            // 파일 저장
            file.transferTo(dest);
            return "File uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file.";
        }
    }
}