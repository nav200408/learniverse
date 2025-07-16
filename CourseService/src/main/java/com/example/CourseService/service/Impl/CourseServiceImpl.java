package com.example.CourseService.service.Impl;

import com.example.CourseService.client.StreamingService;
import com.example.CourseService.dto.*;
import com.example.CourseService.model.CourseEntity;
import com.example.CourseService.model.DocumentEntity;
import com.example.CourseService.model.UnitEntity;
import com.example.CourseService.model.VideoEntity;
import com.example.CourseService.repository.CourseRepository;
import com.example.CourseService.repository.DocumentRepository;
import com.example.CourseService.repository.UnitRepository;
import com.example.CourseService.repository.VideoRepository;
import com.example.CourseService.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StreamingService streamingService;
    @Autowired
    UnitRepository unitRepository;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @Override
    public Page<CourseDto> showAllCourseHandler(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("courseId").descending());
        Page<CourseEntity> courseEntities = courseRepository.findAllByIsDeleteFalseAndIsPublishTrue(pageable);
        Page<CourseDto> dtoPage = courseEntities.map(
                entity -> new CourseDto(entity.getCourseName(), entity.getCourseDetail(), entity.getPrice(), entity.getCourseImage(), entity.getCourseId())
        );
        return dtoPage;
    }

    @Override
    public CourseEntity showCourseDetailHandler(int courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    public ResponseEntity<CourseEntity> createCourse(int courseId,
                                                    String courseName,
                                                     String courseDetail,
                                                    int price,
                                                   String category,
                                                   String courseImage) {
        CourseEntity course = new CourseEntity();
        course.setCourseDetail(courseDetail);
        course.setCourseName(courseName);
        course.setPrice(price);
        course.setCourseImage(courseImage);
        kafkaTemplate.send("category",new CategoryDto(category,courseId,"create"));
        courseRepository.saveAndFlush(course);
        return ResponseEntity.ok().body(course);
    }
@Override
    public ResponseEntity<UnitEntity> createUnit(UnitCreationRequest unitCreationRequest){
        UnitEntity unitEntity = new UnitEntity();
        CourseEntity course = courseRepository.findById(unitCreationRequest.getCourseId()).get();
        unitEntity.setDescription(unitEntity.getDescription());
        unitEntity.setUnitName(unitCreationRequest.getUnitName());
        unitEntity.setCourse(course);
        unitRepository.saveAndFlush(unitEntity);
        return ResponseEntity.ok().body(unitEntity);
    }
@Override
    public ResponseEntity<VideoEntity> createVideo(String videoName,
                                                   String description,
                                                   MultipartFile fileName,
                                                    int unitId) throws IOException {
        VideoEntity videoEntity = new VideoEntity();
        UnitEntity unit = unitRepository.findById(unitId).get();
        videoEntity.setVideoName(videoName);
        videoEntity.setDescription(description);
        String filename = streamingService.uploadVideo(fileName).getBody();
        videoEntity.setFilename(filename);
        videoEntity.setUnit(unit);
        videoRepository.saveAndFlush(videoEntity);
        return ResponseEntity.ok(videoEntity);
    }
@Override
    public ResponseEntity<DocumentEntity> createDocument(int unitId,
                                                         String documentName,
                                                         String documentDescription,
                                                         MultipartFile fileName) throws IOException {
        DocumentEntity document = new DocumentEntity();
        UnitEntity unit = unitRepository.findById(unitId).get();
        document.setDocumentName(documentName);
        document.setDocumentDescribtion(documentDescription);
        String filename = streamingService.uploadPDF(fileName).getBody();
        document.setFilename(filename);
        document.setUnit(unit);
        documentRepository.saveAndFlush(document);
        return ResponseEntity.ok(document);

    }

    @Override
    public ResponseEntity deleteCourse(int courseId) {
      CourseEntity course = courseRepository.findById(courseId).get();
      course.setDelete(true);
      courseRepository.saveAndFlush(course);
        return ResponseEntity.ok("delete success");
    }

    @Override
    public ResponseEntity deleteUnit(int unitId) {
        UnitEntity unit = unitRepository.findById(unitId).get();
        unit.setDelete(true);
        unitRepository.saveAndFlush(unit);
        return ResponseEntity.ok("delete success");
    }

    @Override
    public ResponseEntity deleteVideo(int videoId) {
        videoRepository.deleteById(videoId);
        return ResponseEntity.ok("delete success");
    }

    @Override
    public ResponseEntity deleteDocument(int documentId) {
        documentRepository.deleteById(documentId);
        return ResponseEntity.ok("delete success");
    }

    @Override
    public ResponseEntity publishHandler(int courseId) {
        CourseEntity course = courseRepository.findById(courseId).get();
        course.setPublish(!course.isPublish());
        return ResponseEntity.ok("success");
    }

    @Override
    public ResponseEntity editCourseHandler(int courseId, String courseName, String courseDetail, int price, String category, MultipartFile courseImage) {
        CourseEntity course = courseRepository.findById(courseId).get();
        String image;
        try {
            image = streamingService.uploadImage(courseImage).getBody();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        course.setCourseImage(image);
        course.setCourseName(courseName);
        course.setCourseDetail(courseDetail);
        course.setPrice(price);
        kafkaTemplate.send("category",new CategoryDto(category,courseId,"edit"));
        courseRepository.saveAndFlush(course);
        return ResponseEntity.ok(course);
    }

    @Override
    public ResponseEntity editUnitHandler(EditUnitDTO editUnitDTO) {
        UnitEntity unitEntity = unitRepository.findById(editUnitDTO.getUnitId()).get();
        unitEntity.setUnitName(editUnitDTO.getUnitName());
        unitEntity.setDescription(editUnitDTO.getUnitDesciption());
        unitRepository.saveAndFlush(unitEntity);
        return ResponseEntity.ok(unitEntity);
    }
}
