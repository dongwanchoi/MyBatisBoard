package com.spring.Board.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageNation {

	public int pageNumber = 1; //현재 페이지
	public int PageSize = 10; // 보여질 페이지 수
	public String keyword; // 검색 키워드
	public int offset = 0; // 시작
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
		this.offset = Math.max((pageNumber - 1) * this.PageSize, 0);
	}
}