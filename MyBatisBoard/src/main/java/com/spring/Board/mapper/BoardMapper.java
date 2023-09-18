package com.spring.Board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.spring.Board.Model.Board;
import com.spring.Board.Model.Files;

@Mapper
@Repository
public interface BoardMapper {
	int createBoard(Board board); //글쓰기
	int createFile(Files file); //파일 추가

	int deleteBoard(Integer id); // 글 삭제
	int Update(Board board); // 글 수정
	Files UpdateFile(List<MultipartFile> files); // 글 수정
	
//	public List<Board> detail(int id);
//	public List<Files> detail_Files(int id);
	List<Board> detail(int id);
	
//	List<Board> detail(int id); // 글 상세보기
//	public Map<String, Object> detail(int id);
//	public Board detail(int id);
	
	int selectCnt(Board board); //글 리스트 개수 조회
	List<Board> getBoardsByPage(Board board); //글 10개 조회
	List<Board> Announcement(Board board); //공지 글만 조회
	
}
