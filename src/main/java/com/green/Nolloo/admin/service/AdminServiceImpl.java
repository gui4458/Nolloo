package com.green.Nolloo.admin.service;

import com.green.Nolloo.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //축제 데이터 csv를 통한 등록 기능
    @Override
    public void insertCsv(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertFestival", itemVO);
    }
}
