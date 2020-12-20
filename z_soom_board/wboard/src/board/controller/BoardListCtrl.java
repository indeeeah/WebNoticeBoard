package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardSrv;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardListCtrl
 */
@WebServlet("/boardList")
public class BoardListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardListCtrl() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		int pageSize = 3;
		int pageBlock = 2;

		try {
			BoardSrv bService = new BoardSrv();
			int bCount = bService.getBoardCount();

			String pageNum = request.getParameter("pageNum");
			if (pageNum == null) {
				pageNum = "1";
			}
			int currentPage = Integer.parseInt(pageNum);

			int pageCount = (bCount / pageSize) + (bCount % pageSize == 0 ? 0 : 1);

			int startPage = 1;
			int endPage = 1;

			if (currentPage % pageBlock == 0) {
				startPage = ((currentPage / pageBlock) - 1) * pageBlock + 1;
			} else {
				startPage = ((currentPage / pageBlock)) * pageBlock + 1;
			}

			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}

			int startRnum = (currentPage - 1) * pageSize + 1;
			int endRnum = startRnum + pageSize - 1;
			List<Board> list = bService.getBoardByPage(startRnum, endRnum);

			if (list != null) {
				System.out.println("aaaaaa");
				request.setAttribute("list", list);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("bCount", bCount);
				request.getRequestDispatcher("wList.jsp").forward(request, response);
				;
			} else {
				System.out.println("bbbbbb");
				request.setAttribute("errMsg", "작성된 글이 없습니다");
				request.getRequestDispatcher("wList.jsp").forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("ccccccc");
			request.setAttribute("errMsg", "잠시 후 다시 확인해주세요");
			request.getRequestDispatcher("wList.jsp").forward(request, response);
		}
	}
}
