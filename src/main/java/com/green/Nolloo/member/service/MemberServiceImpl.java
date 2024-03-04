package com.green.Nolloo.member.service;

import com.green.Nolloo.member.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public void join(MemberVO memberVO){
        sqlSession.insert("memberMapper.join", memberVO);
    }

    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login",memberVO);
    }

    @Override
    public MemberVO memberInfo(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.memberInfo",memberVO);
    }

    @Override
    public void revise(MemberVO memberVO) {
        sqlSession.update("memberMapper.revise",memberVO);
    }

    @Override
    public void deleteMember(MemberVO memberVO) {
        sqlSession.delete("memberMapper.deleteMember",memberVO);
    }
}
