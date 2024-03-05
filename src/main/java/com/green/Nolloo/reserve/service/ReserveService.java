package com.green.Nolloo.reserve.service;

import com.green.Nolloo.reserve.vo.ReserveVO;

import java.util.List;

public interface ReserveService {
    void insertReserve(ReserveVO reserveVO);

    List<ReserveVO> selectReserve(ReserveVO reserveVO);
}
