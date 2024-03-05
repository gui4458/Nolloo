package com.green.Nolloo.reserve.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReserveVO {
    private int reserveCode;
    private String memberId;
    private String reserveDate;
    private int reservePrice;
    private int itemCode;


}
