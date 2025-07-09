package com.example.StreamingService.videoStreaming.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

@RestController
@RequestMapping("/video")
public class VideoStreamController {

    private static final String VIDEO_UPLOAD_DIR="E:/Learniverse/StreamingService/src/main/java/com/example/StreamingService/videoStreaming/uploads/";

    @GetMapping("/stream")
    public void streamVideo(HttpServletRequest request, HttpServletResponse response, @RequestParam String filename) throws IOException {
        File videoFile = new File(VIDEO_UPLOAD_DIR+filename);
        long fileLength = videoFile.length();
        String rangeHeader = request.getHeader(HttpHeaders.RANGE);

        long start = 0;
        long end = fileLength - 1;

        if (rangeHeader != null && rangeHeader.startsWith("bytes=")) {
            String[] ranges = rangeHeader.substring(6).split("-");
            start = Long.parseLong(ranges[0]);
            if (ranges.length > 1 && !ranges[1].isEmpty()) {
                end = Long.parseLong(ranges[1]);
            }
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        long contentLength = end - start + 1;

        response.setHeader(HttpHeaders.CONTENT_TYPE, "video/mp4");
        response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
        response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength));
        response.setHeader(HttpHeaders.CONTENT_RANGE, String.format("bytes %d-%d/%d", start, end, fileLength));

        try (RandomAccessFile inputFile = new RandomAccessFile(videoFile, "r");
             OutputStream outputStream = response.getOutputStream()) {

            inputFile.seek(start);
            byte[] buffer = new byte[8192];
            long bytesToRead = contentLength;

            while (bytesToRead > 0) {
                int bytesRead = inputFile.read(buffer, 0, (int) Math.min(buffer.length, bytesToRead));
                if (bytesRead == -1) break;
                outputStream.write(buffer, 0, bytesRead);
                bytesToRead -= bytesRead;
            }
        }
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Video file is empty");
        }

        // Ensure the upload directory exists
        File uploadDir = new File(VIDEO_UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Generate unique filename with timestamp
        String originalName = file.getOriginalFilename();
        String extension = "";
        int dotIndex = originalName.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = originalName.substring(dotIndex);
            originalName = originalName.substring(0, dotIndex);
        }

        String timestamp = String.valueOf(System.currentTimeMillis());
        String newFilename = originalName + "_" + timestamp + extension;
        String filePath = VIDEO_UPLOAD_DIR + newFilename;

        // Save video file
        file.transferTo(new File(filePath));

        return ResponseEntity.ok("Uploaded successfully: " + newFilename);
    }
}

