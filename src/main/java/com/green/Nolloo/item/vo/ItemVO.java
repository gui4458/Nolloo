package com.green.Nolloo.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemVO {
    private int itemCode;
    private String itemTitle;
    private String itemContent;
    private String itemPrice;
    private String itemDate;
    private String itemPeople;
    private String itemPlace;
    private String placeDetail;
    private int cateCode;
}
