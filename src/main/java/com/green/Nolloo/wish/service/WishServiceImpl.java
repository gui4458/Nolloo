package com.green.Nolloo.wish.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wishService")
public class WishServiceImpl {
    @Autowired
    private SqlSessionTemplate sqlSession;

}
