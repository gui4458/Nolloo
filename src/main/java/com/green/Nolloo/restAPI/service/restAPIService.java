package com.green.Nolloo.restAPI.service;

import com.green.Nolloo.restAPI.vo.MapVO;

import java.util.List;

public interface restAPIService {
    //MapVO selectMapLatLnt(int itemCode);
    List<MapVO> selectAllMapLatLnt();
}
