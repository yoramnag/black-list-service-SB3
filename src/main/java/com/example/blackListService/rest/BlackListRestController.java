package com.example.blackListService.rest;

import com.example.blackListService.constants.BlackListConstants;
import com.example.blackListService.dto.BlackListDto;
import com.example.blackListService.dto.ResponseDto;
import com.example.blackListService.entity.BlackList;
import com.example.blackListService.service.BlackListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api" , produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BlackListRestController {

    private BlackListService blackListService;

//    @GetMapping("/blacklist")
//    public List<BlackList> retrieveAllBlackListCards(){
//        return blackListService.findAll();
//    }

    @PostMapping("/blacklist")
    public ResponseEntity<ResponseDto> createBlackListCard(@RequestBody BlackListDto blackListDto){
        blackListService.saveBlackListRepository(blackListDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BlackListConstants.STATUS_201,BlackListConstants.MESSAGE_201));
    }

    @GetMapping("/blacklist/{id}")
    public EntityModel<BlackListDto> retrieveBlackListCard(@PathVariable int id) {

        return null;
    }




}
