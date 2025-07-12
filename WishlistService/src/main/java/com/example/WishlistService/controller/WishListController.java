package com.example.WishlistService.controller;

import com.example.WishlistService.service.WishListService;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
   WishListService wishListService;
    @GetMapping("/add-to-wish-list")
    public ResponseEntity addToWishList(@RequestParam int courseId){
      return wishListService.addToWishListHandler(courseId);

    }

    @GetMapping("/delete-wish-list")
    public ResponseEntity deleteWishList(@RequestParam int courseId){
        return wishListService.deleteWishListHandler(courseId);
    }

    @GetMapping("/show-wish-course")
    public ResponseEntity showWishCourse(){
        return wishListService.showCourseHandler();
    }
}
