package com.green.Nolloo.restAPI.service;

import com.green.Nolloo.restAPI.vo.AddressFormVO;
import com.green.Nolloo.restAPI.vo.MapVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface restAPIService {
    //MapVO selectMapLatLnt(int itemCode);
    List<MapVO> selectAllMapLatLnt();

    List<AddressFormVO> searchAddress(String[] qry);

}

