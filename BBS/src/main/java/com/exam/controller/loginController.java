package com.exam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.dao.MemberDAO;

public class loginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
		rd.forward(req, resp);
	}
    // get 방식으로 loginController에 접근했을 떄, doGet메서드 호출.
	// 로그인 페이지인 login.jsp 페이지를 보여주는 역할만 한다.
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// login,jsp 페이지에서 파라미터들이 <form>태그에 의해 post 방식으로 보내진다.
		// doPost 메서드가 호출 된다. 전달받은 파라미터 값들을 dao에서 선언한 login 메서드의 매개값으로 입력한다.
		// login메서드에 입력된 id, pw 값이 db에 저장되어있는 값과 같으면 리턴값이 1
		// 이 경우 request에 'loginResult'라는 이름으로 리턴 값을 저장하고, 
		// 세션에 "sessionID" 라는 이름으로 아이디 값을 저장하여 index.jsp 페이지로 이동한다.
		
		
		// 리턴 값이 1이 아닌 경우 아이디or 비밀번호가 다르므로 로그인처리 x
		// 기턴 값을 request에 'loginResult' 라는 이름으로 저장 후 다시 login.jsp 페이지로 이동한다.
		
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance();
		int loginResult = dao.login(id, pw);
		
		if(loginResult == 1) {
			req.setAttribute("loginResult", loginResult);
			HttpSession session = req.getSession();
			session.setAttribute("sessionID", id);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(req, resp);
		}else {
			req.setAttribute("loginResult", loginResult);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/login.jsp");
			rd.forward(req, resp);		
		}
	}
}
