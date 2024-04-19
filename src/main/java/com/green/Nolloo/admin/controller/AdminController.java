package com.green.Nolloo.admin.controller;

import com.green.Nolloo.admin.service.AdminService;
import com.green.Nolloo.admin.vo.NoticeImgVO;
import com.green.Nolloo.admin.vo.NoticeVO;
import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.item.vo.PageVO;
import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.reserve.service.ReserveService;
import com.green.Nolloo.reserve.vo.ReserveVO;
import com.green.Nolloo.search.vo.SearchVO;
import com.green.Nolloo.util.PathVariable;
import com.green.Nolloo.util.UploadUtil;
import com.green.Nolloo.wish.vo.WishViewVO;
import com.opencsv.CSVReader;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "itemService")
    private ItemService itemService;
    @Resource(name = "reserveService")
    private ReserveService reserveService;

    //csv 등록 페이지로 이동
    @GetMapping("/readCsv")
    public String readCsv() {
        return "content/admin/insert_csv";
    }

    //csv 파일 등록하기
    @ResponseBody
    @PostMapping("/insertCsv")
    public void insertCsv() {
        //엑셀 파일에서 하나의 행 데이터를 가져와서 저장할 변수
        String[] rowData = null;

        try{
            //utf-8 형태로 csv 파일 파싱
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(PathVariable.FESTIVAL_CSV_PATH), StandardCharsets.UTF_8));

            // 컬럼명은 저장되지 않도록 한 줄 읽기
            csvReader.readNext();
            do{
                //엑셀 파일의 한 줄씩 읽어서 rowData에 저장
                //만약 가져온 데이터가 없으면 rowData에[는 null이 들어감
                rowData = csvReader.readNext();

                //들어온 데이터가 있으면..
                if(rowData != null){
                    System.out.println(Arrays.toString(rowData));
                    ItemVO vo = new ItemVO();
                    vo.setCsvData(rowData);

                    adminService.insertCsv(vo);
                }
            }while(rowData != null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/adminNotice")
    public String adminNotice(Model model){
        //공지사항 조회(리스트, 상세)
        int noticeCode = 0;
        List<NoticeVO> noticeList= adminService.selectNotice(noticeCode);
        model.addAttribute("noticeList",noticeList);

        return "content/admin/admin_notice";
    }
    @GetMapping("/adminMemberManage")
    public String adminMemberManage(Model model){
        List<MemberVO> memberList = adminService.memberInfo();
        model.addAttribute("memberList",memberList);
        return "content/admin/admin_member_manage";
    }
    @GetMapping("/adminBoardManage")
    public String adminBoardManage(PageVO pageVO, Model model){



        List<ItemVO> itemList = itemService.selectPartyList(pageVO);
        model.addAttribute("itemList",itemList);
        return "content/admin/admin_board_manage";
    }


    @RequestMapping("/adminBuyList")
    public String adminBuyList(HttpSession session,Model model, ReserveVO reserveVO){

        List<ReserveVO> reserveList = reserveService.selectReserve(reserveVO);
        model.addAttribute("reserveList",reserveList);
        System.out.println(reserveList);
        return "content/admin/admin_buy_list";
    }
    @GetMapping("/adminJoinStatistics")
    public String adminJoinStatistics(){

        return "content/admin/admin_join_statistics";
    }
   @GetMapping("/noticeForm")
   public String noticeForm(){
        return "content/admin/notice";
   }

   @PostMapping("/notice")
    public String notice(NoticeVO noticeVO, @RequestParam(name="noticeImgs")MultipartFile[] noticeImgs){
        List<NoticeImgVO> imgList= UploadUtil.multiUploadNoticeFile(noticeImgs);

        int noticeCode = adminService.selectNextNoticeCode();
       for (NoticeImgVO img : imgList){
           img.setNoticeCode(noticeCode);

       }
        noticeVO.setNoticeCode(noticeCode);
        noticeVO.setNoticeImgList(imgList);

       System.out.println(noticeVO);
           adminService.insertNotice(noticeVO);

        return "content/admin/admin_notice";
   }

    @ResponseBody
    @PostMapping("/cateSelect")
    public List<ItemVO> cateSelect(@RequestBody PageVO pageVO, Authentication authentication, SearchVO searchVO, HttpSession session){
        System.out.println(pageVO);
        return itemService.selectPartyList(pageVO);
    }
   @GetMapping("/noticeDetail")
    public String noticeDetail(@RequestParam(name="noticeCode")int noticeCode,Model model){

        //공지사항 조회수
        adminService.upReadCnt(noticeCode);

        List<NoticeVO> noticeList = adminService.selectNotice(noticeCode);

        model.addAttribute("noticeDetail", noticeList.get(0));

        return "content/admin/notice_detail";
   }


}
