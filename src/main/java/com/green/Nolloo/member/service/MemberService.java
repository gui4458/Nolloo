package com.green.Nolloo.member.service;

import com.green.Nolloo.member.vo.MemberVO;

public interface MemberService {

    //회원가입
    void join(MemberVO memberVO);

    //로그인
    MemberVO login(MemberVO memberVO);

    //회원정보조회
    MemberVO memberInfo(MemberVO memberVO);


}
