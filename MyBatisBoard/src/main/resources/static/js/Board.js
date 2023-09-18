document.addEventListener("DOMContentLoaded", function() {
	const UpdateBtn = document.querySelector(".btn_update");

	const index = {
		init: () => {
			UpdateBtn.addEventListener("click", () => {
				index.update();
			});
		},

		update: function() {
			const id = document.getElementById("id");
			const title = document.getElementById("title").value;
			const content = document.getElementById("content").value;
			const category = document.getElementById("category").value;
			const visibility = document.querySelector('input[name="visibility"]:checked').value;
			const fixed_ck = document.querySelector('input[name="fixed"]');
			const files = document.querySelector('#file');
			
			console.log(files);
			let fixed;
			console.log(fixed_ck);

			if (fixed_ck.checked) {
				console.log("체크");
				fixed = fixed_ck.value;
			} else {
				fixed = null;
			}

			let data = {
				id: id.value,
				title: title,
				content: content,
				visibility: visibility,
				fixed: fixed,
				category: category,
				files: files,
			};

			console.log(data);
			$.ajax({
				type: "PUT",
				url: "/board/updateAction",
				data: JSON.stringify(data),
				contentType: "application/json; charset=utf-8",
				dataType: "json"
			}).done(() => {
				if (data.category === "선택") {
					alert("카테고리를 선택해주세요.");
					return;
				} else if (data.title.trim() === "") {
					alert("제목을 입력해주세요.");
					return;
				} else if (data.content.trim() === "") {
					alert("내용을 입력해주세요.");
					return;
				}
				alert("글 수정 완료");
				location.href = "/board";

			}).fail((jqXHR, textStatus, errorThrown) => {
				alert("글 수정 실패");
				alert("Status: " + jqXHR.status + "\nError: " + errorThrown);
			});
		},
	}


	index.init();

});