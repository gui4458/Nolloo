package com.green.Nolloo.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO {
    private int totalDataCnt; //전체 데이터 수 (전체 게시글 수)
    private int limit; //
    private int offset; //
    private int cateCode;

}
