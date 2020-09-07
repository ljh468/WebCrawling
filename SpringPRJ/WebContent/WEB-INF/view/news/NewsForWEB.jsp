<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

		String res = CmmUtil.nvl((String)request.getAttribute("res"),"0");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
bbc뉴스 수집 <%=res %>개의 정보가 수집되었습니다.
</body>
</html>