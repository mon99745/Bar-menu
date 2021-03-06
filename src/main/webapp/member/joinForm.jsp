<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>단타포차에 오신걸 환영합니다.</title>
<link rel="stylesheet" href="../css/login.css">
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<div id=tablet>
		<div id=left-sidebar>
			<!--왼쪽 사이드 레이아웃-->
			<button class="mainBtn" onClick="history.back();">HOME</button>

		</div>
		<div id=middle>
			<!--가운데 레이아웃-->
			<div class=middle-top>
				<div class=logo href="menu.jsp">
					<!--단타포차 로고-->
					<button type="button" id="logo">
						<a href="menu.jsp">Danta</a>
					</button>
				</div>
			</div>
			<div class="login-page">
				<div class="form">
					<form action="memberJoinAction.me" method="post">
						<table border="1">


							<input type="text" name="member_id" placeholder="ID" required="required" />
							<input type="password" name="member_pwd" placeholder="password" required="required" />
							<input type="text" name="member_name" placeholder="성명" required="required" />
							<input type="text" name="member_birthday" placeholder="생년월일 (-)생략 " required="required" min="0" />
							
							<tr>
							<input type="radio" name="member_gender" value="남" checked="checked" />남자
							<input type="radio" name="member_gender" value="여" />여자
							</tr>
							<input type="text" name="member_phone" placeholder="휴대폰 번호 (-)생략"maxlength="30" />
							<td colspan="2" align="center">
							<input type="submit" value="회원가입" /> 
							<input type="reset" value="다시 작성" /> 
							<input type="button" value="로그인" onclick="location.href='memberLogin.me'" /></td>


						</table>
					</form>
				</div>
			</div>
</body>
</html>