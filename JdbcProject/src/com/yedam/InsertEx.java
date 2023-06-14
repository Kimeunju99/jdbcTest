package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertEx {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "proj";
		Connection conn = null;
		Statement stat = null; //db로 쿼리를 보내기 위한 객체
		
		System.out.print("id>");
		String id = scan.nextLine();
		System.out.print("pw>");
		String pw = scan.nextLine();
		System.out.print("name>");
		String name = scan.nextLine();
		
		String sql ="INSERT INTO tbl_users (user_id, user_pw, user_name)"
				  + "VALUES ('"+id+"','"+pw+"','"+name+"')";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, user);
			stat = conn.createStatement();
			int r = stat.executeUpdate(sql); //dml로 처리된 튜플 수
			if(r>0) {
				System.out.println("처리 성공");
			}else {
				System.out.println("처리 실패");
			}
		}catch(Exception e) {
			System.out.println("에러 발생.");
			e.printStackTrace();
		}finally{
			try {
				stat.close();
				conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			System.out.println("end of prog");
		}
	}
}
