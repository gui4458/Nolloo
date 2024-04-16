package com.green.Nolloo.member.controller;

import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ImgVO;
import com.green.Nolloo.member.service.MemberService;
import com.green.Nolloo.member.service.MemberServiceImpl;
import com.green.Nolloo.member.vo.MemberImageVO;
import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.reserve.service.ReserveServiceImpl;
import com.green.Nolloo.util.PathVariable;
import com.green.Nolloo.util.UploadUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/member")
public class MemberController {
@Resource(name="memberService")
    private MemberServiceImpl memberService;
@Resource(name="reserveService")
    private ReserveServiceImpl reserveService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private ItemService itemService;


    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO
            , @RequestParam(name = "img")MultipartFile img){
        memberVO.setMemberPw(encoder.encode(memberVO.getMemberPw()));

        memberVO.setMemberTel(memberVO.getMemberTel().replace(",","-"));
        MemberImageVO mainImg = UploadUtil.memberUploadFile(img);

        memberVO.setMemberImageVO(mainImg);

        memberService.join(memberVO);
        return "redirect:/item/list";
    }

    //로그인 화면으로 이동
    @GetMapping("/loginPage")
    public String loginPage(){
        return "/content/member/login";
    }

//    로그인 결과 화면
    @GetMapping("/loginResult")
    public String login(Authentication authentication,HttpSession session){
        if (authentication != null){
            User user = (User)authentication.getPrincipal();
            session.setAttribute("memberId",user.getUsername());
            session.setAttribute("reserveList",reserveService.selectReserve(user.getUsername()));
        }

    return "content/member/login_result";

    }

//    //로그아웃
//    @GetMapping("logout")
//    public String logout(HttpSession session){
//    session.removeAttribute("loginInfo");
//        return "redirect:/item/list";
//    }

    @GetMapping("/myPage")
    public String myPage(MemberVO memberVO, Model model, Authentication authentication){
        User user = (User)authentication.getPrincipal();

        //세션에 있는 로그인한 사람의 id를 가져온다.
        memberVO.setMemberId(user.getUsername());


        MemberVO memberInfo = memberService.memberInfo(memberVO);
        model.addAttribute("memberInfo",memberInfo);

        return "content/member/my_page";
    }

    @PostMapping("/myPage")
    public String myPage1(MemberVO memberVO,Authentication authentication,Model model){
        User user = (User)authentication.getPrincipal();
        memberVO.setMemberPw(encoder.encode(memberVO.getMemberPw()));
        memberVO.setMemberId(user.getUsername());
        memberService.revise(memberVO);

//        MemberVO memberInfo = memberService.memberInfo(memberVO);
//        model.addAttribute("memberInfo",memberInfo);
//        return "content/member/my_page";

        return "redirect:/member/myPage";

    }
    //회원탈퇴
    @ResponseBody
    @PostMapping("/deleteMember")
    public void deleteMember(MemberVO memberVO,Authentication authentication){

        User user = (User)authentication.getPrincipal();
        //개인 회원 탈퇴기능
        if (user.getUsername().equals(memberVO.getMemberId())) {
            String Path = PathVariable.PROFILE_UPLOAD_PATH;//profile경로
            File file = new File(Path + memberService.selectProfile(user.getUsername()));//경로+파일이미지(AttachedFileName)

            try {
                if(file.delete()){
                    System.out.println("파일을 삭제 하였습니다");
                }else {
                    System.out.println("파일 삭제에 실패하였습니다");
                }
            } catch (Exception e){
                e.printStackTrace();
            }


            memberVO.setMemberId(user.getUsername());
            memberService.updateMemberInactive(memberVO);
        }
        //관리자에 의한 강제탈퇴기능
        else{
            String Path = PathVariable.PROFILE_UPLOAD_PATH;//profile경로
            File file = new File(Path + memberService.selectProfile(memberVO.getMemberId()));//경로+파일이미지(AttachedFileName)

            try {
                if(file.delete()){
                    System.out.println("파일을 삭제 하였습니다");
                }else {
                    System.out.println("파일 삭제에 실패하였습니다");
                }
            } catch (Exception e){
                e.printStackTrace();
            }


            memberVO.setMemberId(memberVO.getMemberId());
            memberService.updateMemberInactive(memberVO);
        }
        

    }

    @GetMapping("/pw")
    public String getPw(){
        String pw = encoder.encode("1111");
        System.out.println("@@@@@@" + pw);
        return "";
    }


}
