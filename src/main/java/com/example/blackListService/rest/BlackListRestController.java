package com.example.blackListService.rest;

import com.example.blackListService.constants.BlackListConstants;
import com.example.blackListService.dto.BlackListDto;
import com.example.blackListService.dto.ErrorResponseDto;
import com.example.blackListService.dto.ResponseDto;
import com.example.blackListService.service.BlackListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for black list ",
        description = "CRUD REST APIs in black list to CREATE, FETCH AND DELETE black list"
)
@RestController
@RequestMapping(path = "/api" , produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class BlackListRestController {

    private BlackListService blackListService;

    /**
     * get all records from BlackList table
     * @return all black list cards from the BlackList table
     */
    @Operation(
            summary = "get all black list REST API",
            description = "get all credit cards from the BlackList table"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/getAllBlacklist")
    public ResponseEntity<List<BlackListDto>> retrieveBlackListCard() {
        List<BlackListDto> blackListDtos = blackListService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(blackListDtos);
    }

    /**
     * get black list card by credit Card number
     * @param creditCardNumber black list credit card number
     * @return status OK if credit card exist on the BlackList table
     */
    @Operation(
            summary = "get black list REST API",
            description = "get black list card by credit Card number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request, credit card must be 16 digits",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Black List NOT found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/getBlacklist")
    public ResponseEntity<BlackListDto> retrieveBlackListCard(@RequestParam
                                                                  @Pattern(regexp = "(^$|[0-9]{16})", message = "credit card must be 16 digits")
                                                                  String creditCardNumber) {
        BlackListDto blackListDto = blackListService.findByCreditCardNumber(creditCardNumber);
        return ResponseEntity.status(HttpStatus.OK).body(blackListDto);
    }

    /**
     * aad new credit card to the BlackList table
     * @param blackListDto credit card info
     * @return status created if credit card successfully added to the BlackList table
     */
    @Operation(
            summary = "Create black list REST API",
            description = "aad new credit card to the BlackList table"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Black List All ready Exists Exception OR Luhn Exception",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/createBlacklist")
    public ResponseEntity<ResponseDto> createBlackListCard(@Valid @RequestBody BlackListDto blackListDto){
        blackListService.saveBlackListRepository(blackListDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(BlackListConstants.STATUS_201,BlackListConstants.MESSAGE_201));
    }

    /**
     * delete credit card from BlackList table
     * @param creditCardNumber black list credit card number
     * @return
     */
    @Operation(
            summary = "delete black list REST API",
            description = "delete black list card by credit Card number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request, credit card must be 16 digits",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Black List NOT found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/deleteBlacklist")
    public ResponseEntity<ResponseDto> deleteCreditCardByCreditNumber(@RequestParam
                                                                          @Pattern(regexp = "(^$|[0-9]{16})", message = "credit card must be 16 digits")
                                                                          String creditCardNumber) {
        blackListService.deleteBlackListCardByCardNumber(creditCardNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(BlackListConstants.STATUS_200,BlackListConstants.MESSAGE_200));
    }






}
