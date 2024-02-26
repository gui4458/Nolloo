package com.green.Nolloo.board.controller;

import com.green.Nolloo.board.service.BoardService;
import com.green.Nolloo.board.vo.BoardVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class boardController {

    @Resource(name = "boardService")
    private BoardService boardService;

    //item 목록조회
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("boardList",boardService.selectBoardList());
        return "content/main";
    }
    //item 등록
    @GetMapping("/boardAddForm")
    public String itemAddForm(){
        return"content/board/board_add_form";
    }
    @PostMapping("/boardAdd")
    public String itemAdd(BoardVO boardVO){
        boardService.insertBoard(boardVO);
        return "redirect:/board/list";
    }

}
