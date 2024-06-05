package com.example.blackListService.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlackList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="credit_card")
    private String creditCard;

    @Column(name="mask_credit_card")
    private String maskCreditCard;

}
