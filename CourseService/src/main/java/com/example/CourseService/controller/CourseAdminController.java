package com.example.CourseService.controller;

import com.example.CourseService.client.StreamingService;
import com.example.CourseService.dto.*;
import com.example.CourseService.model.CourseEntity;
import com.example.CourseService.repository.CourseRepository;
import com.example.CourseService.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/course-admin")
public class CourseAdminController {
    @Autowired
CourseService courseService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StreamingService streamingService;
    @PostMapping(value = "/create-course", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity createCourse(@RequestPart("courseId") int courseId,
                                       @RequestPart("courseName") String courseName,
                                       @RequestPart("courseDetail") String courseDetail,
                                       @RequestPart("price") String price,
                                       @RequestPart("category") String category,
                                       @RequestPart("courseImage") MultipartFile courseImage){
        String image;
        try {
            image = streamingService.uploadImage(courseImage).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return courseService.createCourse(courseId,courseName,courseDetail,Integer.parseInt(price),category,image);
    }
    @PostMapping("/create-unit")
    public ResponseEntity createUnit(@ModelAttribute UnitCreationRequest unitCreationRequest){
        return courseService.createUnit(unitCreationRequest);
    }
    @PostMapping(value = "/create-video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity createVideo(@RequestPart("videoName") String videoName,
                                      @RequestPart("description") String description,
                                      @RequestPart("fileName") MultipartFile fileName,
                                      @RequestPart("unitId") int unitId) throws IOException {
        return courseService.createVideo(videoName,description,fileName,unitId);
    }
    @PostMapping(value = "/create-document", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity createDocument(@RequestPart("unitId") int unitId,
                                         @RequestPart("documentName") String documentName,
                                         @RequestPart("documentDescription") String documentDescription,
                                         @RequestPart("filename") MultipartFile filename) throws IOException {
        return courseService.createDocument(unitId,documentName,documentDescription,filename);
    }

    @GetMapping("/delete-course")
    public ResponseEntity deleteCourse(@RequestParam("courseId") int courseId){
        return courseService.deleteCourse(courseId);
    }

    @GetMapping("/delete-unit")
    public ResponseEntity deleteUnit(@RequestParam("unitId") int unitId){
        return courseService.deleteUnit(unitId);
    }

    @GetMapping("/delete-video")
    public ResponseEntity deleteVideo(@RequestParam("videoId") int videoId){
        return courseService.deleteVideo(videoId);
    }

    @GetMapping("/delete-document")
    public ResponseEntity deleteDocument(@RequestParam("documentId") int documentId){
        return courseService.deleteDocument(documentId);
    }

    @GetMapping("/publish-handler")
    public ResponseEntity publishHandler(@RequestParam("courseId") int courseId){
        return courseService.publishHandler(courseId);
    }

    @GetMapping("/showAll")
    public Page<CourseEntity> showAllCourse(@RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("course_id").descending());
        return courseRepository.findAllByIsDeleteFalse(pageable);
    }

    @PostMapping(value="/edit-course", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity editCourse(@RequestPart("courseId") int courseId,
                                            @RequestPart("courseName") String courseName,
                                            @RequestPart("courseDetail") String courseDetail,
                                            @RequestPart("price") int price,
                                            @RequestPart("category") String category,
                                            @RequestPart("courseImage") MultipartFile courseImage){
        return courseService.editCourseHandler(courseId, courseName, courseDetail,price,category,courseImage);
    }

    @PostMapping("/edit-unit")
    public ResponseEntity editUnit(@RequestBody EditUnitDTO editUnitDTO){
        return courseService.editUnitHandler(editUnitDTO);
    }
}
