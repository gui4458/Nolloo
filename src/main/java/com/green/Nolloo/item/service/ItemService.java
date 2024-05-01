package com.green.Nolloo.item.service;


import com.green.Nolloo.item.vo.CategoryVO;
import com.green.Nolloo.item.vo.CateVO;
import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.item.vo.PageVO;
import com.green.Nolloo.search.vo.SearchVO;

import java.util.List;

public interface ItemService {
    //하트 카운트
    int wishCount(int itemCode);

    // board 등록하는 메소드
    void insertParty(ItemVO itemVO);
    // board 목록 조회 메소드
    List<ItemVO> selectPartyList(PageVO pageVO);
    // board 디테일
    List<ItemVO> selectByDistance(SearchVO searchVO);

    List<ItemVO> searchByDistance(SearchVO searchVO);
    List<ItemVO> searchByReadCnt(String memberId);

    List<CategoryVO> selectAllCategory();

    ItemVO selectPartyDetail(ItemVO itemVO);
    //board 삭제하는 메소드
    void deleteParty(ItemVO itemVO);

    void updateParty(ItemVO itemVO);

    int selectNextItemCode();

    void itemListUpdateCnt(ItemVO itemVO);

    int itemAllCnt(int cateCode);

    List<ItemVO> selectMyParty(ItemVO itemVO);

    List<ItemVO> selectCalendarPartyList();

    //나의 파티 조회
    ItemVO selectItemDetail(int itemCode);

    void updateItemDetail(ItemVO itemVO);

    //아이템 이미지 삭제
    void deleteItemImg(ImgVO imgVO);

    List<String> selectItemImage(ItemVO itemVO);

    List<CateVO> selectCate();

    //imgCode로 첨부파일명 조회
    String findAttachedFileNameByImgCode(ImgVO imgVO);

    void insertMainImg(ItemVO itemVO);

    List<ItemVO> titleSearch(SearchVO searchVO);
    List<ItemVO> contentSearch(SearchVO searchVO);

}
