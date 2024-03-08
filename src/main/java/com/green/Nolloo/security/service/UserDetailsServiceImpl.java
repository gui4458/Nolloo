package com.green.Nolloo.security.service;

import com.green.Nolloo.member.service.MemberService;
import com.green.Nolloo.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name="memberService")
    private MemberService memberService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO member = memberService.login(username);
        UserDetails userInfo = User.builder()
                                .username(member.getMemberId())
                .password(member.getMemberPw())
                .roles(member.getMemberRoll())
                .build();
        return userInfo;
    }
}
