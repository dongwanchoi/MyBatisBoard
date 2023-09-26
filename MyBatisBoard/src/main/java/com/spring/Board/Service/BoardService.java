package com.spring.Board.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.Board.Model.Board;
import com.spring.Board.Model.Files;
//import com.spring.Board.Model.MyFileHandler;
import com.spring.Board.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;

	public void BoardSave(Board board) throws Exception {
		mapper.createBoard(board); // 게시물 추가
	}

	public void FileSave(Board board, List<MultipartFile> files) throws Exception { // 글 생성

		System.out.println("files.size() : " + files.size());
		if (!files.isEmpty()) {
			String filePath = "D:\\files\\"; // 파일 저장 경로

			for (MultipartFile file : files) {

				File directory = new File(filePath); // 디렉토리 생성
				if (!directory.exists()) {
					directory.mkdirs();
				}

				UUID uuid = UUID.randomUUID(); // 무작위 값 생성
				String originalFilename = file.getOriginalFilename(); // 파일 이름
				String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1); // 파일 확장자 추출
				String fileName = uuid + "_" + originalFilename;
				File saveFile = new File(filePath, fileName);
				file.transferTo(saveFile); // 파일 서버에 저장

				String uploadPath = "/upload/";

				Files file_list = new Files();
				file_list.setFile_original_name(originalFilename);
				file_list.setFile_name(fileName);
				file_list.setFile_path(uploadPath + fileName);
				file_list.setFile_type(fileExtension); // 파일 확장자 설정

				System.out.println("전체 file_list: " + file_list);
				file_list.setBoard_id(board.getId()); // 추가된 게시물의 ID 얻기
				mapper.addFile(file_list); // 이미지 저장
			}
		}
	}

	public List<Board> detail(int id) {
		List<Board> boards = mapper.detail(id);
		System.out.println("boards: " + boards);
		return boards;
	}

	public void Delete(Integer id) { // 글 삭제
		mapper.deleteBoard(id);
	}

	public void Update(Board board, List<MultipartFile> files, List<Integer> delidList) { // 글 수정
		mapper.Update(board);
		for (Integer fid : delidList) {
			mapper.delFile(fid);
		}
	}

	public Map<String, Object> getBoardsByPage(Board board) { // 글 목록 조회
		Map<String, Object> resultMap = new HashMap<>();

		List<Board> boardList = mapper.getBoardsByPage(board); // 글 10개만
		int totalCount = mapper.selectCnt(board); // 글 전체 개수
		List<Board> Announcement = mapper.Announcement(board);

		resultMap.put("boardList", boardList);
		resultMap.put("totalCount", totalCount);
		resultMap.put("announcement", Announcement);

		return resultMap;
	}

}

//<script>
//const btn_delete =  document.querySelector(".btn_delete");
//const btn_update =  document.querySelector(".btn_update");
//
//btn_delete.addEventListener("click", () =>{
//	 if (confirm("정말 삭제하시겠습니까?") == true){
////		     location.href="/board/delete/${board.id}";
//	 }else{ 
//	     return false;
//	 }
//});
//
//btn_update.addEventListener("click", () =>{
////		 location.href="/board/update/${board.id}";
//});
//
//</script>
