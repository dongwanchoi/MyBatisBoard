<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="layout/header.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<div id="wrap">
		<%@ include file="layout/sidebar.jsp"%>
		
		<div class="content-wrap">

			<div class="content">
				<header class="header shadow">
					header
					<nav class="nav"></nav>
				</header>

				<div class="container ">
					
					<div class="cont1">메인 페이지 입니다.</div>
					
				</div>
			</div>
			<%@ include file="layout/footer.jsp"%>
		</div>
	</div>

</body>
</html>


