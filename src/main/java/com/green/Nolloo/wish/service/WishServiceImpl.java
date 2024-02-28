package com.green.Nolloo.wish.service;

import com.green.Nolloo.wish.vo.WishViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wishService")
public class WishServiceImpl implements WishService{
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public void insertWish() {
        sqlSession.insert("wishMapper.insertWish");
    }

    @Override
    public List<WishViewVO> selectWish() {
        return sqlSession.selectList("wishMapper.selectWish");
    }


}
