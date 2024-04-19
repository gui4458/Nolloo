package com.green.Nolloo.admin.service;

import com.green.Nolloo.admin.vo.NoticeImgVO;
import com.green.Nolloo.admin.vo.NoticeVO;
import com.green.Nolloo.admin.vo.ReplyVO;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.member.vo.MemberVO;
import com.green.Nolloo.reserve.vo.ReserveVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //축제 데이터 csv를 통한 등록 기능
    @Override
    public void insertCsv(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertFestival", itemVO);
    }

    @Override
    public List<MemberVO> memberInfo() {
        return sqlSession.selectList("memberMapper.memberInfo");
    }

    @Override
    public void insertNotice(NoticeVO noticeVO) {
     sqlSession.insert("adminMapper.insertNotice",noticeVO);
     if(noticeVO.getNoticeImgList().size() != 0){
         sqlSession.insert("adminMapper.insertNoticeImg", noticeVO);
     }


    }

    @Override
    public int selectNextNoticeCode() {
        return sqlSession.selectOne("adminMapper.selectNextNoticeCode");
    }

    @Override
    public List<NoticeVO> selectNotice(int noticeCode) {
        return sqlSession.selectList("adminMapper.selectNotice",noticeCode);
    }

    @Override
    public void upReadCnt(int noticeCode) {
        sqlSession.update("adminMapper.upReadCnt",noticeCode);
    }

    @Override
    public int selectListAdminStatistics(int cateCode) {
        return sqlSession.selectOne("adminMapper.selectListAdminStatistics", cateCode);
    }

    public void deleteNotice(NoticeVO noticeVO) {
        sqlSession.delete("adminMapper.deleteNotice",noticeVO);
    }

    @Override
    public void updateNotice(NoticeVO noticeVO) {
        sqlSession.update("adminMapper.updateNotice",noticeVO);
    }

    @Override
    public void insertReply(ReplyVO replyVO) {
        sqlSession.insert("adminMapper.insertReply",replyVO);
    }

}
