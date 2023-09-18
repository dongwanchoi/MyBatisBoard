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

						<%-- 											<c:choose> --%>
						<%-- 												<c:when test="${board.visibility == 'public'}"> --%>
						<!-- 													<p>공개</p> -->
						<%-- 												</c:when> --%>
						<%-- 												<c:when test="${board.visibility == 'private'}"> --%>
						<!-- 													<p>비공개</p> -->
						<%-- 												</c:when> --%>
						<%-- 											</c:choose> --%>

						<c:choose>
							<c:when test="${board.fixed == 'fixation'}">
								<span class="ann">[공지] </span>
							</c:when>
						</c:choose>

						<span class="category">${board.category}</span>
						<nav class="nav"></nav>
					</header>

					<div class="container ">
						<div class="cont shadow">
							<input type="hidden" id="id" value="${board.id}">

							<div class="head">
								<h1>${board.title}</h1>
							</div>
							<div class="body">
								<p>${board.content}</p>
								<div>
									<c:forEach items="${board.fileList}" var="file">
										<c:if test="${file.file_name != null}">
											<img src="${file.file_path}" alt="${file.file_original_name}, ${file.file_path}">
										</c:if>
									</c:forEach>
								</div>
							</div>

							<div class="btn-box">
								<div class="btn-group">
									<button type="button" name="btn_delete" class="btn btn_delete">삭제</button>
									<button type="button" name="btn_update" class="btn btn_update">수정</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="../layout/footer.jsp"%>
			</div>
		</div>
	</c:forEach>
	<script type="text/javascript">
	const btn_update = document.querySelector(".btn_update");
	const btn_delete = document.querySelector(".btn_delete");
	const id = document.getElementById("id").value;

	btn_delete.addEventListener("click", ()=>{
		location.href="/board/delete/"+id;
	});
	
	btn_update.addEventListener("click", ()=>{
		location.href="/board/update/"+id;
	});
		
		
	</script>
</body>
</html>