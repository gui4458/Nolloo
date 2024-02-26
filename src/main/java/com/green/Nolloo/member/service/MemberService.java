package com.green.Nolloo.member.service;

import com.green.Nolloo.member.vo.MemberVO;

public interface MemberService {

    //회원가입
    void join(MemberVO memberVO);

    MemberVO login(MemberVO memberVO);


}
