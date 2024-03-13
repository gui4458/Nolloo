package com.green.Nolloo.search.vo;

import com.green.Nolloo.item.vo.ItemVO;

import com.green.Nolloo.item.vo.PageVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVO extends PageVO {
    private String searchOption;
    private String searchText;
    private String from;
    private String to;
}
