package com.green.Nolloo.wish.service;

import com.green.Nolloo.wish.vo.WishVO;
import com.green.Nolloo.wish.vo.WishViewVO;

import java.util.List;

public interface WishService {

    void insertWish(WishVO wishVO);

    List<WishViewVO> selectWish(String memberId);

    void wishDelete(WishVO wishVO);

    int check(WishVO wishVO);
}
