package com.example.blackListService.service;

import com.example.blackListService.dao.BlackListRepository;
import com.example.blackListService.entity.BlackList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListService {

    @Autowired
    private BlackListRepository blackListRepository;

    // Return all BlackList
    public List<BlackList> findAll(){
        return blackListRepository.findAll();
    }



}
