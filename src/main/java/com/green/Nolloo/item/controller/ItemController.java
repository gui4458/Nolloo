package com.green.Nolloo.item.controller;

import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.member.service.MemberService;
import com.green.Nolloo.member.vo.MemberVO;

import com.green.Nolloo.reserve.service.ReserveService;
import com.green.Nolloo.reserve.vo.ReserveVO;

import com.green.Nolloo.restAPI.service.KakaoApiService;
import com.green.Nolloo.restAPI.vo.AddressVO;
import com.green.Nolloo.restAPI.vo.MapVO;

import com.green.Nolloo.search.vo.SearchVO;
import com.green.Nolloo.util.UploadUtil;
import com.green.Nolloo.wish.service.WishService;
import com.green.Nolloo.wish.vo.WishViewVO;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource(name = "itemService")
    private ItemService itemService;
    @Resource(name = "wishService")
    private WishService wishService;


    @Resource(name="reserveService")
    private ReserveService reserveService;


    @Resource(name = "memberService")
    private MemberService memberService;

    @Autowired
    private KakaoApiService kakaoApiService;

    //파티게시글 목록조회
    @RequestMapping("/list")
    public String list(Model model, Authentication authentication, ItemVO itemVO
                        , @RequestParam(name="chkCode",required = false,defaultValue = "1")int chkCode
                        ,SearchVO searchVO){
        System.out.println(itemVO);
        System.out.println(searchVO);
        model.addAttribute("itemList",itemService.selectPartyList(searchVO));
        List<Integer> wishCodeList = new ArrayList<>();
        model.addAttribute("chkCode",chkCode);




        if (authentication != null){
            User user = (User)authentication.getPrincipal();

            List<WishViewVO> wishList = wishService.selectWish(user.getUsername());
            for (WishViewVO e : wishList){
                wishCodeList.add(e.getItemCode());
            }
            model.addAttribute("wishCodeList",wishCodeList);
            model.addAttribute("memberImg",memberService.selectProfile(user.getUsername()));
        }

        return "content/main";
    }
    //게시글 등록
    @GetMapping("/itemAddForm")
    public String boardAddForm(){


        return"content/item/item_add_form";
    }
    @PostMapping("/itemAdd")
    public String boardAdd(ItemVO itemVO
                            , @RequestParam(name = "img") MultipartFile img
                            , @RequestParam(name = "imgs") MultipartFile[] imgs, @RequestParam(name="itemPlace") String addr){
        //메인이미지 업로드
        ImgVO mainImg = UploadUtil.uploadFile(img);
        //상세이미지 업로드
        List<ImgVO> imgList =UploadUtil.multiUploadFile(imgs);

        int itemCode = itemService.selectNextItemCode();

        itemVO.setItemCode(itemCode);

        mainImg.setItemCode(itemCode);

        for (ImgVO subImg : imgList){
            subImg.setItemCode(itemCode);
        }
        imgList.add(mainImg);
        itemVO.setImgList(imgList);

        MapVO mapVO = kakaoApiService.getGeoFromAddress(addr);

        System.out.println(mapVO);

        double lat = mapVO.getItemX();
        double lng = mapVO.getItemY();

        System.out.println("Lat:" + lat +" Lng:"+ lng);

        itemVO.setItemX(lat);
        itemVO.setItemY(lng);

        itemService.insertParty(itemVO);
        return "redirect:/item/list";
    }

    //itemDetail 조회
    @GetMapping("/itemDetailForm")
    public String boardDetailForm(ItemVO itemVO, ReserveVO reserveVO, Model model, Authentication authentication){

        itemService.itemListUpdateCnt(itemVO);
        Model item = model.addAttribute("item",itemService.selectPartyDetail(itemVO));

        if (authentication != null){
            User user = (User)authentication.getPrincipal();
            System.out.println(user.getUsername());
            reserveVO.setMemberId(user.getUsername());
            model.addAttribute("reserveCnt",reserveService.reserveDone(reserveVO));
        }

        return "content/item/item_detail";
    }
    //게시글 삭제
    @GetMapping("/deleteItem")
    public String deleteParty(ItemVO itemVO){
        itemService.deleteParty(itemVO);
        return "redirect:/item/list";
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, ItemVO itemVO){

        model.addAttribute("item",itemVO);
        return "content/item/item_update_form";
    }

    @PostMapping("/updateParty")
    public String updateParty(ItemVO itemVO){
        itemService.updateParty(itemVO);

        return "redirect:/item/itemDetailForm?itemCode="+itemVO.getItemCode();
    }


}
