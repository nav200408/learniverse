package com.example.StreamingService.ImageStreaming.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Value("${UPLOAD_BASE_DIR}")
    private String uploadBaseDir;

    private String getBaseDir() {
        String path = uploadBaseDir;
        if (!path.endsWith("/") && !path.endsWith("\\")) {
            path += File.separator;
        }
        String fullPath = path + "ImageStreaming" + File.separator + "uploads" + File.separator;
        return new File(fullPath).getAbsolutePath() + File.separator;
    }



    @GetMapping("/stream")
    public void getImage(HttpServletResponse response, @RequestParam String filename) throws IOException {
        File imageFile = new File(getBaseDir() + filename);
        System.out.println(getBaseDir());
        // Set content type based on file type
        response.setContentType("image/png"); // or "image/png"

        try (InputStream inputStream = new FileInputStream(imageFile);
                OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        System.out.println(getBaseDir());

        // Ensure the upload directory exists
        File uploadDir = new File(getBaseDir());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Extract original filename and extension
        String originalName = file.getOriginalFilename();
        String extension = "";
        int dotIndex = originalName.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = originalName.substring(dotIndex); // includes the dot
            originalName = originalName.substring(0, dotIndex); // remove extension
        }

        // Add current time millis to avoid collisions
        String timestamp = String.valueOf(System.currentTimeMillis());
        String newFilename = originalName + "_" + timestamp + extension;
        String filePath = getBaseDir() + newFilename;

        // Save the file
        file.transferTo(new File(filePath));

        return ResponseEntity.ok().body(newFilename);
    }
}
