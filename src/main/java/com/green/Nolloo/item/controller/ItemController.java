package com.green.Nolloo.item.controller;

import com.green.Nolloo.admin.service.AdminService;
import com.green.Nolloo.admin.vo.NoticeVO;
import com.green.Nolloo.chat.service.ChatService;
import com.green.Nolloo.chat.vo.ChatVO;
import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.CateVO;
import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.item.vo.PageVO;
import com.green.Nolloo.member.service.MemberService;
import com.green.Nolloo.member.vo.MemberImageVO;
import com.green.Nolloo.member.vo.MemberVO;

import com.green.Nolloo.reserve.service.ReserveService;
import com.green.Nolloo.reserve.vo.ReserveVO;

import com.green.Nolloo.restAPI.service.KakaoApiService;
import com.green.Nolloo.restAPI.vo.AddressVO;
import com.green.Nolloo.restAPI.vo.MapVO;

import com.green.Nolloo.search.vo.SearchVO;
import com.green.Nolloo.util.PathVariable;
import com.green.Nolloo.util.UploadUtil;
import com.green.Nolloo.wish.service.WishService;
import com.green.Nolloo.wish.vo.WishVO;
import com.green.Nolloo.wish.vo.WishViewVO;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.standard.processor.AbstractStandardDoubleAttributeModifierTagProcessor;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource(name = "chatService")
    private ChatService chatService;

    @Resource(name = "adminService")
    private AdminService adminService;

    @Autowired
    private KakaoApiService kakaoApiService;

    //파티게시글 목록조회
    @GetMapping("/list")
    public String list(Model model, Authentication authentication, ItemVO itemVO
                        , @RequestParam(name="cateCode",required = false,defaultValue = "0")int cateCode
                        , SearchVO searchVO, HttpSession session, NoticeVO noticeVO){
//        searchVO.setCateCode(cateCode);
//        List<ItemVO> itemList = itemService.selectPartyList(searchVO);
//        model.addAttribute("itemList",itemList);
        //        전체 데이터 수
//        int totalDataCnt = itemService.itemAllCnt(searchVO.getCateCode());
//        searchVO.setTotalDataCnt(totalDataCnt);
//        System.out.println("totalDataCnt = " + totalDataCnt);
//        List<Integer> wishCodeList = new ArrayList<>();

        model.addAttribute("cateCode",cateCode);
        List<ItemVO> recommendList ;
        Integer reserveCnt = (Integer) session.getAttribute("reserveCnt");

        if (reserveCnt == null || reserveCnt == 0){
            recommendList = itemService.searchByReadCnt("");
        }else {
            recommendList = itemService.searchByReadCnt((String) session.getAttribute("memberId"));

        }
        session.setAttribute("recommendList",recommendList);

        List<CateVO> cateList = itemService.selectCate();
        session.setAttribute("cateList",cateList);
//        if (authentication != null){
//            User user = (User)authentication.getPrincipal();
//
//            List<WishViewVO> wishList = wishService.selectWish(user.getUsername());
//            for (WishViewVO e : wishList){
//                wishCodeList.add(e.getItemCode());
//            }
//            model.addAttribute("wishCodeList",wishCodeList);
//        session.setAttribute("memberImage",memberService.selectProfile(user.getUsername()));
//        session.setAttribute("memberId",user.getUsername());
//            System.out.println("로그인함" + wishCodeList);
//
//        }

        model.addAttribute("noticeList",adminService.selectNotice(noticeVO.getNoticeCode()));



        return "content/main";
    }
    @ResponseBody
    @PostMapping("/list")
    public Map<String,Object> list(@RequestBody PageVO pageVO,Authentication authentication,SearchVO searchVO,HttpSession session){
        List<ItemVO> itemList = itemService.selectPartyList(pageVO);

        Map<String,Object> data = new HashMap<String, Object>();
        data.put("itemList",itemList);
        List<Integer> wishCodeList = new ArrayList<>();
        int totalDataCnt = itemService.itemAllCnt(searchVO.getCateCode());
        searchVO.setTotalDataCnt(totalDataCnt);
        data.put("dataCnt",totalDataCnt);
        if (authentication != null){
            User user = (User)authentication.getPrincipal();

            List<WishViewVO> wishList = wishService.selectWish(user.getUsername());
            for (WishViewVO e : wishList){
                wishCodeList.add(e.getItemCode());
            }
        }

        data.put("wishCodeList", wishCodeList);
        session.setAttribute("data",data);
        return data;

    }
    //게시글 등록
    @GetMapping("/itemAddForm")
    public String boardAddForm(){


        return"content/item/item_add_form";
    }
    @PostMapping("/itemAdd")
    public String boardAdd(ItemVO itemVO
                            , @RequestParam(name = "img") MultipartFile img
                            , @RequestParam(name = "imgs") MultipartFile[] imgs
                            , @RequestParam(name="itemPlace") String addr
                            , Authentication authentication, ChatVO chatVO){

        User user = (User)authentication.getPrincipal();
        itemVO.setMemberId(user.getUsername());
        //메인이미지 업로드
        ImgVO mainImg = UploadUtil.uploadFile(img);
        //상세이미지 업로드
        List<ImgVO> imgList =UploadUtil.multiUploadFile(imgs);

        int itemCode = itemService.selectNextItemCode();
        chatVO.setItemCode(itemCode);
        chatVO.setRoomName(itemVO.getItemTitle());
        chatVO.setFounder(user.getUsername());
        itemVO.setChatVO(chatVO);
        itemVO.setItemCode(itemCode);

        mainImg.setItemCode(itemCode);

        for (ImgVO subImg : imgList){
            subImg.setItemCode(itemCode);
        }
        imgList.add(mainImg);
        itemVO.setImgList(imgList);

        MapVO mapVO = kakaoApiService.getGeoFromAddress(addr);



        double lat = mapVO.getItemX();
        double lng = mapVO.getItemY();

        System.out.println("Lat:" + lat +" Lng:"+ lng);

        AddressVO addressVO = kakaoApiService.getAddressFromGeolocation(lat,lng);


        itemVO.setRegion1(addressVO.getRegion1depthName());
        itemVO.setRegion2(addressVO.getRegion2depthName());
        itemVO.setRegion3(addressVO.getRegion3depthName());


        itemVO.setItemX(lat);
        itemVO.setItemY(lng);

        itemService.insertParty(itemVO);
        return "redirect:/item/list";
    }

    //itemDetail 조회
    @ResponseBody
    @PostMapping("/itemDetailForm")
    public Map<String,Object> boardDetailForm(ItemVO itemVO, Authentication authentication, HttpSession session,ReserveVO reserveVO){

        itemService.itemListUpdateCnt(itemVO);



        Map<String,Object> data = (Map<String, Object>)session.getAttribute("data");
        data.put("item",itemService.selectPartyDetail(itemVO));
        data.put("wishCnt",itemService.wishCount(itemVO.getItemCode()));
        System.out.println(data.get("item"));
        reserveVO.setMemberId((String) session.getAttribute("memberId"));
        data.put("reserveCnt",reserveService.reserveDone(reserveVO));





       // model.addAttribute("chkCode",chkCode);

//        if (authentication != null){
//            User user = (User)authentication.getPrincipal();
//            reserveVO.setMemberId(user.getUsername());
//            model.addAttribute("reserveCnt",reserveService.reserveDone(reserveVO));
//            List<ReserveVO> reserveList = reserveService.selectReserve(user.getUsername());
//            model.addAttribute("reserveList",reserveList);
//        }

        return data;
    }
    //게시글 삭제
    @GetMapping("/deleteItem")
    public String deleteParty(ItemVO itemVO) {
        List<String> attachedFileNameList = itemService.selectItemImage(itemVO);//attachedFileName만 조회+where절로 itemCode 넣어주기


        try {   //"c:\\ss\\aaa.jpg"
            for(String attachedFileName : attachedFileNameList){//attachedFileName여러개를 하나씩 출력
                String Path = PathVariable.ITEM_UPLOAD_PATH;//itemSolo경로
                File file = new File(Path + attachedFileName);//경로+파일이미지(AttachedFileName)

                if(file.delete()){//delete로 폴더에 파일 삭제
                    System.out.println("파일을 삭제 하였습니다");
                }else {
                    System.out.println("파일 삭제에 실패하였습니다");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        itemService.deleteParty(itemVO);//쿼리 item정보+img삭제
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




    //나의 파티 조회
    @GetMapping("/myParty")
    public String myParty(ItemVO itemVO, Model model,HttpSession session){

        String memberId = (String)session.getAttribute("memberId");
        itemVO.setMemberId(memberId);
        List<ItemVO> myPartyList = itemService.selectMyParty(itemVO);
        model.addAttribute("myPartyList",myPartyList);
        return "content/member/my_party";
    }

    @ResponseBody
    @PostMapping("/selectItemDetail")
    public ItemVO selectItemDetail(@RequestParam(name="itemCode") int itemCode,ItemVO itemVO){
        ItemVO detail=itemService.selectItemDetail(itemCode);



        return detail;
    }

    @PostMapping("/updateItem")
    public String updateItem(ItemVO itemVO,
             @RequestParam(name = "subfileName") MultipartFile[] subfileName){
        //----- 첨부파일 새로 등록 -----//
        //서브 이미지 첨부 및 첨부 데이터 받기
        List<ImgVO> imgList = UploadUtil.multiUploadFile(subfileName);

        //합체된 첨부 정보를 itemVO에 넣어 줌.
        itemVO.setImgList(imgList);

        //수정하려는 내용 + 첨부파일 정보가 다 들어있는 itenmVO를 쿼리 실행 시 매개변수로 전달!
        itemService.updateItemDetail(itemVO);

        return "redirect:/item/myParty";
    }

    //상품 나의파티 상세보기 페이지에서 이미지 삭제버튼 클릭 시 실행
    @ResponseBody
    @PostMapping("/deleteImg")
    public void deleteImg(ImgVO imgVO){

        //첨부파일명 조회
        String attachedFileName = itemService.findAttachedFileNameByImgCode(imgVO);

        //선택한 이미지 디비에서 삭제
        itemService.deleteItemImg(imgVO);

        //첨부파일 삭제
        UploadUtil.deleteUploadFile(PathVariable.ITEM_UPLOAD_PATH + attachedFileName);
    }

    //상품 정보 수정에서 메인 이미지를 변경하는 메서드
    @ResponseBody
    @PostMapping("/changeMainImg")
    public String changeMainImg(ImgVO imgVO, @RequestParam(name = "file", required = false) MultipartFile file){

        //----- 이미지 삭제 및 재등록 -----//

        //----- 원래 이미지 삭제 -----//
        //첨부파일명 조회
        String attachedFileName = itemService.findAttachedFileNameByImgCode(imgVO);

        //선택한 이미지 디비에서 삭제
        itemService.deleteItemImg(imgVO);

        //첨부파일 삭제
        UploadUtil.deleteUploadFile(PathVariable.ITEM_UPLOAD_PATH + attachedFileName);

        //----- 새로운 이미지 등록 -----//
        ImgVO vo = UploadUtil.uploadFile(file);


        ItemVO itemVO = new ItemVO();
        itemVO.setItemCode(imgVO.getItemCode());
        List<ImgVO> imgList = new ArrayList<>();
        imgList.add(vo);
        itemVO.setImgList(imgList);
        itemService.insertMainImg(itemVO);

        return file.getOriginalFilename();
    }

}
