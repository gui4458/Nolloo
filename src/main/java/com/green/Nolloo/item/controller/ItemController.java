package com.green.Nolloo.item.controller;

import com.green.Nolloo.item.service.ItemService;
import com.green.Nolloo.item.vo.ItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Resource(name = "itemService")
    private ItemService itemService;

    //item 목록조회
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("itemList",itemService.selectItem());
        return "content/main";
    }
    //item 등록
    @GetMapping("/itemAddForm")
    public String itemAddForm(){
        return"content/item/item_add_form";
    }
    @PostMapping("/itemAdd")
    public String itemAdd(ItemVO itemVO){
        itemService.insertItem(itemVO);
        return "redirect:/item/list";
    }

}
