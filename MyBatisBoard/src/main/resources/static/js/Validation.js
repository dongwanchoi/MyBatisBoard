document.addEventListener("DOMContentLoaded", function() {
	//	const updateButton = document.querySelector(".btn_update");
	const form = document.querySelector("form");
//	const createButton = document.querySelector(".btn_create");


	function Validation(e) {
		e.preventDefault();
		const title = document.getElementById("title").value;
		const content = document.getElementById("content").value;
		const category = document.getElementById("category").value;

		// 간단한 유효성 검사 예제: 제목과 내용 필드가 비어있지 않아야 함
		 if (category === "선택") {
			alert("카테고리를 선택해주세요.");
			return;
		}else if (title.trim() === "") {
			alert("제목을 입력해주세요.");
			return;
		} else if (content.trim() === "") {
			alert("내용을 입력해주세요.");
			return;
		} else

		form.submit();
	}



	form.addEventListener("submit", Validation);


});