package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectEx {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		//"jdbc:oracle:thin:@접속할ip위치:포트/오라클버전?"
		String user = "proj";
		try {
			Class.forName("oracle.jdbc.OracleDriver"); //jdbc driver check
			Connection conn = DriverManager.getConnection(url,user,user); //db 계정 connection
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT user_id, user_name, user_birth FROM tbl_users");
			//set-> 결과(쿼리)를 여러개 저장 가능
			while(rs.next()) { //가져온 쿼리의 결과(튜플)를 루핑
				System.out.println(rs.getString("user_id") 
						+", "+ rs.getString("user_name") +", "+ rs.getDate("user_birth"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {			
			System.out.println("end of data");
		}
	}
}
