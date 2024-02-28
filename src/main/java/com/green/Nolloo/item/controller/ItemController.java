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

    //파티게시글 목록조회
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("itemList",itemService.selectPartyList());
        return "content/main";
    }
    //게시글 등록
    @GetMapping("/itemAddForm")
    public String boardAddForm(){
        return"content/item/item_add_form";
    }
    @PostMapping("/itemAdd")
    public String boardAdd(ItemVO itemVO){
        itemService.insertParty(itemVO);
        return "redirect:/item/list";
    }
    //itemDetail 조회
    @GetMapping("/itemDetailForm")
    public String boardDetailForm(ItemVO itemVO, Model model){
        model.addAttribute("item",itemService.selectPartyDetail(itemVO));
        return "content/item/item_detail";
    }
    //게시글 삭제
    @GetMapping("/deleteItem")
    public String deletePartt(ItemVO itemVO){
        itemService.deleteParty(itemVO);
        return "redirect:/item/list";
    }

    @GetMapping("/updateForm")
    public String updateForm(Model model, ItemVO itemVO){

        model.addAttribute("item",itemVO);
        return "content/item/item_update_form";
    }

    @PostMapping("/updateParty")
    public String updateParty(ItemVO itemVO){
        itemService.updateParty(itemVO);

        return "redirect:/item/itemDetailForm?itemCode="+itemVO.getItemCode();
    }
}
