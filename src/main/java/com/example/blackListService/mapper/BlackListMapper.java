package com.example.blackListService.mapper;

import com.example.blackListService.dto.BlackListDto;
import com.example.blackListService.entity.BlackList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlackListMapper {

    public static BlackListDto mapToBlackListDto(BlackList blackList,BlackListDto blackListDto){
        blackListDto.setCreditCard(blackList.getCreditCard());
        blackListDto.setMaskCreditCard(blackList.getMaskCreditCard());
        return blackListDto;
    }

    public static BlackList mapToBlackList(BlackListDto blackListDto , BlackList blackList){
        blackList.setCreditCard(blackListDto.getCreditCard());
        blackList.setMaskCreditCard(blackListDto.getMaskCreditCard());
        return blackList;
    }

    public static BlackListDto mapToBlackListDto(Optional<BlackList> blackListOpt, BlackListDto blackListDto) {
        blackListDto.setCreditCard(blackListOpt.stream().toList().get(0).getCreditCard());
        blackListDto.setMaskCreditCard(blackListOpt.stream().toList().get(0).getMaskCreditCard());
        return blackListDto;
    }
}
