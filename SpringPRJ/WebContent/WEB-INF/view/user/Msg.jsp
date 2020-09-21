<%@page import="poly.dto.UserInfoDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 

<%
	//Controller로부터 전달받은 데이터
	String msg = nvl((String)request.getAttribute("msg"));

	//Controller로부터 전달받은 웹(회원정보 입력화면)으로부터 입력받은 데이터(회원아이디, 이름, 이메일, 주소 등)
	UserInfoDTO pDTO = (UserInfoDTO)request.getAttribute("pDTO");
	
	if(pDTO==null){
		pDTO = new UserInfoDTO();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입을 축하드립니다.</title>
<script type="text/javascript">
	alert("<%=msg%>");
</script>
</head>
<body>
<%if(pDTO.getUser_id()!=null) {%>
<%=nvl(pDTO.getUser_name()) %>님의 회원가입을 축하드립니다.
<%} else {%>
	회원가입에 실패하셨습니다.
<% } %>
</body>
</html>