package com.green.Nolloo.reserve.controller;

import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.reserve.service.ReserveServiceImpl;
import com.green.Nolloo.reserve.vo.ReserveVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reserve")
public class ReserveController{
    @Resource(name="reserveService")
    private ReserveServiceImpl reserveService;
    @Resource(name = "itemService")
    private ItemService itemService;

    @ResponseBody
    @PostMapping("/partyReserve")
    public void reserve(HttpSession session
                        , @RequestBody ReserveVO reserveVO, ItemVO itemVO){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        reserveVO.setMemberId(loginInfo.getMemberId());

        itemVO.setItemCode(reserveVO.getItemCode());
        itemVO = itemService.selectPartyDetail(itemVO);
        System.out.println(reserveVO);
        System.out.println(itemVO);
        reserveService.insertReserve(reserveVO);
        return ;
    }

    @GetMapping("/reserveList")
    public String reserveList(ReserveVO reserveVO, HttpSession session, Model model){
        MemberVO loginInfo= (MemberVO)session.getAttribute("loginInfo");
        reserveVO.setMemberId(loginInfo.getMemberId());

        model.addAttribute("reserveList",reserveService.selectReserve(reserveVO));
        return "content/member/reserve";
    }
}
