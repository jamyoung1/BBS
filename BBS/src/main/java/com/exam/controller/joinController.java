package com.exam.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.dao.MemberDAO;
import com.exam.dto.MemberDTO;

public class joinController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/signUp.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// post 방식으로 접근하여 이 메서드가 실행 될 경우 signUp.jsp 에서 가져온 파라미터 값들을 DTO에 setter를 이용하여 넣고
		// dao에서 생성한 join 메서드 안에 파라미터 값을 넣은 dto를 입력하였다.
		
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		dto.setEmail(email);
		int joinResult = dao.join(dto);
		
		// join 메서드의 sql 문이 정상 수행 되었을 경우 1이 리턴되고, 수행이 되지 않았을 때 0이 리턴 된다.
		// 1이 리턴되면 db에 입력한 값이 저장되었는 것이고, 이 떄 setAttribute 메서드로 request 객체의
		// Attribute에 "joinResult"라는 이름의 값을 1로 하고 세션에도 "sessionID" 라는 이름으로 
		// 입력한 아이디 값을 저장하여 메인 화면인 index.jsp 페이지로 이동한다.
		// 0이 리턴되면 request객체의 Attribute에 "joinResult" 라는 이름의 값을 0으로하여 다시 signUp.jsp페이지로 이동
		if(joinResult == 1) {
			req.setAttribute("joinResult", joinResult);
			HttpSession session = req.getSession();
			session.setAttribute("sessionID", id);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(req, resp);		
		}else {
			req.setAttribute("joinResult", 0);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/signUp.jsp");
			rd.forward(req, resp);
		}
	}

}
