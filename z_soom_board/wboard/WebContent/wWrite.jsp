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
margin-right:20px;
margin-top:20px;
}
table>tbody{
background-color:white;
text-align:center;

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
	<form enctype="multipart/form-data"	method="post" action="/board/boardInsert"	onsubmit="return writefrm_submit();">
		<input type="hidden" name="bno" value="<%=bno%>">   <!-- 0은 새글, 그외 댓글인 경우는 읽고 있던 글의 bno를 넣어주기로 함. -->
		<input type="hidden" name="bref" value="<%=bref%>">
		<input type="hidden" name="bre_step" value="<%=bre_step%>">
		<input type="hidden" name="bre_level" value="<%=bre_level%>">
		<table border="1" class="table table-hover">
			<!-- <tr class="active">
				<td colspan="2"><a href="/board/boardList">글목록</a></td>
			</tr> -->
			<tr>
				<td class="active">작성자ID</td>
				<td><input type="text" class="form-control" name="bwriter" id="bwriter"></td>
			</tr>
			<tr>
				<td class="active">제목</td> 
				<td><input type="text" class="form-control" name ="bsubject" id="bsubject"></td>
			</tr>
			<tr>
				<td class="active">내용</td>
				<td><input type="text" class="form-control" name ="bcontent" id="bcontent"></td>
			</tr>
			<tr>
				<td class="active">파일선택</td>
				<td><input type="file" name ="bfilepath" id="bfilepath"></td>
			</tr>
			<!-- <tr>
				<td>여러 파일선택</td>
				<td><input type="file" name ="bfilepath" id="bfilepaths" multiple="multiple"></td>
			</tr> -->
			<tr>
				<td class="active">비밀번호</td>
				<td><input type="password" class="form-control" name ="bpwd"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="btn btn-success" value="글등록">
					<input type="reset" class="btn btn-warning" value="다시작성">
					<input type="button" class="btn btn-primary" value="글목록" onclick="window.location='/wboard/boardList'">
				</td>
			</tr>
		</table>


	</form>
	</div>
</body>
</html>