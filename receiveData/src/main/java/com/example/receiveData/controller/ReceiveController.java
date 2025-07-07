package com.example.receiveData.controller;

import com.example.receiveData.client.ProfileService;
import com.example.receiveData.client.SendDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveController {
    @Autowired
    SendDataService sendDataService;

    @Autowired
    ProfileService profileService;
    @GetMapping("/send")
    public String send(@RequestParam("myParam") String myParam){
        return sendDataService.sendData();
    }

    @GetMapping("/profile")
    public String profile(){
        String token = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        if(token==null){
           System.out.println("dmmmmmmmm");
        }
        System.out.println(token);
        return profileService.showUserProfile("Bearer "+token).getBody().getFullName();
    }
}
