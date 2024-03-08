package com.green.Nolloo.item.service;

import com.green.Nolloo.item.vo.ItemVO;
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


    // item 등록
    @Override
    @Transactional(rollbackFor =  Exception.class)
    public void insertParty(ItemVO itemVO) {
        sqlSession.insert("itemMapper.insertParty",itemVO);

        sqlSession.insert("itemMapper.insertImgs",itemVO);
    }
    //item 목록조회
    @Override
    public List<ItemVO> selectPartyList(ItemVO itemVO) {
        return sqlSession.selectList("itemMapper.selectPartyList", itemVO);

    }

    @Override
    public ItemVO selectPartyDetail(ItemVO itemVO) {
        return sqlSession.selectOne("itemMapper.selectPartyDetail", itemVO);
    }

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public void deleteParty(ItemVO itemVO) {
        sqlSession.delete("itemMapper.deleteImg",itemVO);
        sqlSession.delete("itemMapper.deleteParty",itemVO);
    }

    @Override
    public void updateParty(ItemVO itemVO) {
        sqlSession.update("itemMapper.updateParty",itemVO);
    }


}
