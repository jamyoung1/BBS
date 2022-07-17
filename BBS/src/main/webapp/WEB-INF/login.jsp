<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<style>
.container {
	width: 385px;
	line-height: 50px;
	margin: 40px auto;	
}
h5{
	text-align: center;
}

h5 span {
	ccolor: teal;
}

.login{
	background-color: rgb(255, 80, 90);
	color: white;
	border-radius : 15px;
	border: 0;
	padding:10px 172px;
}

#signup {
	background-color:white;
	color: teal;
	border: 0;
	font-size: 17px;
}
p {
	text-align: center;
}

i {
	color: lightgray;
}
#imail {
	position: absolute;
	top: 130px;
	margin: 0 355px;	
}

#ipw {
	position: absolute;
	top: 180px;
	margin: 0 355px;
}

input {
	border: 1px solid lightgray;
	border-radius : 10px;
}
</style>
</head>
<body>
<!--  sessionID 라는 이름으로 저장된 아이디 값이 존재 하면, 알림창을 띄운 후 home.do 주소를 호출
	  HomeController를 통해 메인 페이지인 index.jsp 페이지로 이동한다.
 -->
	<c:if test="${ sessionID != null} ">
		<script>
			alert("이미 로그인 중입니다.");
			location.href="home.do";
		</script>
	</c:if>
	
	<c:if test="${ loginResult == -1 || loginResult == 0 }">
		<script>
			alert("아이디 혹은 비밀번호가 틀렸습니다.");
		</script>
	</c:if>
	<div class="container">
		<div id="imail">
			<i class="material-icons">person_outline</i>
		</div>
		
		<div id="ipw">
			<i class="material-icons">lock_outline</i>
		</div>
			<h5><span>로그인</span> 페이지입니다.</h5>
			<hr />
			
<!-- <input> 태그에서 각각의 name속성으로 id,pw를 주고 <form> 태그의 action 속성이 가리키는
	  login.do로 입력한 파라미터를 전송하게 했다. login.do는 web.xml에서 작설 할 url 패턴.
 -->
			<form action="login.do" method="post">
				<input type="text" placeholder="ID" name="id" required style="height:30px; width: 380px;"><br />
				<input type="password" placeholder="password" name="pw" required style="height:30px; width:380px;">
				<input type="submit" value="로그인" class="login" />
				<button onclick="location.href='home.do'" class="login" >HOME</button>	
			</form>
			<hr />
			<p><a href="join.do"><input type="button" value="회원가입" id="signup"></a></p>
	</div>	
</body>
</html>