package com.green.Nolloo.item.service;

import com.green.Nolloo.item.vo.CategoryVO;
import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.item.vo.ItemVO;
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
    @Override
    public void updateItemDetail(ItemVO itemVO) {
        sqlSession.update("itemMapper.updateItemDetail",itemVO);
        sqlSession.insert("itemMapper.insertImage",itemVO);

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
    public String findAttachedFileNameByImgCode(ImgVO imgVO) {
        return sqlSession.selectOne("itemMapper.findAttachedFileNameByImgCode", imgVO);
    }


    // item 등록
    @Override
    @Transactional(rollbackFor =  Exception.class)
    public void insertParty(ItemVO itemVO) {
        sqlSession.insert("itemMapper.insertParty",itemVO);

        sqlSession.insert("itemMapper.insertImgs",itemVO);

        sqlSession.update("itemMapper.updatePeopleCnt",itemVO);

        sqlSession.insert("chatMapper.insertChatRoom",itemVO.getChatVO());
    }
    //item 목록조회
    @Override
    public List<ItemVO> selectPartyList(SearchVO searchVO) {
        return sqlSession.selectList("itemMapper.selectPartyList", searchVO);

    }

    @Override
    public List<ItemVO> selectByDistance() {
        return sqlSession.selectList("itemMapper.selectByDistance");
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
