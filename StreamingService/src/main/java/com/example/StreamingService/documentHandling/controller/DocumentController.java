package com.example.StreamingService.documentHandling.controller;


import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
@RestController
@RequestMapping("/document")
public class DocumentController {
    private static final String UPLOAD_DIR = "E:/Learniverse/StreamingService/src/main/java/com/example/StreamingService/documentHandling/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPDF(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || !file.getContentType().equals("application/pdf")) {
            return ResponseEntity.badRequest().body("Only PDF files are allowed.");
        }

        // Generate unique filename with current time
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String timestampedFilename = System.currentTimeMillis() + extension;

        File dest = new File(UPLOAD_DIR + timestampedFilename);
        file.transferTo(dest);

        return ResponseEntity.ok(timestampedFilename);
    }


    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadPDF(@PathVariable String filename) throws IOException {
        File file = new File(UPLOAD_DIR + filename);
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(file.length())
                .body(resource);
    }
}
