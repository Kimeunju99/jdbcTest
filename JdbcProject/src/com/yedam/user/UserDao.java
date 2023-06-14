package com.yedam.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.Dao;

//insert, update, delete, select
public class UserDao {
	Connection conn;
	PreparedStatement pstat;
	ResultSet rs;
	String sql = "";
	
	private void close() {
		try {
			if(rs != null)
				rs.close();
			if(pstat != null)
				pstat.close();
			if(conn != null)
				conn.close();			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean add(UserVO user) {//insert
		sql = "INSERT INTO tbl_users VALUES (?,?,?,?,?,?)";
		conn = Dao.getConnect();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getId());
			pstat.setString(2, user.getPw());
			pstat.setString(3, user.getName());
			pstat.setString(4, user.getBirth());
			pstat.setString(5, user.getPhone());
			pstat.setString(6, user.getAddr());
			int r = pstat.executeUpdate();
			if(r>0) {
				return true;				
			}else {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	
	public UserVO search(String userId) {//select
		sql = "SELECT * FROM tbl_users WHERE user_id=?";
		conn = Dao.getConnect();
//		conn.setAutoCommit(false); //자동커밋 방지
//		conn.commit();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, userId);
			rs = pstat.executeQuery();
			if(rs.next()) {
				UserVO user = new UserVO();
				user.setId(rs.getString("user_id"));
				user.setPw(rs.getString("user_pw"));
				user.setName(rs.getString("user_name"));
				user.setBirth(rs.getString("user_birth"));
				user.setPhone(rs.getString("user_phone"));
				user.setAddr(rs.getString("user_addr"));
				return user;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return null;
	}
	
	public boolean modify(UserVO user) {//update
		sql = "UPDATE tbl_users SET user_pw=nvl(?, user_pw), user_name=nvl(?, user_name), "
				+ "user_birth=nvl(?, user_birth), user_phone=nvl(?, user_phone), user_addr=nvl(?, user_addr)"
			+ " WHERE user_id=?";
		conn = Dao.getConnect();
		try{
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getPw());
			pstat.setString(2, user.getName());
			pstat.setString(3, user.getBirth());
			pstat.setString(4, user.getPhone());
			pstat.setString(5, user.getAddr());
			pstat.setString(6, user.getId());
			int r = pstat.executeUpdate();
			if(r > 0)
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	
	public boolean remove(String id) {//delete
		sql = "DELETE tbl_users WHERE user_id=?";
		conn = Dao.getConnect();
		try {
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			int r = pstat.executeUpdate();
			if(r >0)
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	
	public List<UserVO> list() {
		List<UserVO> list = new ArrayList<>();
		sql = "SELECT * FROM tbl_users";
		conn = Dao.getConnect();
		try {
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			if(rs == null)
				return null;
			while(rs.next()) {
				UserVO user = new UserVO();
				user.setId(rs.getString("user_id"));
				user.setPw(rs.getString("user_pw"));
				user.setName(rs.getString("user_name"));
				user.setBirth(rs.getString("user_birth"));
				user.setPhone(rs.getString("user_phone"));
				user.setAddr(rs.getString("user_addr"));
				
				list.add(user);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	public boolean Login(String id, String pw) {
		UserDao dao = new UserDao();
		UserVO user = dao.search(id);
		if(user != null && user.getId().equals(id) && user.getPw().equals(pw))
			return true;
		else
			return false;
	}
}
