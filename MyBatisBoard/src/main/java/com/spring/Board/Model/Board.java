package com.spring.Board.Model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.Board.dto.PageNation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data // getter, setter
@Builder
public class Board { // dto

	private int id;
	private String title;
	private String content;
	private String visibility;
	private String fixed;
	private String category;
	private PageNation pageNation;

	private Files files; //파일
	private List<Files> fileList; // 파일 목록
}
