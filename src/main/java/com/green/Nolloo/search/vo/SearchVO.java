package com.green.Nolloo.search.vo;

import com.green.Nolloo.item.vo.ItemVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO extends ItemVO {
    private String searchOption;
    private String searchText;
    private String from;
    private String to;
}
