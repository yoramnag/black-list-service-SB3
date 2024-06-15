package com.example.blackListService.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BlackListDto {

    @NotEmpty(message = "credit card can NOT be null or empty")
    @Pattern(regexp = "(^$|[0-9]{16})", message = "credit card must be 16 digits")
    private String creditCard;

    private String maskCreditCard;

}
