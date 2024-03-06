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

import java.util.List;

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
                        , @RequestBody ReserveVO reserveVO){
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        reserveVO.setMemberId(loginInfo.getMemberId());
        reserveService.insertReserve(reserveVO);

    }

    @GetMapping("/reserveList")
    public String reserveList(ReserveVO reserveVO, HttpSession session, Model model){
        MemberVO loginInfo= (MemberVO)session.getAttribute("loginInfo");
        reserveVO.setMemberId(loginInfo.getMemberId());
        List<ReserveVO> reserveList = reserveService.selectReserve(reserveVO);
        System.out.println(reserveList);
        model.addAttribute("reserveList",reserveList);


//        List<ReserveVO> reserveDetail = reserveService.selectDetail(reserveVO);
//        model.addAttribute("reserveDetail",reserveDetail);
        return "content/member/reserve";
    }
    @ResponseBody
    @PostMapping("/reserveDetail")
    public ItemVO reserveDetail(@RequestBody ReserveVO reserveVO){

        ItemVO reserveDetail = reserveService.selectDetail(reserveVO);

        return reserveDetail;

    }



}
