package board.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.service.BoardSrv;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardInsertCtrl
 */
@WebServlet("/boardInsert")
public class BoardInsertCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String realFolder = "";
	private final String saveFolder = "/fileSave";
	private final String encType = "UTF-8";
	private final int maxSize = 5 * 1024 * 1024;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardInsertCtrl() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		realFolder = getServletContext().getRealPath(saveFolder);
		
		System.out.println(realFolder);
		try {
			MultipartRequest mRequest = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			
			String fileName1 = "";
			Enumeration<?> files = mRequest.getFileNames();
			while(files.hasMoreElements()) {
				String name = (String)files.nextElement();
				String filename = mRequest.getFilesystemName(name);
				fileName1 = filename;
				String original = mRequest.getOriginalFileName(name);
				String type = mRequest.getContentType(name);
				File f1 = mRequest.getFile(name);
				if(f1==null) {
					System.out.println("파일 업로드 실패");
					fileName1 = "";
				} else {
					System.out.println(f1.length());
				}
				System.out.println(name);
				System.out.println(filename);
				System.out.println(original);
				System.out.println(type);
			}
			Board vo = new Board();
			vo.setBno(Integer.parseInt(mRequest.getParameter("bno")));
			vo.setBref(Integer.parseInt(mRequest.getParameter("bref")));
			vo.setBre_step(Integer.parseInt(mRequest.getParameter("bre_step")));
			vo.setBre_level(Integer.parseInt(mRequest.getParameter("bre_level")));
			
			vo.setBwriter(mRequest.getParameter("bwriter"));
			vo.setBsubject(mRequest.getParameter("bsubject"));
			vo.setBcontent(mRequest.getParameter("bcontent"));
			vo.setBfilepath(fileName1);
			vo.setBpwd(mRequest.getParameter("bpwd"));
			
			BoardSrv bService = new BoardSrv();
			int result = bService.writeBoard(vo);
			if(result>=1) {
				System.out.println("aaa");
				response.sendRedirect("boardList");
//				request.getRequestDispatcher("wWrite.jsp").forward(request, response);
			} else {
				System.out.println("bbb");
				request.getRequestDispatcher("wWrite.jsp").forward(request, response);
			}
		} catch(NumberFormatException e) {
			System.out.println("ccc");
			e.printStackTrace();
			request.getRequestDispatcher("wWrite.jsp").forward(request, response);
		} catch(NullPointerException e) {
			System.out.println("ddd");
			e.printStackTrace();
			request.getRequestDispatcher("wWrite.jsp").forward(request, response);
		}
	}

}
