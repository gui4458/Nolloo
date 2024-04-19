package com.green.Nolloo.admin.service;

import com.green.Nolloo.admin.vo.NoticeImgVO;
import com.green.Nolloo.admin.vo.NoticeVO;
import com.green.Nolloo.admin.vo.ReplyVO;
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


    int selectListAdminStatistics(int cateCode);

    //공지사항 삭제
    void deleteNotice(NoticeVO noticeVO);

    //공지사항 수정
    void updateNotice(NoticeVO noticeVO);

    //공지사항 댓글 추가
    void insertReply(ReplyVO replyVO);

}
