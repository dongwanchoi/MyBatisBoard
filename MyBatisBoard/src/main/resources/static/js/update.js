document.addEventListener("DOMContentLoaded", function() {
	const form = document.querySelector("form");
	const fileInput = document.getElementById('file');
	const $delBtns = document.querySelectorAll('.del');
	let list = [];
	let delFiles = [];
	var formData = new FormData();
	const index = {
		init: () => {
			form.addEventListener("submit", (e) => {
				e.preventDefault();

				if (index.validateForm()) {
					index.update(list);
				}
			});

			fileInput.addEventListener('change', () => {
				index.fileList(list);
			});

			$delBtns.forEach(button => {
				button.addEventListener('click', function() {
					index.delFileEvent(this);
				});
			});
		},

		delFileEvent: function(button) {

			console.log("삭제버튼 실행됨");
			const fid = button.getAttribute('data-fileid');
			delFiles.push(fid);
			const delFile = document.getElementById('delFile');
			delFile.value = delFiles;
			const parentLi = button.closest("li");
			parentLi.remove();
			console.log(delFiles);

			//				delFiles.forEach(fid => {
			//					const valueToAdd = fid || '';
			//					console.log(fid);
			//					formData.append('delid', valueToAdd);
			//				});
		},
		update: function(list) {
			//			var formData = new FormData();
			//				let delFiles = [];
//			console.log("delFiles:", delFiles);
//			delFiles.forEach(fid => {
//				const valueToAdd = fid || '';
//				console.log("fid 널인지 아닌지 판단", fid);
//				formData.append('delid', '');
//			});

			if (delFiles.length === 0) {
				const valueToAdd = ''; // 빈 문자열 추가
				formData.append('delid', valueToAdd);
			} else {
				delFiles.forEach(fid => {
					const valueToAdd = fid || '';
					console.log(fid);
					formData.append('delid', valueToAdd);
				});
			}


			const title = document.getElementById("title").value;
			const content = document.getElementById("content").value;
			const category = document.getElementById("category").value;
			const visibility = document.querySelector('input[name="visibility"]:checked').value;
			const fixed_ck = document.querySelector('input[name="fixed"]');

			let fixed;

			if (fixed_ck.checked) {
				console.log("체크");
				fixed = fixed_ck.value;
			} else {
				fixed = null;
			}

			let data = {
				//				id : id,
				title: title,
				content: content,
				visibility: visibility,
				fixed: fixed,
				category: category,
				//				delFile: delFile,
			};

			for (var i = 0; i < list.length; i++) {
				// 리스트에 파일 추가
				formData.append('file', list[i]);
			}

			console.log("list:list: ", list);
			console.log("data:data: ", data);

			formData.append('key', new Blob([JSON.stringify(data)], { type: "application/json" }));
			formData.append('id', document.getElementById("id").value); // input hidden 값을 추가

			$.ajax({
				type: "POST",
				enctype: "multipart/form-data",
				url: '/board/update',
				data: formData,
				processData: false,
				contentType: false,
			}).done((response) => {
				if (response.result === "OK") {
//					alert("파일 업로드 성공");
				} else {
//					alert("서버 내 오류로 처리가 지연되고 있습니다. 잠시 후 다시 시도해주세요");
				}
				alert("글 쓰기 완료");
				location.href = "/board";
			}).fail((jqXHR, textStatus, errorThrown) => {
				alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
				alert("Status: " + jqXHR.status + "\nError: " + errorThrown);
			});
		},
		validateForm: function(e) {
			const title = document.getElementById("title").value;
			const content = document.getElementById("content").value;
			const category = document.getElementById("category").value;

			if (category === "선택") {
				alert("카테고리를 선택해주세요.");
				return;
			} else if (title.trim() === "") {
				alert("제목을 입력해주세요.");
				return;
			} else if (content.trim() === "") {
				alert("내용을 입력해주세요.");
				return;
			}

			// 유효성 검사 통과
			return true;
		},

		fileList: function(list) {
			var selectedFileList = document.getElementById('FileList');
			let files = fileInput.files;

			for (var i = 0; i < files.length; i++) {
				const li = document.createElement("li"); //파일 담을 곳
				const fileName = document.createElement('span'); // 파일 이름 표시
				fileName.textContent = files[i].name;

				const deleteBtn = document.createElement("button"); // 삭제버튼
				deleteBtn.innerText = "삭제";

				// 삭제 버튼 클릭 이벤트 처리
				deleteBtn.addEventListener('click', function() {
					const parentLi = this.closest("li");
					if (parentLi) {
						const indexToRemove = Array.from(parentLi.parentElement.children).indexOf(parentLi);
						if (indexToRemove !== -1) {
							console.log("성공!!");
							console.log(indexToRemove);
							parentLi.remove();

							// 배열에서도 해당 인덱스의 파일 제거
							list.splice(indexToRemove, 1);
						}
					}
				});


				// <li> 엘리먼트에 파일 이름과 삭제 버튼 추가
				li.appendChild(fileName);
				li.appendChild(deleteBtn);

				// <ul> 엘리먼트에 <li> 엘리먼트 추가
				selectedFileList.appendChild(li);

				// 리스트에 파일 추가
				list.push(files[i]);
			}

			fileInput.value = ""; //파일 다시 초기화
			console.log(delFiles);
		},
		delFile: function(fid) {
			const delFile = document.getElementById('delFile');
			delFiles.push(fid);
			delFile.value = delFiles;
			console.log(delFile.value);
		},
	}


	index.init();

});