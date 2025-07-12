package com.example.CourseService.service;

import com.example.CourseService.dto.*;
import com.example.CourseService.model.CourseEntity;
import com.example.CourseService.model.DocumentEntity;
import com.example.CourseService.model.UnitEntity;
import com.example.CourseService.model.VideoEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CourseService {
    Page<CourseDto> showAllCourseHandler(int page, int size);

    CourseEntity showCourseDetailHandler(int courseId);

    ResponseEntity createCourse(int courseId,
                                String courseName,
                                String courseDetail,
                                int price,
                                String courseImage);

    public ResponseEntity<UnitEntity> createUnit(UnitCreationRequest unitCreationRequest);

    public ResponseEntity<VideoEntity> createVideo(String videoName,
                                                   String description,
                                                   MultipartFile fileName,
                                                   int unitId) throws IOException;
    public ResponseEntity<DocumentEntity> createDocument(int unitId,
                                                         String documentName,
                                                         String documentDescription,
                                                         MultipartFile fileName) throws IOException;
    public ResponseEntity deleteCourse(int courseId);

    ResponseEntity deleteUnit(int unitId);

    ResponseEntity deleteVideo(int videoId);

    ResponseEntity deleteDocument(int documentId);

    ResponseEntity publishHandler(int courseId);

    ResponseEntity editCourseHandler(int courseId, String courseName, String courseDetail, int price, MultipartFile courseImage);

    ResponseEntity editUnitHandler(EditUnitDTO editUnitDTO);
}
