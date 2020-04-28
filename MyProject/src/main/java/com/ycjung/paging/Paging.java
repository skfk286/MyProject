package com.ycjung.paging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Paging {
	
    DataSource ds;
    
    public Paging() {
        try {
            Context context = new InitialContext();
            ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public PagingVo membersAboutList() {
		PagingVo pv = new PagingVo();
	    Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int rowCnt = 0;
        try {
            conn = ds.getConnection();
            String query = "SELECT COUNT(*) FROM mvc_board";
            psmt = conn.prepareStatement(query);
            rs = psmt.executeQuery();
            
            if(rs.next()) {
                rowCnt = rs.getInt(1);
            }
//            ResultSetMetaData rsmd = null;
//            rsmd = rs.getMetaData();
//            colCnt = rsmd.getColumnCount();
            
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
        pv.setTotalCount(rowCnt); // 전체 게시글 수
        pv.setTotalPage(rowCnt / pv.getDisplayRow()); // 전체 페이지 수
        return pv;
	}
}
