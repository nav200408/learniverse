package com.example.CategoryService.client;


import com.example.CategoryService.dto.response.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "CourseService", fallback = CourseServiceFallback.class)
public interface CourseService {
    @PostMapping("/course/getCourseByIds")
    public List<CourseDto> getCourseByIds(@RequestBody List<Integer> courseIds, @RequestHeader("Authorization") String authHeader);
}
@Component
class CourseServiceFallback implements CourseService{
    @Override
    public List<CourseDto> getCourseByIds(List<Integer> courseIds,@RequestHeader("Authorization") String authHeader) {
        return null;
    }
}
