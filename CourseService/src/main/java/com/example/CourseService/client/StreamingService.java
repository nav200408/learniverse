package com.example.CourseService.client;

import com.example.CourseService.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name ="StreamingService",fallback = StreamingServiceFallBack.class,configuration = FeignConfig.class)
public interface StreamingService {
    @PostMapping(value = "/image/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file) throws IOException;
    @PostMapping(value = "/video/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadVideo(@RequestPart("file") MultipartFile file) throws IOException;
    @PostMapping(value = "/document/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadPDF(@RequestPart("file") MultipartFile file) throws IOException;
}
@Component
class StreamingServiceFallBack implements StreamingService{
    @Override
    public ResponseEntity<String> uploadImage(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public ResponseEntity<String> uploadVideo(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public ResponseEntity<String> uploadPDF(MultipartFile file) throws IOException {
        return null;
    }
}
