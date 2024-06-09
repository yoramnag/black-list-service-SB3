package com.example.blackListService.service;

import com.example.blackListService.dto.BlackListDto;
import com.example.blackListService.exception.BlackListAlreadyExistsException;
import com.example.blackListService.exception.LuhnException;
import com.example.blackListService.mapper.BlackListMapper;
import com.example.blackListService.repository.BlackListRepository;
import com.example.blackListService.entity.BlackList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlackListService {

    private BlackListRepository blackListRepository;

    /**
     *
     * @return
     */
    public List<BlackList> findAll(){
        return blackListRepository.findAll();
    }

    /**
     * Save new credit card to the black list DB , we are getting blackListDto and we are mapping
     * it to blackList
     * @param blackListDto
     */
    public void saveBlackListRepository(BlackListDto blackListDto){
        if(findBlackListCard(blackListDto.getCreditCard())){
            throw new BlackListAlreadyExistsException(blackListDto.getCreditCard() + " " + "allready exist in the data base");
        }
        BlackList blackList = BlackListMapper.mapToBlackList(blackListDto,new BlackList());
        // Validate credit card with luhn algorithm
        if (!Utils.luhnValidetor(blackList.getCreditCard())) {
            throw new LuhnException(Utils.mask(blackListDto.getCreditCard()) + " is not valid");
        }
        blackList.setMaskCreditCard(Utils.mask(blackList.getCreditCard()));
        blackList.setCreditCard(Utils.maskCreditCard(blackList.getCreditCard()));
        blackListRepository.save(blackList);
    }

    public boolean findBlackListCard(String creditCardNumber){
        creditCardNumber = Utils.maskCreditCard(creditCardNumber);
        Optional<BlackList> blackList = blackListRepository.findByCreditCard(creditCardNumber);
        if(!blackList.isPresent()) {
            return false;
        }
        return true;
    }



}
