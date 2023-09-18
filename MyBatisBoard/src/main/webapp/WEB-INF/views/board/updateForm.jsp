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

	<c:forEach items="${board}" var="board">
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
							<!-- 						<form action="/board/updateAction" method="GET"> -->
							<input type="hidden" id="id" value="${board.id}" />

							<div class="form-group">


								<label for="category">카테고리 :</label> <select id="category" name="category">

									<c:choose>
										<c:when test="${board.category == '일반'}">
											<option value="${board.category}">${board.category}</option>
											<option value="뉴스">뉴스</option>
											<option value="질문">질문</option>
										</c:when>

										<c:when test="${board.category == '뉴스'}">
											<option value="${board.category}">${board.category}</option>
											<option value="일반">일반</option>
											<option value="질문">질문</option>
										</c:when>

										<c:when test="${board.category == '질문'}">
											<option value="${board.category}">${board.category}</option>
											<option value="일반">일반</option>
											<option value="뉴스">뉴스</option>
										</c:when>
									</c:choose>
								</select>
							</div>

							<div class="form-group">
								<label for="title">Title :</label> <input type="text" name="title" id="title" value="${board.title}" />
							</div>

							<div class="form-group">
								<label for="content">Content :</label>
								<textarea name="content" id="content">${board.content}</textarea>
							</div>

							<div class="form-group">
								<label for="filetest">첨부파일 : </label>
								<!-- 새로 업로드할 파일 선택 -->
								<input type="file" name="file" id="file" multiple="multiple" /> <label for="file">파일 선택</label>
							</div>

							<div class="file-group">
								<ul id="FileList">
									<c:forEach items="${board.fileList}" var="file">
										<li class="files"><span class="fileName" id="${file.file_id}">${file.file_original_name}</span></li>
									</c:forEach>
								</ul>
							</div>

							<div class="form-group">
								<label>글 가시성 :</label>
								<c:choose>
									<c:when test="${board.visibility == 'public'}">
										<label for="public">공개</label>
										<input type="radio" id="public" name="visibility" value="public" checked="checked">
										<label for="private">비공개</label>
										<input type="radio" id="private" name="visibility" value="private">
									</c:when>

									<c:when test="${board.visibility == 'private'}">
										<label for="public">공개</label>
										<input type="radio" id="public" name="visibility" value="public">
										<label for="private">비공개</label>
										<input type="radio" id="private" name="visibility" value="private" checked="checked">
									</c:when>
								</c:choose>
							</div>


							<div class="form-group">
								<label>고정여부 :</label> <label for="fixed">고정</label>

								<c:choose>
									<c:when test="${board.fixed == 'fixation'}">
										<input type="checkbox" id="fixed" name="fixed" value="fixation" checked="checked">
									</c:when>

									<c:when test="${board.fixed == null}">
										<input type="checkbox" id="fixed" name="fixed" value="fixation">
									</c:when>
								</c:choose>


							</div>

							<div class="btn-box">
								<div class="btn-group">
									<button type="submit" name="btn_update" class="btn btn_update">글 수정 완료</button>
									<button type="submit" name="btn_back" class="btn btn_back">취소</button>
								</div>
							</div>
							<!-- 						</form> -->
						</div>
					</div>
				</div>
				<%@ include file="../layout/footer.jsp"%>
			</div>
		</div>
	</c:forEach>
	<script type="text/javascript">
	
	const btn_back = document.querySelector(".btn_back");
	const test11 = document.querySelector(".files");
	
	const id = document.getElementById("id").value;
	
	btn_back.addEventListener("click", ()=>{
		location.href="/board/detail/"+id;
	});
	
// 	여기부터 시작
	const fileInput = document.getElementById("file");

	fileInput.onchange = () => {
	  const selectedFile = [...fileInput.files];
	  console.log(selectedFile);
	};
	
	
// 	document.getElementById('file').addEventListener('change', function() {
// 	    var fileList = document.getElementById('file').files;
// 	    var fileListContainer = document.getElementById('FileList');
	    
// 	    fileListContainer.innerHTML = ""; // 이전 내용을 지웁니다.

// 	    for (var i = 0; i < fileList.length; i++) {
// 	        var file = fileList[i];
// 	        var listItem = document.createElement('li');
// 	        listItem.textContent = '파일 이름: ' + file.name + ', 크기: ' + file.size + ' bytes';
// 	        fileListContainer.appendChild(listItem);
// 	    }
// 	});
	
	</script>
	<script src="/js/Board.js"></script>
</body>
</html>


