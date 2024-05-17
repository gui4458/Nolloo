package com.green.Nolloo.restAPI.controller.kakao;

import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ItemVO;
import com.green.Nolloo.restAPI.service.KakaoApiService;
import com.green.Nolloo.restAPI.service.MyService;
import com.green.Nolloo.restAPI.service.restAPIService;
import com.green.Nolloo.restAPI.vo.AddressFormVO;
import com.green.Nolloo.restAPI.vo.AddressVO;
import com.green.Nolloo.restAPI.vo.MapVO;
import com.green.Nolloo.restAPI.vo.MyEntity;
import com.green.Nolloo.search.vo.SearchVO;
import com.green.Nolloo.wish.service.WishService;
import com.green.Nolloo.wish.vo.WishVO;
import com.green.Nolloo.wish.vo.WishViewVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class Address {


    @Autowired
    private KakaoApiService kakaoApiService;

    @Autowired
    private restAPIService restAPIService;

    @Autowired
    private ItemService itemService;
    @Autowired
    private WishService wishService;

    @GetMapping("/address")
    public String index() {
        return "/content/restAPI/address";
    }

    @GetMapping("/getAddress")
    @ResponseBody
    public AddressVO getAddress(@RequestParam(name="latitude") double latitude, @RequestParam(name="longitude") double longitude) {
        return kakaoApiService.getAddressFromGeolocation(latitude, longitude);
    }

    @GetMapping("/location")
    public String loc() {
        return "/content/restAPI/location";
    }

    @GetMapping("/getLocation")
    @ResponseBody
    public MapVO getAddress(@RequestParam(name="query") String addr) {
        return kakaoApiService.getGeoFromAddress(addr);
    }




    @GetMapping("/addressForm")
    public String addressForm(Model model){

        return "/content/restAPI/address_form";
    }

    @ResponseBody
    @PostMapping("/addressResult")
    public List<AddressFormVO> addressResult(@RequestParam(name="query") String query) {
        //문자열을  스페이스 기준으로 분리 // "aa bb"   "aaa"
        String[] data =  query.split(" ");

        return  restAPIService.searchAddress(data);
    }

    @Autowired
    private MyService myService;

    @ResponseBody
    @GetMapping("/entities")
    public List<MyEntity> getAllEntities() {
        return myService.getAllEntities();
    }


    @GetMapping("/search")
    public String searchEntities() {
        return "/content/restAPI/search";
    }

//    @ResponseBody
//    @PostMapping("/search")
//    public List<MyEntity> searchEntities(@RequestParam(name="sido") String sido) {
//        return myService.searchEntities(sido);
//    }
    @PostMapping("/search")
    public String searchResult(SearchVO searchVO, Model model, HttpSession session) {
        List<ItemVO> searchItemList = itemService.titleSearch(searchVO);
        List<ItemVO> contentSearch = itemService.contentSearch(searchVO);
        if (!searchItemList.isEmpty()){
            model.addAttribute("searchItemList",searchItemList);
        }
        if (!contentSearch.isEmpty()){
            model.addAttribute("contentSearch",contentSearch);
        }
        model.addAttribute("searchText",searchVO.getSearchText());

        List<WishViewVO> wishList = wishService.selectWish((String) session.getAttribute("memberId"));
        model.addAttribute("wishList",wishList);
        return "/content/restAPI/search_result";
    }



}
