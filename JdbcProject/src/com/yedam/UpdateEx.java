package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateEx {
	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "proj";
		Connection conn = null;
		PreparedStatement pstat = null; //쿼리에 입력값을 전달하는 방식이 Statement와 다름
		
		System.out.print("set pw>");
		String pw = scan.nextLine();
		System.out.print("set addr>");
		String addr = scan.nextLine();
		System.out.print("update id>");
		String id = scan.nextLine();
		
		
		String sql = "UPDATE tbl_users "
					+"SET user_pw=?, user_addr=?"
					+"WHERE user_id=?";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, user);
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pw);
			pstat.setString(2, addr);
			pstat.setString(3, id);
			int r = pstat.executeUpdate();
			
			if(r>0) {	System.out.println("처리 성공");
			}else {		System.out.println("처리 실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstat.close();
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			System.out.println("system exit");
		}
		
	}
}
