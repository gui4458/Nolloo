package com.green.Nolloo.reserve.service;

import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.reserve.vo.ReserveVO;

import java.util.List;

public interface ReserveService {
    void insertReserve(ReserveVO reserveVO);


    List<ReserveVO> selectReserve(ReserveVO reserveVO);


    ItemVO selectDetail(ReserveVO reserveVO);


    int reserveDone(ReserveVO reserveVO);

    void deleteReserve(ReserveVO reserveVO);
}
