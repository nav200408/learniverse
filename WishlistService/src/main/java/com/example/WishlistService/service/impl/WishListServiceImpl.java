package com.example.WishlistService.service.impl;

import com.example.WishlistService.client.CourseService;
import com.example.WishlistService.dto.CourseDto;
import com.example.WishlistService.model.WishList;
import com.example.WishlistService.repository.WishListRepository;
import com.example.WishlistService.service.WishListService;
import com.example.WishlistService.exception.ResourceNotFoundException;
import com.example.WishlistService.exception.WishlistProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    CourseService courseService;
    @Override
    public ResponseEntity addToWishListHandler(int courseId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(wishListRepository.findByUsernameAndCourseId(username,courseId).isPresent()) {
            throw new WishlistProcessingException("Course is already in your wishlist.");
        }
        else
        {
            WishList wishList = new WishList(username, courseId);
            wishListRepository.saveAndFlush(wishList);
            return ResponseEntity.ok("success");
        }
    }

    @Override
    public ResponseEntity deleteWishListHandler(int courseId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        WishList wishList = wishListRepository.findByUsernameAndCourseId(username,courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Wishlist item not found for course id: " + courseId));
        wishListRepository.delete(wishList);
        return ResponseEntity.ok("success");
    }

    @Override
    public ResponseEntity showCourseHandler() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Integer> courseIds = wishListRepository.findCourseIdsByUsername(username);
        
        if (courseIds == null || courseIds.isEmpty()) {
            throw new ResourceNotFoundException("No courses found in your wishlist.");
        }
        
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        System.out.println(token);
        List<CourseDto> courseDtos = courseService.getCourseByIds(courseIds,"Bearer "+token);
        return ResponseEntity.ok(courseDtos);
    }


}
