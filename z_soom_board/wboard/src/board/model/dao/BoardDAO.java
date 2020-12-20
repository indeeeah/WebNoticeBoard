package board.model.dao;

import static common.jdbcDriver.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import board.model.vo.Board;

public class BoardDAO {
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public int getBoardCount(Connection conn) {
		int cnt = 0;
		String sql = "select count(*) from d_board";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return cnt;
	}

	public List<Board> getBoardAll(Connection conn) {
		List<Board> list = new ArrayList<Board>();
		String sql = "select * from d_board order by bref desc, bre_step asc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Board vo = new Board();
					vo.setBno(rs.getInt("bno"));
					vo.setBcnt(rs.getInt("bcnt"));
					vo.setBcontent(rs.getString("bcontent"));
					vo.setBdate(rs.getTimestamp("bdate"));
					vo.setBfilepath(rs.getString("bfilepath"));
					vo.setBsubject(rs.getString("bsubject"));
					vo.setBwriter(rs.getString("bwriter"));
					vo.setBref(rs.getInt("bref"));
					vo.setBre_step(rs.getInt("bre_step"));
					vo.setBre_level(rs.getInt("bre_level"));
					list.add(vo);
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public List<Board> getBoardByPage(Connection conn, int start, int end) {
		List<Board> list = new ArrayList<Board>();
		String sql = "select * from (select ROWNUM rnum, d.* from (select * from d_board order by bref desc, bre_step asc) d) where rnum>=? and rnum <=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Board vo = new Board();
					vo.setBno(rs.getInt("bno"));
					vo.setBcnt(rs.getInt("bcnt"));
					vo.setBcontent(rs.getString("bcontent"));
					vo.setBdate(rs.getTimestamp("bdate"));
					vo.setBfilepath(rs.getString("bfilepath"));
					vo.setBsubject(rs.getString("bsubject"));
					vo.setBwriter(rs.getString("bwriter"));
					vo.setBref(rs.getInt("bref"));
					vo.setBre_step(rs.getInt("bre_step"));
					vo.setBre_level(rs.getInt("bre_level"));
					list.add(vo);
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public List<Board> getBoard(Connection conn, int bno) {
		List<Board> list = new ArrayList<Board>();
		String sql = "select * from d_board where bno=" + bno;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Board vo = new Board();
					vo.setBno(rs.getInt("bno"));
					vo.setBcnt(rs.getInt("bcnt"));
					vo.setBcontent(rs.getString("bcontent"));
					vo.setBfilepath(rs.getString("bfilepath"));
					vo.setBsubject(rs.getString("bsubject"));
					vo.setBwriter(rs.getString("bwriter"));
					vo.setBpwd(rs.getString("bpwd"));
					vo.setBref(rs.getInt("bref"));
					vo.setBre_step(rs.getInt("bre_step"));
					vo.setBre_level(rs.getInt("bre_level"));
					list.add(vo);
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int writeBoard(Connection conn, Board vo) {
		int result = 0;
		String bwriter = vo.getBwriter();
		String bsubject = vo.getBsubject();
		String bcontent = vo.getBcontent();
		String bfilepath = vo.getBfilepath();
		String bpwd = vo.getBpwd();

		int bno = vo.getBno();
		int bref = vo.getBref();
		int bre_step = vo.getBre_step();
		int bre_level = vo.getBre_level();

		String sql_max = "select nvl(max(bno), 0) from d_board";
		int maxBno = 0;
		try {
			pstmt = conn.prepareStatement(sql_max);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				maxBno = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		try {
			if (bno == 0) {
				bref = maxBno;
			} else {
				String sql_update = "update d_board set bre_step=bre_step+1 where bref=? and bno<>bref and bre_step>?";
				System.out.println(bno + " : " + bref + " : " + bre_step + " : " + bre_level);
				pstmt = conn.prepareStatement(sql_update);
				pstmt.setInt(1, bref);
				pstmt.setInt(2, bre_step);
				result = pstmt.executeUpdate();
				close(pstmt);
				bre_step++;
				bre_level++;
			}

			String sql = "insert into d_board values (?,?,?,current_timestamp,0,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxBno);
			pstmt.setInt(8, bref);
			pstmt.setInt(9, bre_step);
			pstmt.setInt(10, bre_level);
			pstmt.setString(2, bsubject);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bwriter);
			pstmt.setString(5, bwriter);
			pstmt.setString(6, bfilepath);
			pstmt.setString(7, bpwd);
			result = pstmt.executeUpdate();
			if (result < 1) {
				System.out.println("insert 실패!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}
}
