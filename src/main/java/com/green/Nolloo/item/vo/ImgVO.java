package com.green.Nolloo.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ImgVO {
    private int imgCode;
    private String originFileName;
    private String attachedFileName;
    private String isMain;
    private int itemCode;
}
