package com.green.Nolloo.member.service;

import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.member.vo.MemberVO;

public interface MemberService {

    //회원가입
    void join(MemberVO memberVO);

    //로그인
    MemberVO login(MemberVO memberVO);

    //회원정보조회
    MemberVO memberInfo(MemberVO memberVO);

    //회원정보수정
    void revise(MemberVO memberVO);

    //회원탈퇴
    void deleteMember(MemberVO memberVO);

}
