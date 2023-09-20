package com.spring.Board.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.Board.Model.Board;
import com.spring.Board.Model.PageNation;
import com.spring.Board.Service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/")
	public String Home() {
		return "index";
	}

//	글쓰기
	@GetMapping("/board/write")
	public String Write() {
		return "/board/saveForm";
	}


//	for (MultipartFile file : fileList) {
//        if (!file.isEmpty()) {
//            byte[] fileData = fileList.getBytes();
//            System.out.println("fileData  : " + fileData);
//        }
//    }
	
	@PostMapping("/board/writeAction")
	public String writeAction(Board board, @RequestParam("file") List<MultipartFile> fileList) throws Exception {
		System.out.println("controller file : " + fileList);
		boardService.create(board, fileList);
		return "redirect:/board/";
	}
	
	// 이미지 파일을 불러와서 출력하는 메서드
//	@GetMapping("/board/images/{fileName:.+}")
//    public void getImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {
//        // 이미지 파일을 읽어서 응답으로 출력
////		response.setContentType("image/jpeg");
//        try (InputStream is = new FileInputStream("D:/files/" + fileName)) {
//            response.setContentType("image/jpeg"); // 이미지 타입 설정 (여기서는 JPEG로 가정)
//            IOUtils.copy(is, response.getOutputStream());
//        } catch (FileNotFoundException e) {
//            // 파일을 찾을 수 없는 경우 예외 처리
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        } 
//    }
		

	
//	@Autowired
//    private ServletContext servletContext;

//    @GetMapping("/images/{fileName:.+}")
//    public String getImage(@PathVariable String fileName, Model model) {
//        String webPath = servletContext.getContextPath() + "/uploads/" + fileName;
//        model.addAttribute("webPath", webPath);
//        return "image";
//    }
	 

	// 글 전체 목록 조회
	@GetMapping("/board")
	public String BoardList() {
		return "/board/list";
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

//	 글 상세보기
	@GetMapping("/board/detail/{id}")
	public String BoardDetail(@PathVariable("id") int id, Model model) {
		List<Board> board = boardService.detail(id);
		model.addAttribute("board", board);
		return "/board/detail";
	}

	// 글 삭제
	@GetMapping("/board/delete/{id}")
	public String BoardDelete(@PathVariable("id") Integer id) {
		boardService.Delete(id);
		return "redirect:/board";
	}

	// 글 수정
	@GetMapping("/board/update/{id}")
	public String Update(@PathVariable("id") Integer id, Model model) {
		List<Board> board = boardService.detail(id);
		model.addAttribute("board", board);
		return "/board/updateForm";
	}

//	// 글 수정
//	@PutMapping("/board/updateAction")
//	@ResponseBody
//	public String UpdateAction1(Board board, @RequestParam("file") List<MultipartFile> fileList) throws Exception {
//		System.out.println("controller file : " + fileList);
//		boardService.create(board, fileList);
//
//		return "redirect:/board/";
//	}

	// 글 수정

	@PutMapping("/board/updateAction")
	@ResponseBody
	public ResponseEntity<String> UpdateAction(@RequestBody Board board, @RequestParam("file") List<MultipartFile> fileList) {
		System.out.println("fileList: " + fileList);
		System.out.println("board: " +board);
		boardService.Update(board, fileList);
		return ResponseEntity.ok("{\"message\": \"Success\"}");
	}

}
