package com.green.Nolloo.admin.service;

import com.green.Nolloo.item.vo.ItemVO;

public interface AdminService {

    //축제 데이터 csv를 통한 등록 기능
    void insertCsv(ItemVO itemVO);
}
