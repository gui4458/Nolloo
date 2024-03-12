package com.green.Nolloo.reserve.controller;

import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.reserve.service.ReserveService;
import com.green.Nolloo.reserve.service.ReserveServiceImpl;
import com.green.Nolloo.reserve.vo.ReserveVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reserve")
public class ReserveController{
    @Resource(name="reserveService")
    private ReserveServiceImpl reserveService;
    @Resource(name = "itemService")
    private ItemService itemService;


    //예약하기
    @ResponseBody
    @PostMapping("/partyReserve")
    public void reserve(Authentication authentication
                        , @RequestBody ReserveVO reserveVO){
        //reserveService.reserveDone(reserveVO);

        User user = (User)authentication.getPrincipal();
        reserveVO.setMemberId(user.getUsername());
        reserveService.insertReserve(reserveVO);

    }

    //예약 조회
    @GetMapping("/reserveList")
    public String reserveList(ReserveVO reserveVO, Authentication authentication, Model model){
        User user = (User)authentication.getPrincipal();
        reserveVO.setMemberId(user.getUsername());
        List<ReserveVO> reserveList = reserveService.selectReserve(reserveVO);
        System.out.println(reserveList);
        model.addAttribute("reserveList",reserveList);


//        List<ReserveVO> reserveDetail = reserveService.selectDetail(reserveVO);
//        model.addAttribute("reserveDetail",reserveDetail);
        return "content/member/reserve";
    }

    //예약 상세 내역 조회
    @ResponseBody
    @PostMapping("/reserveDetail")
    public ItemVO reserveDetail(@RequestBody ReserveVO reserveVO){

        ItemVO reserveDetail = reserveService.selectDetail(reserveVO);

        return reserveDetail;

    }

    @GetMapping("/reserveDelete")
    public String reserveDelete(@RequestParam(name = "reserveCodeList") ArrayList<Integer> reserveCodeList){
        System.out.println(reserveCodeList);
        //return "";
        return "redirect:/reserve/reserveList";
    }



}
