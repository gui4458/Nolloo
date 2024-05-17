package com.green.Nolloo.reserve.service;


import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.reserve.vo.ReserveVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertReserve(ReserveVO reserveVO) {
        sqlSession.insert("reserveMapper.insertReserve", reserveVO);
        sqlSession.update("itemMapper.updatePeopleCnt", reserveVO);
    }

    @Override
    public List<ReserveVO> chartReserveList() {
        return sqlSession.selectList("reserveMapper.chartReserveList");
    }

    @Override
    public List<ReserveVO> selectReserve(ReserveVO reserveVO) {
        return sqlSession.selectList("reserveMapper.selectReserve",reserveVO);
    }

    @Override
    public ItemVO selectDetail(ReserveVO reserveVO) {
        return sqlSession.selectOne("reserveMapper.selectDetail",reserveVO);
    }

    @Override
    public int reserveDone(ReserveVO reserveVO) {
        return sqlSession.selectOne("reserveMapper.reserveDone",reserveVO);
    }

    @Override
    public void deleteReserve(ReserveVO reserveVO) {
        sqlSession.delete("reserveMapper.deleteReserve", reserveVO);
        sqlSession.update("itemMapper.minusPeopleCnt", reserveVO);

    }


}
