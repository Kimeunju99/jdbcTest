package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteEx {
	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		Connection conn = Dao.getConnect();
		PreparedStatement pstat = null;
		
		System.out.print("delete id>");
		String id = scan.nextLine();
		String sql = "DELETE tbl_users WHERE user_id=?";
		
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			int r = pstat.executeUpdate();
			
			if(r > 0) {	System.out.println("처리 성공");
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
