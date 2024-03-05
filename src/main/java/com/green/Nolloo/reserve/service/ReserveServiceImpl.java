package com.green.Nolloo.reserve.service;


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
    }

    @Override
    public List<ReserveVO> selectReserve(ReserveVO reserveVO) {
        return sqlSession.selectList("reserveMapper.selectReserve",reserveVO);
    }


}
