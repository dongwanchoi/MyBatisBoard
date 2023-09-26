package com.spring.Board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.Board.Model.Board;
import com.spring.Board.Service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 테스트
	@GetMapping("/")
	public String Home() {
		return "index";
	}

	// 글쓰기
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "/board/saveForm";
	}

	// 글 전체 목록 조회
	@GetMapping("/board")
	public String BoardList() {
		return "/board/list";
	}

	// 글 상세보기
	@GetMapping("/board/detail/{id}")
	public String BoardDetail(@PathVariable("id") int id, Model model) {
		List<Board> board = boardService.detail(id);
		model.addAttribute("board", board);
		System.out.println("board: " +board);
		return "/board/detail";
	}

	// 글 수정
	@GetMapping("/board/updateForm/{id}")
	public String Update(@PathVariable("id") Integer id, Model model) {
		List<Board> board = boardService.detail(id);
		model.addAttribute("board", board);
		return "/board/updateForm";
	}

}
