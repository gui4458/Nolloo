package com.green.Nolloo.item.controller;

import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.util.UploadUtil;
import com.green.Nolloo.wish.service.WishService;
import com.green.Nolloo.wish.vo.WishViewVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    //파티게시글 목록조회
    @GetMapping("/list")
    public String list(Model model, HttpSession session, ItemVO itemVO,@RequestParam(name="chkCode",required = false,defaultValue = "1")int chkCode){
        model.addAttribute("itemList",itemService.selectPartyList(itemVO));
        MemberVO loginInfo =(MemberVO)session.getAttribute("loginInfo");
        List<Integer> wishCodeList = new ArrayList<>();
        model.addAttribute("chkCode",chkCode);
        if (loginInfo != null){
            List<WishViewVO> wishList = wishService.selectWish(loginInfo.getMemberId());
            for (WishViewVO e : wishList){
                wishCodeList.add(e.getItemCode());
            }
            model.addAttribute("wishCodeList",wishCodeList);
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
                            , @RequestParam(name = "imgs") MultipartFile[] imgs){
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


        itemService.insertParty(itemVO);
        return "redirect:/item/list";
    }
    //itemDetail 조회
    @GetMapping("/itemDetailForm")
    public String boardDetailForm(ItemVO itemVO, Model model){
        model.addAttribute("item",itemService.selectPartyDetail(itemVO));
        return "content/item/item_detail";
    }
    //게시글 삭제
    @GetMapping("/deleteItem")
    public String deleteParty(ItemVO itemVO){
        itemService.deleteParty(itemVO);
        itemService.deleteImg(itemVO);
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
