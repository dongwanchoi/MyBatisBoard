window.onload = function() {
	const keyword = document.querySelector(".keyword");
	const boardBody = document.getElementById("boardBody");
	const pagination_Btns = document.querySelectorAll(".pagination li");
	const searchKeywordInput = document.getElementById("searchKeyword"); //검색 키워드
	const pagination = document.querySelector(".pagination"); //버튼 리스트

	let DataLists = []; // 데이터 담을 곳
	let PageNumber = 1; //현재 페이지
	let itemsPerPage = 10; // 보여질 페이지 수
	let btnCount; // 전체 리스트 개수

	const index = {
		init: () => {
			index.FullDataList(PageNumber);
			searchKeywordInput.addEventListener("input", index.performSearchTest); //input이 바뀌면 performSearch 실행
		},
		FullDataList: (PageNumber, inputKeyword) => { //전체 데이터 조회
			DataLists = []; // 데이터 담을 곳

			ajaxCmm('POST', '/board/updatePage', { pageNumber: PageNumber, keyword: inputKeyword }, function(data) { //데이터 가져오기

				btnCount = data.totalCount; //전체 리스트 개수
				DataLists.push(...data.boardList); // 전체 데이터 배열에 넣기

				index.ListOutput(DataLists); //화면 출력
				index.btnCalculation(btnCount, inputKeyword); // 버튼 수 계산 함수

				index.announcement(data.announcement);
			});
		},
		announcement: (announcement) => {
			//boardBody.innerHTML = ``; //초기화
			announcement.forEach(e => { //리스트 찍기
				const tr = document.createElement("tr");
				const td1 = document.createElement("td");
				td1.innerText = e.id;
				const td2 = document.createElement("td");
				td2.innerHTML = `<a href="/board/detail/${e.id}"><span class='ann'>[공지]</span><span class='category'>${e.category}</span> ${e.title}</a>`;
				
				tr.append(td1);
				tr.append(td2);

				tr.classList.add("ann");
				boardBody.prepend(tr);
			
			});
		},

		ListOutput: (DataLists) => {
			boardBody.innerHTML = ``; //초기화

			DataLists.forEach(item => { //리스트 찍기
				boardBody.innerHTML += `
   				<tr>
   					<td>${item.id}</td>
   					<td><a href="/board/detail/${item.id}"><span class='category'>${item.category}</span> ${item.title}</a></td>
   				</tr>`;
			});
		},

		btnCalculation: (btnCount, inputKeyword) => { //버튼 개수, 키워드
			const totalPages = Math.ceil(btnCount / itemsPerPage); //버튼 수 계산
			index.createPageButtons(totalPages, inputKeyword); //버튼 생성 함수
		},

		createPageButtons: (btnNum, inputKeyword) => { //버튼 생성
			pagination.innerHTML = ``; // 기존 버튼 영역 초기화

			for (let i = 1; i <= btnNum; i++) {
				const li = document.createElement("li");
				const button = document.createElement("button");
				button.textContent = i;
				button.addEventListener("click", () => index.goToPage(i, inputKeyword));

				li.appendChild(button);
				pagination.appendChild(li);
			}
		},

		goToPage: (i, inputKeyword) => {
			index.FullDataList(i, inputKeyword); //버튼 클릭 시 화면 다시 그림
		},

		performSearchTest: (e) => {
			return index.FullDataList(1, e.target.value);
		},
	}
	index.init();
}