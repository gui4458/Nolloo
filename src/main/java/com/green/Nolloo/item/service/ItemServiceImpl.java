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

        sqlSession.insert("itemMapper.insertParty",itemVO);
    }
    //item 목록조회
    @Override
    public List<ItemVO> selectPartyList() {
        return sqlSession.selectList("itemMapper.selectPartyList");
    }

    @Override
    public ItemVO selectPartyDetail(ItemVO itemVO) {
        return sqlSession.selectOne("itemMapper.selectPartyDetail", itemVO);
    }

    @Override
    public void deleteParty(ItemVO itemVO) {
        sqlSession.delete("itemMapper.deleteParty",itemVO);
    }

    @Override
    public void updateParty(ItemVO itemVO) {
        sqlSession.update("itemMapper.updateParty",itemVO);
    }
}
