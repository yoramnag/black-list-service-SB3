package com.example.blackListService.rest;

import com.example.blackListService.entity.BlackList;
import com.example.blackListService.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class BlackListRestController {

    @Autowired
    private BlackListService blackListService;

    @GetMapping("/blacklist")
    public List<BlackList> retrieveAllBlackListCards(){
        return blackListService.findAll();
    }


}
