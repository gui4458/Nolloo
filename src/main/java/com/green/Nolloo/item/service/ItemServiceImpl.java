package com.green.Nolloo.item.service;


import com.green.Nolloo.item.vo.CategoryVO;

import com.green.Nolloo.item.vo.CateVO;

import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.item.vo.PageVO;
import com.green.Nolloo.search.vo.SearchVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int selectNextItemCode() {
        return sqlSession.selectOne("itemMapper.selectNextItemCode");
    }

    @Override
    public void itemListUpdateCnt(ItemVO itemVO) {
        sqlSession.update("itemMapper.itemListUpdateCnt",itemVO);
    }

    @Override
    public int itemAllCnt(int cateCode) {
        return sqlSession.selectOne("itemMapper.itemAllCnt",cateCode);
    }

    //나의 파티 조회
    @Override
    public List<ItemVO> selectMyParty(ItemVO itemVO) {
        return sqlSession.selectList("itemMapper.selectMyParty",itemVO);
    }

    @Override
    public List<ItemVO> selectCalendarPartyList() {
        return sqlSession.selectList("itemMapper.selectCalendarPartyList");
    }

    //나의 파티 상세
    @Override
    public ItemVO selectItemDetail(int itemCode) {
        return sqlSession.selectOne("itemMapper.selectItemDetail",itemCode);
    }

    //파티 상세 수정
    @Transactional(rollbackFor = Exception.class)
    //쿼리 분리된 여러 개의 기능을 하나로 묶어 주는 것 두개의 쿼리가 동시에 실행 되어야 하는 것
    @Override
    public void updateItemDetail(ItemVO itemVO) {
        sqlSession.update("itemMapper.updateItemDetail",itemVO);
        if(itemVO.getImgList().size() != 0){
            sqlSession.insert("itemMapper.insertImage",itemVO);
        }
    }

    @Override
    public void deleteItemImg(ImgVO imgVO) {
        sqlSession.delete("itemMapper.deleteItemImg", imgVO);
    }

    @Override
    public List<String> selectItemImage(ItemVO itemVO) {
        return sqlSession.selectList("itemMapper.selectItemImage",itemVO);
    }

    @Override
    public List<CateVO> selectCate() {
        return sqlSession.selectList("itemMapper.selectCate");
    }


    @Override
    public String findAttachedFileNameByImgCode(ImgVO imgVO) {
        return sqlSession.selectOne("itemMapper.findAttachedFileNameByImgCode", imgVO);
    }

    @Override
    public void insertMainImg(ItemVO itemVO) {
        sqlSession.insert("itemMapper.insertImage",itemVO);
    }


    @Override
    public int wishCount(int itemCode) {
        return sqlSession.selectOne("wishMapper.wishCount",itemCode);
    }

    // item 등록
    @Override
    @Transactional(rollbackFor =  Exception.class)
    public void insertParty(ItemVO itemVO) {
        sqlSession.insert("itemMapper.insertParty",itemVO);
        sqlSession.insert("itemMapper.insertImgs",itemVO);
        sqlSession.update("itemMapper.updatePeopleCnt",itemVO);
        sqlSession.insert("reserveMapper.insertReserve",itemVO);
        sqlSession.insert("chatMapper.insertChatRoom",itemVO.getChatVO());
    }
    //item 목록조회
    @Override
    public List<ItemVO> selectPartyList(PageVO pageVO) {
        return sqlSession.selectList("itemMapper.selectPartyList", pageVO);
    }


    @Override
    public List<ItemVO> selectByDistance(SearchVO searchVO) {
        return sqlSession.selectList("itemMapper.selectByDistance",searchVO);
    }

    @Override
    public List<ItemVO> searchByDistance(SearchVO searchVO) {
        return sqlSession.selectList("itemMapper.searchByDistance",searchVO);
    }

    @Override
    public List<ItemVO> searchByReadCnt(String memberId) {
        return sqlSession.selectList("itemMapper.selectByReadCnt",memberId);
    }

    @Override
    public List<CategoryVO> selectAllCategory() {
        return sqlSession.selectList("itemMapper.selectAllCategory");
    }


    //itemDetail 목록조회
    @Override
    public ItemVO selectPartyDetail(ItemVO itemVO) {
        return sqlSession.selectOne("itemMapper.selectPartyDetail", itemVO);
    }

    // item 삭제 쿼리
    @Override
    @Transactional(rollbackFor =  Exception.class)
    public void deleteParty(ItemVO itemVO) {
        sqlSession.delete("itemMapper.deleteImg",itemVO);
        sqlSession.delete("itemMapper.deleteChatRoom",itemVO);
        sqlSession.delete("itemMapper.deleteParty",itemVO);
    }

    @Override
    public void updateParty(ItemVO itemVO) {
        sqlSession.update("itemMapper.updateParty",itemVO);
    }



}
