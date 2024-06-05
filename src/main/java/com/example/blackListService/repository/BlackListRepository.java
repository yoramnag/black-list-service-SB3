package com.example.blackListService.repository;

import com.example.blackListService.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList, Integer> {

    Optional<BlackList> findByCreditCard(String creditCardNumber);

}
