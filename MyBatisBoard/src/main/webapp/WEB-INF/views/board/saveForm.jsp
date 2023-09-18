<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<div id="wrap">
		<%@ include file="../layout/sidebar.jsp"%>

		<div class="content-wrap">

			<div class="content">
				<header class="header shadow">
					header
					<nav class="nav"></nav>
				</header>

				<div class="container ">

					<div class="cont form_cont shadow">
						<form action="/board/writeAction" method="POST" enctype="multipart/form-data">

							<div class="form-group">
								<label>공지글 :</label> <label for="fixed">설정</label> <input type="checkbox" id="fixed" name="fixed" value="fixation">
							</div>

							<div class="form-group">
								<label for="category">카테고리 :</label> <select id="category" name="category">
									<option value="선택">선택</option>
									<option value="일반">일반</option>
									<option value="뉴스">뉴스</option>
									<option value="질문">질문</option>
									<!-- 다른 카테고리 항목 추가 -->
								</select>
							</div>

							<div class="form-group">
								<label for="title">Title :</label> <input type="text" name="title" id="title" />
							</div>


							<div class="form-group">
								<label for="content">Content :</label>
								<textarea name="content" id="content"></textarea>
							</div>


							<div class="form-group">
								<label for="filetest">첨부파일 : </label>
								<!-- 새로 업로드할 파일 선택 -->
								<input type="file" name="file" id="file" multiple="multiple" /> <label for="file">파일 선택</label>
							</div>

							<div class="file-group">
								<ul id="FileList">
									<c:forEach items="${board.fileList}" var="file">
										<li class="files">${file.file_original_name}
											<button class="deleteFiles" id="deleteFile" data-fileid="${file.file_id}">삭제</button>
										</li>
									</c:forEach>
								</ul>
							</div>

							<div class="form-group">
								<label>글 가시성 :</label> <label for="public">공개</label> <input type="radio" id="public" name="visibility" value="public" checked="checked"> <label for="private">비공개</label> <input
									type="radio" id="private" name="visibility" value="private">
							</div>

							<div class="btn-box">
								<div class="btn-group">
									<button type="submit" name="btn_create" class="btn btn_create">글쓰기</button>
									<button type="submit" name="btn_back" class="btn btn_back">뒤로가기</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<%@ include file="../layout/footer.jsp"%>
		</div>
	</div>
	<script src="/js/Validation.js"></script>


	<script type="text/javascript">
		// 파일 선택 시 hidden input 업데이트

		var selectedFileList = document.getElementById('FileList');

		document.getElementById('file').addEventListener('change',function() {
					var selectedFiles = this.files;
					console.log(selectedFiles);

					selectedFileList.innerHTML = '';
					// 선택한 파일들의 이름을 표시할 리스트 생성
					console.log(selectedFileList);

					for (var i = 0; i < selectedFiles.length; i++) {
						var selectedFile = selectedFiles[i];
						var listItem = document.createElement('li');
						listItem.textContent = selectedFile.name;

						// "삭제" 버튼 생성 및 속성 설정
// 						var deleteBtn = document.createElement('button');
// 						deleteBtn.textContent = '삭제';
// 						deleteBtn.classList.add('deleteFiles');
// 						deleteBtn.setAttribute('data-fileid',
// 								'your_file_id_here'); // 파일 ID를 설정하세요

// 						// "삭제" 버튼 클릭 이벤트 처리
// 						deleteBtn.addEventListener('click', function() {
// // 							var fileId = this.getAttribute('data-fileid');
// 							// 해당 리스트 아이템 삭제
// 							var listItemToRemove = this.parentElement;
// 							listItemToRemove.remove();
// 						});

// 						listItem.appendChild(deleteBtn);
						selectedFileList.appendChild(listItem);
					}
				});
	</script>

</body>
</html>


