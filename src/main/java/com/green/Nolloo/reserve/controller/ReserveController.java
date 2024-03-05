package com.green.Nolloo.reserve.controller;

import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.reserve.service.ReserveServiceImpl;
import com.green.Nolloo.reserve.vo.ReserveVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reserve")
public class ReserveController{
    @Resource(name="reserveService")
    private ReserveServiceImpl reserveService;

    @PostMapping("/partyReserve")
    public String reserve(ReserveVO reserveVO, HttpSession session){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        reserveVO.setMemberId(loginInfo.getMemberId());

        reserveService.insertReserve(reserveVO);
        return "redirect:/reserve/reserveList";
    }

    @GetMapping("/reserveList")
    public String reserveList(ReserveVO reserveVO, HttpSession session, Model model){
        MemberVO loginInfo= (MemberVO)session.getAttribute("loginInfo");
        reserveVO.setMemberId(loginInfo.getMemberId());

        model.addAttribute("reserveList",reserveService.selectReserve(reserveVO));
        return "content/member/reserve";
    }
}
