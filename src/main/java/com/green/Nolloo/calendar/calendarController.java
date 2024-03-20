package com.green.Nolloo.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calendar")
public class calendarController {

    @GetMapping("/goCalendear")
    public String goCalendear(){

        return "content/calendar/calendar";
    }
}
