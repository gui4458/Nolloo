package com.green.Nolloo.restAPI.controller.kakao;

import com.green.Nolloo.restAPI.service.KakaoApiService;
import com.green.Nolloo.restAPI.service.MyService;
import com.green.Nolloo.restAPI.service.restAPIService;
import com.green.Nolloo.restAPI.vo.AddressFormVO;
import com.green.Nolloo.restAPI.vo.AddressVO;
import com.green.Nolloo.restAPI.vo.MapVO;
import com.green.Nolloo.restAPI.vo.MyEntity;
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
        System.out.println(Arrays.toString(data));
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

    @ResponseBody
    @PostMapping("/search")
    public List<MyEntity> searchEntities(@RequestParam(name="sido") String sido) {
        return myService.searchEntities(sido);
    }

}
