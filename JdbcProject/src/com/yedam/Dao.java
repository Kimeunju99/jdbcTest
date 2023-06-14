package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;

//db 접속 Connection return
public class Dao {
	static String url = "jdbc:oracle:thin:@localhost:1521/xe";
	static String user = "proj";
	static Connection conn = null;
	
	public static Connection getConnect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, user);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			return conn;			
		}
	}
}
