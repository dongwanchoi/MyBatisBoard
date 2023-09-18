package com.spring.Board.Service;


import java.io.File;
import java.util.ArrayList;
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

	public void create(Board board, List<MultipartFile> files) throws Exception { // 글 생성
		// 이미지 정보 생성
		System.out.println("files.size() : " + files.size());
		List<Files> images = new ArrayList<>();
		mapper.createBoard(board); // 게시물 추가
		System.out.println("서비스 file1 : " + files);
		if (!files.isEmpty()) {
//			System.getProperty("user.dir") //현재 경로
//			String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\"; //파일 저장 경로
			UUID uuid = UUID.randomUUID(); // 무작위 값 생성
			String filePath = "D:\\files\\"; // 파일 저장 경로

			File directory = new File(filePath); // 디렉토리 생성
			if (!directory.exists()) {
			    directory.mkdirs(); 
			}
			
			System.out.println("filePath : " + filePath);
			for (MultipartFile file : files) {
				System.out.println("서비스 file2 : " + file);
				String originalFilename = file.getOriginalFilename(); // 파일 이름

				String fileName = uuid + "_" + originalFilename;
				
				File saveFile = new File(filePath, fileName);
				file.transferTo(saveFile); // 파일 서버에 저장

				
//				String fileExtension = FilenameUtils.getExtension(originalFilename);

				byte[] fileBytes = file.getBytes();
				
				Files file_list = new Files();
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ1");
				
				System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ2");
				
				file_list.setFile_original_name(originalFilename);
				file_list.setFile_name(fileName);
				file_list.setFile_path(filePath + fileName);
//			    file_list.setFile_type(fileExtension); // 파일 확장자 설정
			    file_list.setFile_data(fileBytes);
				// 이미지 정보와 게시물 정보 연결

			 // 파일 데이터 읽어오기
//	            byte[] fileBytes = MyFileHandler.readAllBytes(saveFile.toPath());
	            
				System.out.println("board.getId(): " + board.getId());
				file_list.setBoard_id(board.getId()); // 추가된 게시물의 ID 얻기
				
//				images.add(file_list);
				
				mapper.createFile(file_list); // 이미지 저장
			}
			System.out.println("images: " + images);
		}
	}

	public List<Board> detail(int id) {
		List<Board> boards = mapper.detail(id); 
		System.out.println(boards);
	    return boards;
	}
	
	
	public void Delete(Integer id) { // 글 삭제
		mapper.deleteBoard(id);
	}

	
	public void Update(Board board, List<MultipartFile> files){ // 글 수정
		mapper.Update(board);
		mapper.UpdateFile(files);
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
