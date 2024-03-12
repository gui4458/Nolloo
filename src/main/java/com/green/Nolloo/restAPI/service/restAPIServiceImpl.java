package com.green.Nolloo.restAPI.service;

import com.green.Nolloo.restAPI.vo.MapVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("restAPIService")
public class restAPIServiceImpl implements restAPIService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<MapVO> selectAllMapLatLnt() {
        return sqlSession.selectList("mapMapper.selectAllMapLatLng");
    }

}
