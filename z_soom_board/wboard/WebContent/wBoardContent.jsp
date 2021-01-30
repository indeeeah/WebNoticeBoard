<%@page import="board.model.service.BoardSrv"%>
<%@page import="board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
body{
background-color:beige;
}
div{
margin-left:20px;
margin-right:20px;
margin-top:20px;
}
table>tbody{
background-color:white;
text-align:center;
}
</style>
</head>
<body>
<% 
int bno = Integer.parseInt(request.getParameter("bno"));
BoardSrv bservice = new BoardSrv();
List<Board> list = bservice.getBoard(bno);  // bno를 가지고 읽어왔으므로 list는 0번 인덱스만 존재할 것임.
Board vo = list.get(0);   //

// 목록보기 시 이전에 보던 페이지로 이동하도록 하기 위함.
String pageNum = request.getParameter("pageNum");
if(pageNum==null){
	pageNum="1";
}
%>
<div>
	<p>글 보기</p>
	<form method="post" action="wWrite.jsp">
		<input type="hidden" name="bno" value="<%=bno%>">   <!-- 현재 보고 있는 글의 글 번호를 입력 -->
		<input type="hidden" name="bref" value="<%=vo.getBref()%>">   <!-- 현재 보고 있는 글의 ref를 입력 -->
		<input type="hidden" name="bre_step" value="<%=vo.getBre_step()%>">   <!-- 현재 보고 있는 글의 re_step를 입력 -->
		<input type="hidden" name="bre_level" value="<%=vo.getBre_level()%>">   <!-- 현재 보고 있는 글의 re_level를 입력 -->
		<table border="1" class="table table-hover">
			<!-- <tr>
				<td colspan="2"><a href="wList.jsp">글목록</a></td>
			</tr> -->
			<tr>
				<td class="active">작성자ID</td>
				<td><%= vo.getBwriter() %></td>
			</tr>
			<tr>
				<td class="active">제목</td>
				<td><%= vo.getBsubject() %></td>
			</tr>
			<tr>
				<td class="active">내용</td>
				<td><%= vo.getBcontent() %></td>
			</tr>
			<tr>
				<td class="active">파일선택</td>
				<td><%= vo.getBfilepath() %></td>
			</tr>
			<tr>
				<td class="active">비밀번호</td>
				<td><%= vo.getBpwd() %></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-success" value="답글작성">
					<input type="button" class="btn btn-warning" value="삭제">
					<input type="button" class="btn btn-primary" value="글목록" onclick="window.location='wList.jsp?pageNum=<%=pageNum%>'">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>