package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardSrv;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardUpadateCtrl
 */
@WebServlet("/boardUpdate")
public class BoardUpadateCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardUpadateCtrl() {
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
		
		try {
			Board vo = new Board();
			vo.setBno(Integer.parseInt(request.getParameter("bno")));
			vo.setBref(Integer.parseInt(request.getParameter("bref")));
			vo.setBre_step(Integer.parseInt(request.getParameter("bre_step")));
			vo.setBre_level(Integer.parseInt(request.getParameter("bre_level")));
			
			vo.setBwriter(request.getParameter("bwriter"));
			vo.setBsubject(request.getParameter("bsubject"));
			vo.setBcontent(request.getParameter("bcontent"));
			vo.setBfilepath(request.getParameter("bfilepath"));
			vo.setBpwd(request.getParameter("bpwd"));
			
			BoardSrv bService = new BoardSrv();
			int result = bService.writeBoard(vo);
			if(result>1) {
				response.sendRedirect("boardList");
			} else {
				request.getRequestDispatcher("wWrite.jsp").forward(request, response);
			}
		} catch(NumberFormatException e) {
			request.getRequestDispatcher("wWrite.jsp").forward(request, response);
		} catch(NullPointerException e) {
			request.getRequestDispatcher("wWrite.jsp").forward(request, response);
		}
	}

}
