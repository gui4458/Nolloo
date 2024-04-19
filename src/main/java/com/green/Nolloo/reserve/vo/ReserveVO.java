package com.green.Nolloo.reserve.vo;

import com.green.Nolloo.item.vo.ItemVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReserveVO {
    private int reserveCode;
    private String memberId;
    private String reserveDate;
    private int itemCode;
    private ItemVO itemVO;
    private List<Integer> reserveCodeList;
    private String searchOption;
    private String searchText;
    private String from;
    private String to;


}
