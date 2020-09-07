<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/mail/sendMail.do" method="post">
	<table border="1" >
	<tr>
	<td>받는사람</td>
	<td><input type="text" name="toMail" placeholder="받는사람 이메일을 입력하세요." style="width:200px;" required></td>	
	</tr>
	<tr>
	<td>메일제목</td>
	<td><input type="text" name="title" placeholder="제목을 입력하세요" style="width:200px;" required></td>	
	</tr>
	<tr>
	<td rowspan="5">메일내용</td>
	<td><input type="text" name="contents" placeholder="내용을 입력하세요" style="width:200px; height:150px;" required></td>	
	</tr>
	</table>
	
	<input type="submit" value="메일전송">
	<input type="reset" value="작성 초기화">
	
	</form>
</body>
</html>