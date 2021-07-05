package com.tech.spring_tx_board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.tech.spring_tx_board.dto.BoardDto;
import com.tech.spring_tx_board.util.Constant;

public class BoardDao {

	DataSource dataSource;
	
	JdbcTemplate template=null;
	
	public BoardDao() {
		System.out.println("db connnnnnn");
		try {
			Context context=new InitialContext();
			dataSource=(DataSource) context.lookup("java:comp/env/jdbc/springorcl");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
//		template=Constant.template;	
	}
	public ArrayList<BoardDto> list() {
//		ArrayList<BoardDto> dtos=new ArrayList<BoardDto>();
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//			con=dataSource.getConnection();
//			String sql="select bid,bname,btitle,bcontent,"
//					+ "bdate,bhit,bgroup,bstep," + 
//					"bindent from mvc_board order by bgroup desc,"
//					+ " bstep asc";
//			pstmt=con.prepareStatement(sql);
//			rs=pstmt.executeQuery();
//			
//			while(rs.next()) {
//				int bid=rs.getInt("bid");
//				String bname=rs.getString("bname");
//				String btitle=rs.getString("btitle");
//				String bcontent=rs.getString("bcontent");
//				
//				Timestamp bdate=rs.getTimestamp("bdate");
//				
//				int bhit=rs.getInt("bhit");
//				int bgroup=rs.getInt("bgroup");
//				int bstep=rs.getInt("bstep");
//				int bindent=rs.getInt("bindent");
//				
//				BoardDto dto=new BoardDto(bid, bname, btitle, 
//						bcontent, bdate, bhit, bgroup, bstep, bindent);
//				dtos.add(dto);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(rs!=null) rs.close();
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			} catch (Exception e2) {
//			}
//		}
//		return dtos;
		return null;
	}

	public void write(String bName, String bTitle, String bContent) {
		System.out.println("dao write~~~~~~"+bName);
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		
//		try {
//			con=dataSource.getConnection();
//			String sql="insert into mvc_board " + 
//					"values(mvc_board_seq.nextval,?,?,?," + 
//					"sysdate,0,MVC_BOARD_SEQ.currval,0,0)";
//			pstmt=con.prepareStatement(sql);
//			pstmt.setString(1, bName);
//			pstmt.setString(2, bTitle);
//			pstmt.setString(3, bContent);
//			
//			int rn=pstmt.executeUpdate();
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			} catch (Exception e2) {
//			}
//		}
	}

	public BoardDto contentView(String sbid) {
//		upHit(sbid);
		
		BoardDto dto=null;
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//			con = dataSource.getConnection();
//			String sql = "select * from mvc_board where bid=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, Integer.parseInt(sbid));
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				int bid=rs.getInt("bid");
//				String bname=rs.getString("bname");
//				String btitle=rs.getString("btitle");
//				String bcontent=rs.getString("bcontent");
//				
//				Timestamp bdate=rs.getTimestamp("bdate");
//				
//				int bhit=rs.getInt("bhit");
//				int bgroup=rs.getInt("bgroup");
//				int bstep=rs.getInt("bstep");
//				int bindent=rs.getInt("bindent");
//				
//				dto=new BoardDto(bid, bname, btitle, 
//						bcontent, bdate, bhit, bgroup, 
//						bstep, bindent);
//			}
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			try {
//				if(rs!=null) rs.close();
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			} catch (Exception e2) {
//			}
//		}
		return dto;
		
	}

	private void upHit(String sbid) {
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		try {
//			con = dataSource.getConnection();
//			String sql = "update mvc_board set bhit=bhit+1 where bid=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, Integer.parseInt(sbid));
//			pstmt.executeUpdate();
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			} catch (Exception e2) {
//			}
//		}
		
	}

	public void modify(String bid, String bName,
			String bTitle, String bContent) {
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		try {
//			con = dataSource.getConnection();
//			String sql = "update mvc_board set bname=?,"
//					+ "btitle=?,bcontent=? where bid=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, bName);
//			pstmt.setString(2, bTitle);
//			pstmt.setString(3, bContent);
//			pstmt.setInt(4, Integer.parseInt(bid));
//			pstmt.executeUpdate();
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			} catch (Exception e2) {
//			}
//		}
		
	}

	public void delete(String bid) {
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		try {
//			con = dataSource.getConnection();
//			String sql = "delete from mvc_board where bid=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, Integer.parseInt(bid));
//			pstmt.executeUpdate();
//		}catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			} catch (Exception e2) {
//			}
//		}
	}

	public BoardDto replyView(String sbid) {
		BoardDto dto=null;
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//			con = dataSource.getConnection();
//			String sql = "select * from mvc_board where bid=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, Integer.parseInt(sbid));
//			rs = pstmt.executeQuery();
//			
//			if (rs.next()) {
//				int bid=rs.getInt("bid");
//				String bname=rs.getString("bname");
//				String btitle=rs.getString("btitle");
//				String bcontent=rs.getString("bcontent");
//				
//				Timestamp bdate=rs.getTimestamp("bdate");
//				
//				int bhit=rs.getInt("bhit");
//				int bgroup=rs.getInt("bgroup");
//				int bstep=rs.getInt("bstep");
//				int bindent=rs.getInt("bindent");
//				
//				dto=new BoardDto(bid, bname, btitle, 
//						bcontent, bdate, bhit, bgroup, 
//						bstep, bindent);
//			}
//	
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			try {
//				if(rs!=null) rs.close();
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			} catch (Exception e2) {
//			}
//		}
		return dto;
	}

	public void reply(String bid, String bName, String bTitle,
			String bContent, String bgroup, 
			String bstep,String bindent) {
//		replyShape(bgroup,bstep);
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = dataSource.getConnection();
//			String sql = "insert into mvc_board(bid,bname,btitle,"
//					+ "bcontent,bgroup,bstep,bindent) "
//					+ "values(mvc_board_seq.nextval,?,?,?,?,?,?)";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, bName);
//			pstmt.setString(2, bTitle);
//			pstmt.setString(3, bContent);
//			
//			pstmt.setInt(4,Integer.parseInt(bgroup));
//			pstmt.setInt(5,Integer.parseInt(bstep)+1);
//			pstmt.setInt(6,Integer.parseInt(bindent)+1);
//			
//			pstmt.executeQuery();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			} catch (Exception e2) {
//			}
//		}

	}

	private void replyShape(String bgroup, String bstep) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = dataSource.getConnection();
//			String sql = "update mvc_board set bstep=bstep+1 "
//					+ "where bgroup=? and bstep>?";
//			pstmt=con.prepareStatement(sql);
//			pstmt.setInt(1, Integer.parseInt(bgroup));
//			pstmt.setInt(2, Integer.parseInt(bstep));
//			pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();
//			} catch (Exception e2) {
//			}
//		}
		
	}

}
