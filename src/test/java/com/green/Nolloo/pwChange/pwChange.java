package com.green.Nolloo.pwChange;

import com.green.Nolloo.member.service.MemberService;
import com.green.Nolloo.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class pwChange {
    @Resource(name = "memberService")
    MemberService memberService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void 패스워드_변경(){
        //$2a$10$FbtNkb31MQ9aA1DjeKsMZuD8qfECAnS1ZFpkQ8xhMCerMXNY8AAZW
        //$2a$10$PAk6jq4SrO03q0ozjm3AheQDYqYa3KpSAtVmyGD/0psmXM1Hfk.Ve
        MemberVO memberVO = new MemberVO();
        String pw = "123";
        memberVO.setMemberId("b");
        MemberVO memberVO2 = memberService.memberInfo(memberVO);
        memberVO2.setMemberPw(encoder.encode(pw));
        memberService.revise(memberVO2);
    }
}
