package com.green.Nolloo.restAPI.controller.kakao;

import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ItemVO;
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

    @Resource(name="itemService")
    private ItemService itemService;

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

    @GetMapping("/now")
    public String circle(Model model) {
//        List<ItemVO> itemList = itemService.selectByDistance();
//        model.addAttribute("itemList",itemList);

        return "/content/member/my-pos";
    }

    @ResponseBody
    @PostMapping("/now")
    public List<ItemVO> nowPos() {
        List<ItemVO> nowPos = itemService.selectByDistance();
        System.out.println(nowPos);
        return nowPos;
    }

}
