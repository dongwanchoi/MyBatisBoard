const keyword = document.querySelector(".keyword");
const boardBody = document.getElementById("boardBody");
const pagination_Btns = document.querySelectorAll(".pagination li");
const searchKeywordInput = document.getElementById("searchKeyword"); //검색 키워드
const pagination = document.querySelector(".pagination"); //버튼 리스트

let pageNumber = 1; //현재 페이지
let itemsPerPage = 10; // 보여질 페이지 수

const index = {
	init: () => {
		index.FullDataList();
		searchKeywordInput.addEventListener("input", index.performSearchTest); //input이 바뀌면 performSearch 실행
	},
	FullDataList: (e) => { //전체 데이터 조회
		console.log("e1:" + e);
		ajaxCmm('POST', '/board/updatePage', pageNumber, function(data) { //데이터 가져오기
			let DataLists = []; // 데이터 담을 곳
			DataLists.push(...data); // 전체 데이터 배열에 넣기
			if (e == undefined) {
				//index.ListOutput(DataLists, pageNumber); //전체 데이터와 pageNumber을 담아서 리스트 뽑기
				index.btnCalculation(DataLists, pageNumber); // 전체 데이터와 pageNumber을 담아서 계산
			} else {
				index.performSearch(pageNumber, e, DataLists);
			}
		});
	},

	performSearchTest: (e) => {
		index.FullDataList(e.target.value);
	},

	btnCalculation: (boardData, pageNumber) => {
		const startIndex = (pageNumber - 1) * itemsPerPage; // 보여지는 첫 번째 글 번호 //0
		const endIndex = startIndex + itemsPerPage; //보여지는 마지막 글 번호 // 10
		const slicedData = boardData.slice(startIndex, endIndex); //10개씩 자르기
		const totalPages = Math.ceil(boardData.length / itemsPerPage); //버튼 수 계산

		index.ListOutput(slicedData, totalPages); //전체 데이터와 pageNumber을 담아서 리스트 뽑기
		index.createPageButtons(totalPages, boardData);
	},
	ListOutput: (slicedData, totalPages) => {
		boardBody.innerHTML = ``; //초기화
		slicedData.forEach(item => { //리스트 찍기
			boardBody.innerHTML += `
   				<tr>
   					<td>${item.id}</td>
   					<td>${item.title}</td>
   				</tr>`;
		});
	},

	createPageButtons: (btnNum, boardData) => { //버튼 생성
		pagination.innerHTML = ""; // 기존 버튼 영역 초기화

		for (let i = 1; i <= btnNum; i++) {
			const li = document.createElement("li");
			const button = document.createElement("button");
			button.textContent = i;
			button.addEventListener("click", () => index.goToPage(i, boardData));

			li.appendChild(button);
			pagination.appendChild(li);
		}
	},

	goToPage: (pageNumber, boardData) => { //버튼 생성 함수
		index.btnCalculation(boardData, pageNumber);
	},

	performSearch: (pageNumber, searchWord, DataLists) => {
		let searchData = []; // 데이터 담을 곳

		const keyword = searchWord.toLowerCase(); // 입력값을 소문자로 변환하여 일관된 검색을 위해
		const filteredData = DataLists.filter(item => item.title.toLowerCase().includes(keyword)); // 모든 키워드 검색
		searchData.push(...filteredData); // 검색한 리스트만 담기
		index.btnCalculation(filteredData, pageNumber);
	},
}

index.init();