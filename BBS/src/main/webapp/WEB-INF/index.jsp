<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
HOME <br />

<!-- sessionID 라는 이름의 값이 존재할 경우 해당 값과 로그인 중이라는 문구 출력 -->
<c:if test="${sessionID != null }">
	${sessionID } 로그인 중
</c:if>
</body>
</html>