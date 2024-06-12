package com.example.blackListService.rest;

import com.example.blackListService.constants.BlackListConstants;
import com.example.blackListService.dto.BlackListDto;
import com.example.blackListService.dto.ResponseDto;
import com.example.blackListService.service.BlackListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api" , produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BlackListRestController {

    private BlackListService blackListService;

    /**
     * get all records from BlackList table
     * @return all black list cards from the BlackList table
     */
    @GetMapping("/blacklist")
    public ResponseEntity<List<BlackListDto>> retrieveBlackListCard() {
        List<BlackListDto> blackListDtos = blackListService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(blackListDtos);
    }

    /**
     * get black list card by credit Card number
     * @param creditCardNumbet black list credit card number
     * @return status OK if credit card exist on the BlackList table
     */
    @GetMapping("/blacklist/{creditCardNumbet}")
    public ResponseEntity<Optional<BlackListDto>> retrieveBlackListCard(@PathVariable String creditCardNumbet) {
        Optional<BlackListDto> blackListDto = blackListService.findByCreditCardNumber(creditCardNumbet);
        return ResponseEntity.status(HttpStatus.OK).body(blackListDto);
    }

    /**
     * aad new credit card to the BlackList table
     * @param blackListDto credit card info
     * @return status created if credit card successfully added to the BlackList table
     */
    @PostMapping("/blacklist")
    public ResponseEntity<ResponseDto> createBlackListCard(@RequestBody BlackListDto blackListDto){
        blackListService.saveBlackListRepository(blackListDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BlackListConstants.STATUS_201,BlackListConstants.MESSAGE_201));
    }






}
