package com.green.Nolloo.wish.service;

import com.green.Nolloo.wish.vo.WishViewVO;

import java.util.List;

public interface WishService {

    void insertWish();

    List<WishViewVO> selectWish();
}
