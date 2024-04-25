package com.green.Nolloo.item.vo;

import com.green.Nolloo.chat.vo.ChatVO;
import com.green.Nolloo.search.vo.SearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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
    private int peopleCnt;
    private int readCnt;
    private String itemPlace;
    private String placeDetail;
    private String itemStartDate;
    private String itemEndDate;
    private String itemTel;
    private String itemUrl;
    private String itemRoadPlace;
    private String itemNumPlace;
    private double itemX;
    private double itemY;
    private int cateCode;
    private String cateName;
    private String memberId;
    private CategoryVO categoryVO;
    private List<ImgVO> imgList;
    private ChatVO chatVO;
    private String region1;
    private String region2;
    private String region3;
    private String searchOption;
    private String searchText;
    private String from;
    private String to;


    //매개변수로 전달되는 csv 행 데이터를  vo의 객 변수에 매핑
    public void setCsvData(String[] rowData){
        setItemTitle(rowData[0]);
        setItemPlace(rowData[1]);
        setItemStartDate(rowData[2]);
        setItemEndDate(rowData[3]);
        setItemContent(rowData[4]);
        setItemTel(rowData[5]);
        setItemUrl(rowData[6]);
        setItemRoadPlace(rowData[7]);
        setItemNumPlace(rowData[8]);
        setItemX(Double.parseDouble(rowData[9]));
        setItemY(Double.parseDouble(rowData[10]));
        setCateCode(Integer.parseInt(rowData[11]));
        setMemberId(rowData[12]);
    }
}
