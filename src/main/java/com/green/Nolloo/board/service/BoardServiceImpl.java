package com.green.Nolloo.board.service;

import com.green.Nolloo.board.vo.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // item 등록
    @Override
    public void insertBoard(BoardVO boardVO) {

        sqlSession.insert("boardMapper.insertBoard",boardVO);
    }
    //item 목록조회
    @Override
    public List<BoardVO> selectBoardList() {
        return sqlSession.selectList("boardMapper.selectBoardList");
    }
}
