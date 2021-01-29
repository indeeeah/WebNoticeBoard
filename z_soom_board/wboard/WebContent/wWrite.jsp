<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
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
}
</style>
<script type="text/javascript">
	function writefrm_submit(){
		//document.getElementById("bwriter");
		// 유효성 체크 부분 이부분에 넣어 줌
		// 유효성에 문제가 있으면 return false;
		// 유효성에 문제가 없다면 
		return true;
	}
</script>
</head>
<body>
<%
	String bno = (request.getParameter("bno")!=null) ? request.getParameter("bno") : "0";
	String bref = (request.getParameter("bref")!=null) ? request.getParameter("bref") : "0";
	String bre_step = (request.getParameter("bre_step")!=null) ? request.getParameter("bre_step") : "0";
	String bre_level = (request.getParameter("bre_level")!=null) ? request.getParameter("bre_level") : "0";
%>
<div>
	<p>글쓰기</p>
	<form enctype="multipart/form-data"	method="post" action="/wboard/boardInsert"	onsubmit="return writefrm_submit();">
		<input type="text" name="bno" value="<%=bno%>">   <!-- 0은 새글, 그외 댓글인 경우는 읽고 있던 글의 bno를 넣어주기로 함. -->
		<input type="text" name="bref" value="<%=bref%>">
		<input type="text" name="bre_step" value="<%=bre_step%>">
		<input type="text" name="bre_level" value="<%=bre_level%>">
		<table border="1">
			<tr>
				<td colspan="2"><a href="/wboard/boardList">글목록</a></td>
			</tr>
			<tr>
				<td>작성자ID</td>
				<td><input type="text" name="bwriter" id="bwriter"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name ="bsubject" id="bsubject"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><input type="text" name ="bcontent" id="bcontent"></td>
			</tr>
			<tr>
				<td>파일선택</td>
				<td><input type="file" name ="bfilepath" id="bfilepath"></td>
			</tr>
			<!-- <tr>
				<td>여러 파일선택</td>
				<td><input type="file" name ="bfilepath" id="bfilepaths" multiple="multiple"></td>
			</tr> -->
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name ="bpwd"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글등록">
					<input type="reset" value="다시작성">
					<input type="button" value="글목록" onclick="window.location='/wboard/boardList'">
				</td>
			</tr>
		</table>


	</form>
	</div>
</body>
</html>