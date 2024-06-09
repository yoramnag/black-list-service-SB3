package com.example.blackListService.mapper;

import com.example.blackListService.dto.BlackListDto;
import com.example.blackListService.entity.BlackList;

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
}
