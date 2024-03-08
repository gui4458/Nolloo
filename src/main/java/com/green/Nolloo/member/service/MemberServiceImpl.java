package com.green.Nolloo.member.service;

import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.member.vo.MemberImageVO;
import com.green.Nolloo.member.vo.MemberVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
    @Autowired
    private SqlSessionTemplate sqlSession;


    //프로필 이미지+가입 메소드
    @Override
    @Transactional(rollbackFor =  Exception.class)
    public void join(MemberVO memberVO){
        sqlSession.insert("memberMapper.join", memberVO);
        sqlSession.insert("memberMapper.profileImageInsert",memberVO);
    }

    @Override
    public MemberVO login(String memberId) {
        return sqlSession.selectOne("memberMapper.login",memberId);
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

    @Override
    public MemberImageVO selectProfile(String memberId) {
        return sqlSession.selectOne("memberMapper.selectProfile",memberId);
    }
}
