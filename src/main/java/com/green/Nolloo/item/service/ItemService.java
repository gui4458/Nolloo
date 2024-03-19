package com.green.Nolloo.item.service;

import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.search.vo.SearchVO;

import java.util.List;

public interface ItemService {
    // board 등록하는 메소드
    void insertParty(ItemVO itemVO);
    // board 목록 조회 메소드
    List<ItemVO> selectPartyList(SearchVO searchVO);
    // board 디테일
    ItemVO selectPartyDetail(ItemVO itemVO);
    //board 삭제하는 메소드
    void deleteParty(ItemVO itemVO);

    void updateParty(ItemVO itemVO);

    int selectNextItemCode();

    void itemListUpdateCnt(ItemVO itemVO);

    int itemAllCnt(int cateCode);

    List<ItemVO> selectMyParty(ItemVO itemVO);

    ItemVO selectItemDetail(int itemCode);

    void updateItemDetail(ItemVO itemVO);
}
