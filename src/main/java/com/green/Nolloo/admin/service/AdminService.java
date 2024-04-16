package com.green.Nolloo.admin.service;

import com.green.Nolloo.admin.vo.NoticeVO;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.member.vo.MemberVO;

import java.util.List;

public interface AdminService {

    //축제 데이터 csv를 통한 등록 기능
    void insertCsv(ItemVO itemVO);
    // 회원 목록 조회
    List<MemberVO> memberInfo();

    //공지사항 글작성
    void insertNotice(NoticeVO noticeVO);

    int selectNextNoticeCode();



}
