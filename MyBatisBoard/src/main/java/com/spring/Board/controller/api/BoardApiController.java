package com.spring.Board.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.Board.Model.Board;
import com.spring.Board.Service.BoardService;
import com.spring.Board.dto.PageNation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardApiController {

	@Autowired
	private BoardService boardService;

//	글쓰기
	@PostMapping("/board/save")
	@ResponseBody
	public void BoardSave(@RequestPart(value = "key") Map<String, Object> param,
			@RequestParam(value = "file", required = false) List<MultipartFile> fileList) throws Exception {
		Board board = new Board();

		board.setTitle((String) param.get("title"));
		board.setContent((String) param.get("content"));
		board.setVisibility((String) param.get("visibility"));
		board.setFixed((String) param.get("fixed"));
		board.setCategory((String) param.get("category"));

		System.out.println("controller board : " + board);
		System.out.println("controller param : " + param);
		System.out.println("controller fileList : " + fileList);

		boardService.BoardSave(board);
		if (fileList != null && !fileList.isEmpty()) {
			// 파일이 선택되었을 때 fileList를 처리

			System.out.println("파일있음");
			boardService.FileSave(board, fileList);
		} else {
			// 파일이 선택되지 않았을 때 처리
			System.out.println("파일없음");
		}
	}

	// 글 조회
	@PostMapping("/board/updatePage")
	@ResponseBody
	public Map<String, Object> BoardListAction(@RequestParam Integer pageNumber,
			@RequestParam(required = false) String keyword) {
		Map<String, Object> resultMap = new HashMap<>();

		Board board = new Board();
		PageNation pageNation = new PageNation();

		pageNation.setPageNumber(pageNumber);
		pageNation.setKeyword(keyword);
		board.setPageNation(pageNation);

		resultMap = boardService.getBoardsByPage(board);
		System.out.println("서비스:" + resultMap);

		return resultMap;
	}

	// 글 삭제
	@GetMapping("/board/delete/{id}")
	public String BoardDelete(@PathVariable("id") Integer id) {
		boardService.Delete(id);
		return "redirect:/board";
	}

	// 글 수정
	@PostMapping("/board/update")
	@ResponseBody
	public void BoardUpdate(@RequestPart(value = "key") Map<String, Object> param,
			@RequestParam(value = "file", required = false) List<MultipartFile> fileList,
			@RequestParam(value = "id", required = false) int id,
			@RequestParam(value = "delid", required = false) List<Integer> delidList) throws Exception {
		Board board = new Board();

		board.setId(id);
		board.setTitle((String) param.get("title"));
		board.setContent((String) param.get("content"));
		board.setVisibility((String) param.get("visibility"));
		board.setFixed((String) param.get("fixed"));
		board.setCategory((String) param.get("category"));

		System.out.println("controller delid : " + delidList);
		System.out.println("controller board : " + board);
		System.out.println("controller param : " + param);
		System.out.println("controller fileList : " + fileList);

		boardService.Update(board, fileList, delidList);

		if (fileList != null && !fileList.isEmpty()) {
			// 파일이 선택되었을 때 fileList를 처리
			System.out.println("파일있음");
			boardService.FileSave(board, fileList);
		} else {
			// 파일이 선택되지 않았을 때 처리
			System.out.println("파일없음");
		}

	}

}
