package com.green.Nolloo.admin.service;

import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.member.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //축제 데이터 csv를 통한 등록 기능
    @Override
    public void insertCsv(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertFestival", itemVO);
    }

    @Override
    public List<MemberVO> memberInfo() {
        return sqlSession.selectList("memberMapper.memberInfo");
    }


}
