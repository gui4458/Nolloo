package com.green.Nolloo.item.service;

import com.green.Nolloo.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Name;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertItem(ItemVO itemVO) {
        sqlSession.insert("itemMapper.insertItem",itemVO);
    }
}
