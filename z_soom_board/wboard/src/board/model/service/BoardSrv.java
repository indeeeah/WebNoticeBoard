package board.model.service;

import static common.jdbcDriver.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.model.dao.BoardDAO;
import board.model.vo.Board;

public class BoardSrv {

	public int getBoardCount() {
		Connection conn = getConnection();
		int result = new BoardDAO().getBoardCount(conn);
		close(conn);
		return result;
	}

	public List<Board> getBoardAll() {
		Connection conn = getConnection();
		List<Board> result = new BoardDAO().getBoardAll(conn);
		close(conn);
		return result;
	}

	public List<Board> getBoardByPage(int start, int end) {
		Connection conn = getConnection();
		List<Board> result = new BoardDAO().getBoardByPage(conn, start, end);
		close(conn);
		return result;
	}

	public List<Board> getBoard(int bno) {
		Connection conn = getConnection();
		List<Board> result = new BoardDAO().getBoard(conn, bno);
		close(conn);
		return result;
	}

	public int writeBoard(Board vo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			conn.setAutoCommit(false);
			result = new BoardDAO().writeBoard(conn, vo);
			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}
		return result;
	}
}
