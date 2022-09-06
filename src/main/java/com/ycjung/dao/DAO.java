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
import javax.xml.transform.Templates;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.ycjung.dto.DTO;
import com.ycjung.util.Constant;

public class DAO {
	
	DataSource ds;
	
	JdbcTemplate template = null;
	
	public DAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		template = Constant.template;
	}
	
	public ArrayList<DTO> list() {
	     
	    ArrayList<DTO> dtos = null;
	    
	    String query = "SELECT bCd, bTitle, bAuthor, bDate, bContent, bHits, bGroup, bStep, bIndent FROM mvc_board ORDER BY bGroup desc, bStep asc";
	    dtos = (ArrayList<DTO>) template.query(query, new BeanPropertyRowMapper<DTO>(DTO.class));
	    return dtos;
	}
	
	public DTO contentView(int Cd) {
	    
		upHits(Cd);
		
		String query = "SELECT * FROM mvc_board WHERE bCd = "+Cd;
		return template.queryForObject(query, new BeanPropertyRowMapper<DTO>(DTO.class));
	}
	
	public void upHits(final int Cd) {
	    
	    String query = "UPDATE mvc_board SET bHits = bHits + 1 WHERE bCd = ?";
		template.update(query, new PreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                // TODO Auto-generated method stub
                ps.setInt(1, Cd);
            }
        });
	}
	
	public void write(final String bAuthor, final String bTitle, final String bContent) {
	     
	    template.update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // TODO Auto-generated method stub
                String query = "INSERT INTO mvc_board (bCd, bTitle, bAuthor, bContent, bHits, bGroup, bStep, bIndent) "
                        + "VALUES(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)"; 
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, bTitle);
                pstmt.setString(2, bAuthor);
                pstmt.setString(3, bContent);
                return pstmt;
            }
        });
	}
	
	public void modify(final int bCd, final String bAuthor, final String bTitle, final String bContent) {
	    String query = "UPDATE mvc_board SET bAuthor=?, bTitle=?, bContent=?, bDate=? WHERE bCd=?";
	    template.update(query,new PreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                // TODO Auto-generated method stub
                ps.setString(1, bAuthor);
                ps.setString(2, bTitle);
                ps.setString(3, bContent);
                ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                ps.setInt(5, bCd);
                
            }
        });
	}
	
	public void delete(final int bCd) {
	    String query = "DELETE FROM mvc_board WHERE bCd=?";
	    
		template.update(query, new PreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                // TODO Auto-generated method stub
                ps.setInt(1, bCd);
            }
        });
	}
	
	public void dummyDataInsert() {
	    String query = "INSERT INTO mvc_board (bCd, bTitle, bAuthor, bContent, bHits, bGroup, bStep, bIndent) "
                + "VALUES(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)"; 
	    
	    for(int i=0;i<101;i++) {
	        template.update(new PreparedStatementCreator() {
	            
	            @Override
	            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	                // TODO Auto-generated method stub
	                String query = "INSERT INTO mvc_board (bCd, bTitle, bAuthor, bContent, bHits, bGroup, bStep, bIndent) "
	                        + "VALUES(mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)"; 
	                PreparedStatement pstmt = con.prepareStatement(query);
	                pstmt.setString(1, "임시제목");
	                pstmt.setString(2, "홍길동");
	                pstmt.setString(3, "임시줄거리");
	                return pstmt;
	            }
	        });
	    }

	}
	
    public void deleteAll() {
        String query = "TRUNCATE TABLE mvc_board"; 
        template.update(query);
    }
}
