package com.green.Nolloo.admin.service;


import com.green.Nolloo.admin.vo.*;

import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.reserve.vo.ReserveVO;

import java.util.List;

public interface AdminService {

    //축제 데이터 csv를 통한 등록 기능
    void insertCsv(ItemVO itemVO);
    // 회원 목록 조회
    List<MemberVO> memberInfo();

    //공지사항 글작성
    void insertNotice(NoticeVO noticeVO);


    int selectNextNoticeCode();

    //공지사항 조회
    List<NoticeVO> selectNotice(int noticeCode);

    //공지사항 조회수 증가
    void upReadCnt(int noticeCode);


    //공지사항 삭제
    void deleteNotice(NoticeVO noticeVO);

    //공지사항 수정
    void updateNotice(NoticeVO noticeVO);

    //공지사항 댓글 추가
    void insertReply(ReplyVO replyVO);

    int selectBoardCnt(AdminPageVO adminPageVO);

    //공지사항 댓글 조회
    List<ReplyVO> selectReply(ReplyVO replyVO);

    // 도넛 차트 참여인원 조회
    List<ReserveVO> selectListAdminStatistics();

    //바차트 월별 상품수 조회
    List<ItemCntPerMonth> selectDate();

    //글 관리 페이지 상품 조회
    List<ItemVO> adminBoardList(AdminPageVO adminPageVO);
}
