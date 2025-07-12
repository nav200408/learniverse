package com.example.WishlistService.service;

import org.springframework.http.ResponseEntity;

public interface WishListService {
    public ResponseEntity addToWishListHandler(int courseId);

    ResponseEntity deleteWishListHandler(int courseId);

    ResponseEntity showCourseHandler();
}
