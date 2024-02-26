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

    //파티게시글 목록조회
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("boardList",boardService.selectBoardList());
        return "content/main";
    }
    //게시글 등록
    @GetMapping("/boardAddForm")
    public String boardAddForm(){
        return"content/board/board_add_form";
    }
    @PostMapping("/boardAdd")
    public String boardAdd(BoardVO boardVO){
        boardService.insertBoard(boardVO);
        return "redirect:/board/list";
    }
    //boardDetail 조회
    @GetMapping("/boardDetailForm")
    public String boardDetailForm(BoardVO boardVO, Model model){
        model.addAttribute("board",boardService.selectBoardDetail(boardVO));
        return "content/board/board_detail";
    }
    //게시글 삭제
    @GetMapping("/deleteBoard")
    public String deleteBoard(BoardVO boardVO){
        boardService.deleteBoard(boardVO);
        return "redirect:/board/list";
    }
}
