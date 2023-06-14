package com.yedam.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.Dao;

public class BoardDao {
	Connection conn;
	PreparedStatement pst = null;
	ResultSet rs;
	
	public boolean insert(BoardVO vo) {
		String sql = "INSERT INTO tbl_board(brd_no, brd_title, brd_writer, brd_content, create_date)"
				  + " VALUES (board_seq.nextval, ?, ?, ?, ?)";
		conn = Dao.getConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, vo.getTitle());
			pst.setString(2, vo.getWriter());
			pst.setString(3, vo.getContent());
			pst.setString(4, vo.getDate());
			
			if( pst.executeUpdate() > 0)
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	
	public boolean delete(int no) {
		String sql = "DELETE FROM tbl_board WHERE brd_no=?";
		conn = Dao.getConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, no);
			if( pst.executeUpdate() > 0)
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	

	public boolean update(int no, String content) {
		String sql = "UPDATE tbl_board SET brd_content=? WHERE brd_no=?";
		conn = Dao.getConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, content);
			pst.setInt(2, no);
			if( pst.executeUpdate() > 0)
				return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	
	public List<BoardVO> showAll() {
		List<BoardVO> list = new ArrayList<>();
		String sql = "SELECT * FROM tbl_board";
		conn = Dao.getConnect();
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs == null)
				return null;
			while(rs.next()) {
				BoardVO vo = new BoardVO(rs.getInt("brd_no"),rs.getString("brd_title"),rs.getString("brd_writer"),
						rs.getString("brd_content"), rs.getString("create_date"), rs.getInt("click_cnt"));
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	
	public BoardVO showOne(int no) {
		BoardVO vo = new BoardVO();
		String sql = "SELECT * FROM tbl_board WHERE brd_no = ?";
		conn = Dao.getConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, no);
			rs = pst.executeQuery();
			if(rs == null)
				return null;
			while(rs.next()) {
				vo.setNo(rs.getInt("brd_no"));
				vo.setTitle(rs.getString("brd_title"));
				vo.setWriter(rs.getString("brd_writer"));
				vo.setContent(rs.getString("brd_content"));
				vo.setDate(rs.getString("create_date"));
				vo.setCount(rs.getInt("click_cnt"));
				//조회수 수정
			}
			countup(no);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	
	public void countup(int no) {
		String sql = "UPDATE tbl_board SET click_cnt = click_cnt+1 WHERE brd_no=?";
		conn = Dao.getConnect();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, no);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public void close() {
		try {
			if(rs != null)
				rs.close();
			if(pst != null)
				pst.close();
			if(conn != null)
				conn.close();			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
