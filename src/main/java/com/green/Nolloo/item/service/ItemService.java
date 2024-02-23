package com.green.Nolloo.item.service;

import com.green.Nolloo.item.vo.ItemVO;

import java.util.List;

public interface ItemService {
    // item 등록하는 메소드
    void insertItem(ItemVO itemVO);
    // item 목록 조회 메소드
    List<ItemVO> selectItem();
}
