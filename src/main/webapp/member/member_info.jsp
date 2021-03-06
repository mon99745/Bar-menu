<%@ page import="com.member.study.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
MemberDTO dto = (MemberDTO) request.getAttribute("dto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member info JSP</title>
</head>
<body>
<div align="center">
	<h3>[회원 정보 상세 보기]</h3>
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><%=dto.getMember_id() %></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<%
			int pwLength = dto.getMember_pwd().length();
			String mark = dto.getMember_pwd().substring(0, 2);
			for(int i = 0; i < pwLength - 2; i++) {
				mark += "*";
			}
			out.println("<td>" + mark + "</td>");
			%>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getMember_name() %></td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td><%=dto.getMember_birthday() %></td>
		</tr>
		<tr>
			<th>성별</th>
			<td><%=dto.getMember_gender() %></td>
		</tr>
		<tr>
			<th>휴대폰 번호</th>
			<%if(dto.getMember_phone() == null) { %>
				<td>휴대폰 번호가 입력되지 않았습니다.</td>
			<%} else { %>
				<td><%=dto.getMember_phone() %></td>
			<%} %>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="회원 목록 보기" onclick="location.href='memberListAction.me'" />
				<input type="button" value="게시판 보기" onclick="location.href='boardList.bo'" />
				<input type="button" value="로그아웃" onclick="location.href='memberLogout.me'" />
			</td>
		</tr>
	</table>
</div>
</body>
</html>