<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
					<p>테이블 입니다.</p>
					<div class="table_wrap shadow">
						<div class="table-header">
							<h6>게시판 테이블</h6>
						</div>
						<div class="table-body">
							<div class="table-option">
								<div class="table_length">
									<p>Show 10 entries</p>
								</div>

								<div class="search_box">
									<label>Search: </label> <input type="text" name="keyword" class="keyword" id="searchKeyword" placeholder="">
								</div>
							</div>
							<div class="table">
								<table>
									<thead>
										<tr>
											<th>글 번호</th>
											<th>제목</th>
										</tr>
									</thead>
									<tbody id="boardBody">
									</tbody>
								</table>
							</div>
							<div class="pagination_box">
								<ul class="pagination">
								</ul>
							</div>
						</div>
					</div>
				</div>

			</div>
			<%@ include file="../layout/footer.jsp"%>
		</div>
	</div>
<script src="/js/ListOutput.js"></script>
</body>
</html>