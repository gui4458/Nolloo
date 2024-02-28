package com.green.Nolloo.item.service;

import com.green.Nolloo.item.vo.ItemVO;

import java.util.List;

public interface ItemService {
    // board 등록하는 메소드
    void insertParty(ItemVO itemVO);
    // board 목록 조회 메소드
    List<ItemVO> selectPartyList();
    // board 디테일
    ItemVO selectPartyDetail(ItemVO itemVO);
    //board 삭제하는 메소드
    void deleteParty(ItemVO itemVO);

    void updateParty(ItemVO itemVO);

}
