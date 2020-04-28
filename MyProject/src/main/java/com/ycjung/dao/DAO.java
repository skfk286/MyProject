package com.ycjung.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.ycjung.dto.DTO;

public class DAO {
	
	DataSource ds;
	
	public DAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<DTO> list() {
		ArrayList<DTO> dtos = new ArrayList<DTO>();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String query = "SELECT bCd, bTitle, bAuthor, bDate, bContent, bHits, bGroup, bStep, bIndent FROM mvc_board ORDER BY bGroup desc, bStep asc"; 
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int bCd = rs.getInt("bCd");
				String bTitle = rs.getString("bTitle");
				String bAuthor = rs.getString("bAuthor");
				Timestamp bDate = rs.getTimestamp("bDate");
				String bContent = rs.getString("bContent");
				int bHits = rs.getInt("bHits");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				DTO dto = new DTO(bCd, bTitle, bAuthor, bDate, bContent, bHits, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(rs != null)
						rs.close();
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return dtos;
	}
	
	public DTO contentView(int Cd) {
		upHits(Cd);
		DTO dto = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String query = "SELECT * FROM mvc_board WHERE bCd = ?";
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, Cd);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int bCd = rs.getInt("bCd");
				String bTitle = rs.getString("bTitle");
				String bAuthor = rs.getString("bAuthor");
				Timestamp bDate = rs.getTimestamp("bDate");
				String bContent = rs.getString("bContent");
				int bHits = rs.getInt("bHits");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				dto = new DTO(bCd, bTitle, bAuthor, bDate, bContent, bHits, bGroup, bStep, bIndent);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
					if(rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return dto;
	}
	
	public void upHits(int Cd) {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = ds.getConnection();
			String query = "UPDATE mvc_board SET bHits = bHits + 1 WHERE bCd = ?";
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, Cd);
			int r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void write(String bAuthor, String bTitle, String bContent) {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = ds.getConnection();
			String query = "INSERT INTO mvc_board (bCd, bTitle, bAuthor, bContent, bHits, bGroup, bStep, bIndent) "
					+ "VALUES(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)"; 
			psmt = conn.prepareStatement(query);
			psmt.setString(1, bTitle);
			psmt.setString(2, bAuthor);
			psmt.setString(3, bContent);
			int result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void modify(int bCd, String bAuthor, String bTitle, String bContent) {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = ds.getConnection();
			String query = "UPDATE mvc_board SET bAuthor=?, bTitle=?, bContent=?, bDate=? WHERE bCd=?"; 
			psmt = conn.prepareStatement(query);
			psmt.setString(1, bAuthor);
			psmt.setString(2, bTitle);
			psmt.setString(3, bContent);
			psmt.setTimestamp(4, new Timestamp(new GregorianCalendar().getTimeInMillis()));
			psmt.setInt(5, bCd);
			int result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void delete(int bCd) {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = ds.getConnection();
			String query = "DELETE FROM mvc_board WHERE bCd=?"; 
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, bCd);
			int result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void dummyDataInsert() {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = ds.getConnection();
			for (int i = 0; i < 101; i++) {
				String query = "INSERT INTO mvc_board (bCd, bTitle, bAuthor, bContent, bHits, bGroup, bStep, bIndent) "
						+ "VALUES(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)"; 
				psmt = conn.prepareStatement(query);
				psmt.setString(1, "임시제목"+(i+1));
				psmt.setString(2, "홍길동"+(i+1));
				psmt.setString(3, "임시글"+(i+1));
				int result = psmt.executeUpdate();
			}
			System.out.println("dummy data insert success...");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public void	deleteAll() {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = ds.getConnection();
			String query = "TRUNCATE TABLE mvc_board"; 
			psmt = conn.prepareStatement(query);
			int result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public int allPageCountJoin() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int rowCnt = 0;
		try {
			conn = ds.getConnection();
			String query = "SELECT count(*) FROM mvc_board";
			psmt = conn.prepareStatement(query);
			rs = psmt.executeQuery();
			
			ResultSetMetaData rsmd = null;
			rsmd = rs.getMetaData();
			rowCnt = rsmd.getColumnCount();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
					if(rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return rowCnt;
	}
	
}
