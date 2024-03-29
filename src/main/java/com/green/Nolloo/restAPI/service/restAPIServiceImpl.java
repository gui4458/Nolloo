package com.green.Nolloo.restAPI.service;

import com.green.Nolloo.restAPI.vo.AddressFormVO;
import com.green.Nolloo.restAPI.vo.MapVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Override
    public List<AddressFormVO> searchAddress(String[] qry) {
        return sqlSession.selectList("addressMapper.selectAddress",qry);
    }

    @Autowired
    private AddressRepository addressRepository;

    @Cacheable("addressCache")
    public List<AddressFormVO> processQuery(String inputParams) {
        return sqlSession.selectList("addressMapper.selectAddress",inputParams);
    }

}