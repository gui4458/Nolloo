package com.green.Nolloo.calendar;

import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    @Resource(name="itemService")
    private ItemService itemService;

    @GetMapping("/goCalendar")
    public String goCalendar(){

        return "content/calendar/calendar";
    }
    @ResponseBody
    @PostMapping("/getCalendarInfo")
    public List<ItemVO> getCalendarInfo(){
        List<ItemVO> itemList = itemService.selectCalendarPartyList();

        return itemList;
    }
}
