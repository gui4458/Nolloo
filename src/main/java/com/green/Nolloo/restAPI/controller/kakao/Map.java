package com.green.Nolloo.restAPI.controller.kakao;

import com.green.Nolloo.restAPI.service.restAPIService;
import com.green.Nolloo.restAPI.vo.MapVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Map {

    @Resource(name = "restAPIService")
    private restAPIService restAPIService;

    public double getDistanceBetweenPointsNew(double latitude1, double longitude1, double latitude2, double longitude2, String unit) {
        double theta = longitude1 - longitude2;
        double distance = 60 * 1.1515 * (180/Math.PI) * Math.acos(
                Math.sin(latitude1 * (Math.PI/180)) * Math.sin(latitude2 * (Math.PI/180)) +
                        Math.cos(latitude1 * (Math.PI/180)) * Math.cos(latitude2 * (Math.PI/180)) * Math.cos(theta * (Math.PI/180))
        );
        if (unit.equals("miles")) {
            return Math.round(distance);
        } else if (unit.equals("kilometers")) {
            return Math.round(distance * 1.609344);
        } else {
            return 0;
        }
    }


    @GetMapping("/position")
    public String pos() {
        return "/content/restAPI/position";
    }

    @ResponseBody
    @PostMapping("/position")
    public MapVO pos(@RequestParam(name="itemCode") int itemCode, Model model) {
        MapVO LatLng = restAPIService.selectMapLatLnt(itemCode);
        return LatLng;
    }


    @GetMapping("/find-items")
    public String findItems() {
        return "/content/restAPI/find-items";
    }

    @ResponseBody
    @PostMapping("/find-items")
    public List<MapVO> allPos() {
        List<MapVO> allPos = restAPIService.selectAllMapLatLnt();
        return allPos;
    }


    @GetMapping("/circle")
    public String circle() {

        return "/content/restAPI/circle";
    }



}
