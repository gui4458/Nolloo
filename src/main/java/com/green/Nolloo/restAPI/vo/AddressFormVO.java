package com.green.Nolloo.restAPI.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
@Data
public class AddressFormVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String zipNo;
    private String sido;
    private String sigungu;
    private String doro;
    private String buildNo1;
    private String buildNm;
}
