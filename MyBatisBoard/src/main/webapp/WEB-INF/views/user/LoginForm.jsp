<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<title>회원가입</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<div class="form_cont">
		<form action="/auth/loginProc" method="POST">
			<div class="form-group">
				<label for="username">Username</label>
				<input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
			</div>
			
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
			</div>
			<!-- 
			<div class="form-group form-check">
				<label class="form-check-label">
				<input name="remember" class="form-check-input" type="checkbox">Remember me
				</label>
			</div> -->
			
			<button type="submit" class="btn btn-primary" id="btn-login">로그인</button>
			<a href="https://kauth.kakao.com/oauth/authorize
			?response_type=code
			&client_id=40a41d46cf3c3398a4b7be5a06f612ce
			&redirect_uri=http://localhost:8000/auth/kakao/callback"><img height="38px" src="/image/kakao_login_button.png"/></a>
		</form>
	</div>
 
	<%@ include file="../layout/footer.jsp"%>
</body>
</html>


