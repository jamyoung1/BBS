package com.exam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.exam.dto.MemberDTO;

public class MemberDAO {

	private static MemberDAO dao;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int result;
	
private MemberDAO() {
		
	}

	public static synchronized MemberDAO getInstance() {
		if (dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "book_ex", pw = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		if (pstmt != null) {try {pstmt.close();}catch (SQLException e) {e.printStackTrace();}}
		if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	
	public int login(String id, String pw) {
		// 입력받은 id 값을 select 문에 넣어, 입력받은 id 값이 db에 저장되어 있는 id 열과 
		// 같은 행을 찾아, 해당 행의 pw열의 값을 불러와 입력받은 pw값과 비교한 후 결과 리턴
		
		// 아이디와 비밀번호 값이 모두 같으면 1, 비밀번호가 다르면 0, 아이디가 다르면 -1을 리턴한다.		
		conn = this.getConnection();
		String sql = "select pw from mem_tbl where id = ? ";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pw").equals(pw)) {
					return 1;
				}else {
					return 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.close(conn,pstmt,rs);
		}
		return -1;
	}
	
	public int join(MemberDTO mdto) {
		// 매개 값으로 dto 클래스를 입력 받아 그 dto 클래스에 저장된 값들을 테이블에 추가해주는 역할
		conn = this.getConnection();
		String sql = "insert into mem_tbl values(?,?,?,?)";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPw());
			pstmt.setString(3, mdto.getName());
			pstmt.setString(4, mdto.getEmail());
			// 수행한 수를 리턴. 정상적으로 작동할 경우 result에 1이 저장되어, join 메서드의 값으로 리턴 된다.
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(conn, pstmt, null);
		}
		return result;
	}	
}