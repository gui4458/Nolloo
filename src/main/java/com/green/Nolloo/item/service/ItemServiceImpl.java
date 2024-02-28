package com.green.Nolloo.item.service;

import com.green.Nolloo.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // item 등록
    @Override
    public void insertParty(ItemVO itemVO) {

        sqlSession.insert("boardMapper.insertBoard",itemVO);
    }
    //item 목록조회
    @Override
    public List<ItemVO> selectPartyList() {
        return sqlSession.selectList("boardMapper.selectBoardList");
    }

    @Override
    public ItemVO selectPartyDetail(ItemVO itemVO) {
        return sqlSession.selectOne("boardMapper.selectBoardDetail", itemVO);
    }

    @Override
    public void deleteParty(ItemVO itemVO) {
        sqlSession.delete("boardMapper.deleteBoard",itemVO);
    }

    @Override
    public void updateParty(ItemVO itemVO) {
        sqlSession.update("boardMapper.updateBoard",itemVO);
    }
}
