package com.example.blackListService.service;

import com.example.blackListService.dto.BlackListDto;
import com.example.blackListService.exception.BlackListAllReadyExistsException;
import com.example.blackListService.exception.BlackListCardNotFoundException;
import com.example.blackListService.exception.LuhnException;
import com.example.blackListService.mapper.BlackListMapper;
import com.example.blackListService.repository.BlackListRepository;
import com.example.blackListService.entity.BlackList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlackListService {

    private BlackListRepository blackListRepository;

    /**
     * get all records from BlackList table
     * @return list of all black list credit cards from BlackList table
     */
    public List<BlackListDto> findAll(){
        List<BlackList> blackListList = blackListRepository.findAll();
        List<BlackListDto> blackListDtos = new ArrayList<>();
        for (BlackList blackList : blackListList) {
            blackListDtos.add(BlackListMapper.mapToBlackListDto(blackList, new BlackListDto()));
        }
        return blackListDtos;
    }

    /**
     *
     * @param creditCard
     * @return
     */
    public BlackListDto findByCreditCardNumber(String creditCard) {
        Optional<BlackList> blackListOpt = findBlackListCardByCardNumber(creditCard);
        if (!blackListOpt.isPresent()) {
            throw new BlackListCardNotFoundException("black list card", "creditCard", creditCard);
        }
        BlackListDto blackListDto = BlackListMapper.mapToBlackListDto(blackListOpt,new BlackListDto());
        return blackListDto;
    }

    /**
     * Save new credit card to the black list DB , we are getting blackListDto and we are mapping
     * it to blackList
     * @param blackListDto
     */
    public void saveBlackListRepository(BlackListDto blackListDto){
        if (checkIfCreditCradAllReadyExist(blackListDto.getCreditCard())){
            throw new BlackListAllReadyExistsException(blackListDto.getCreditCard() + " " + "allready exist in the data base");
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

    /**
     * delete black list credit card by credit card number
     * @param creditCardNumber credit card number to delete
     */
    public void deleteBlackListCardByCardNumber(String creditCardNumber){
        Optional<BlackList> blackListOpt = findBlackListCardByCardNumber(creditCardNumber);
        if (!blackListOpt.isPresent()) {
            throw new BlackListCardNotFoundException("black list card", "creditCard", creditCardNumber);
        }
        blackListRepository.deleteById(blackListOpt.stream().toList().get(0).getId());
    }

    /**
     * @param creditCardNumber
     * @return
     */
    private Optional<BlackList> findBlackListCardByCardNumber(String creditCardNumber){
        creditCardNumber = Utils.maskCreditCard(creditCardNumber);
        Optional<BlackList> blackList = blackListRepository.findByCreditCard(creditCardNumber);
        if(!blackList.isPresent()) {
            throw new BlackListCardNotFoundException("black list card", "Credit Card", String.valueOf(creditCardNumber));
        }
        return blackList;
    }

    /**
     *
     * @param creditCardNumber
     * @return
     */
    private boolean checkIfCreditCradAllReadyExist(String creditCardNumber){
        creditCardNumber = Utils.maskCreditCard(creditCardNumber);
        Optional<BlackList> blackList = blackListRepository.findByCreditCard(creditCardNumber);
        if(!blackList.isPresent()) {
            return false;
        }
        return true;
    }






}
