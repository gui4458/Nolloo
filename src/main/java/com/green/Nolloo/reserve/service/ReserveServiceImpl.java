package com.green.Nolloo.reserve.service;


import com.green.Nolloo.reserve.vo.ReserveVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reserveService")
public class ReserveServiceImpl implements ReserveService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void reserve(ReserveVO reserveVO) {
        sqlSession.insert("reserveMapper.reserve", reserveVO);
    }
}
