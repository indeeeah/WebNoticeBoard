<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
int pageSize = 3; //  한 페이지당글수
int pageBlock = 2; // 한 화면에 나타날 페이지 링크 수
%>
<%
int bCount = (int)request.getAttribute("bCount");
int currentPage = (int)request.getAttribute("currentPage");
int startPage = (int)request.getAttribute("startPage");
int endPage = (int)request.getAttribute("endPage");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
body{
background-color:beige;
}
div{
margin-left:20px;
margin-top:20px;
}
table>tbody{
background-color:white;
text-align:center;
}
</style>
</head>
<body>
	<div>
		<table border="1">
			<tr>
				<td colspan="5"><a href="wWrite.jsp">글쓰기</a></td>
			</tr>
			<tr>
				<td>글번호</td>
				<td>ref</td>
				<td>re_step</td>
				<td>re_level</td>
				<td>글쓴이</td>
				<td>제목</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
			<c:if test="${not empty list}">
				<c:forEach items="${list}" var="vo" varStatus="s">
					<tr>
					<td>${vo.bno}</td>
					<td>${vo.bref}</td>
					<td>${vo.bre_step}</td>
					<td>${vo.bre_level}</td>
					<td>${vo.bwriter}</td>
					<td>
					<%--
					int w = 0;
					for (int j = 0; j < vo.getBre_level(); j++) {
					--%> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<%-- 	} --%> 
					<a href="wBoardContent.jsp?bno=${vo.bno}&pageNum=${currentPage}">${vo.bsubject}</a>
				</td>
				<td>
				<%--=sdf.format(vo.getBdate())--%>
				</td>
				<td>${vo.bcnt}</td>
			</tr>
				</c:forEach>
			</c:if>
		</table>
		<%
			if (bCount > 0) {
			// [이전]
			if (startPage > pageBlock) {
		%>
		<a href="boardList?pageNum=<%=startPage - pageBlock%>">[이전]</a>
		<%
			}

		// [1][2][3]...
		for (int i = startPage; i <= endPage; i++) {
		if (currentPage != i) {
		%>
		<a href="boardList?pageNum=<%=i%>">[<%=i%>]
		</a>
		<%
			} else {
		%>
		[<%=i%>]
		<%
			}
		}

		// [다음]
		if (startPage < pageBlock) {
		%>
		<a href="boardList?pageNum=<%=startPage + pageBlock%>">[다음]</a>
		<%
			}
		}
		%>

	</div>
</body>
</html>














