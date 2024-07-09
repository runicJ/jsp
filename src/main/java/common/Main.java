package common;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.database.LoginDAO;
import study.database.LoginVO;

@SuppressWarnings("serial")
@WebServlet("/Main")
public class Main extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	// 초기화면에 상품이미지(title image)를 랜덤하게 올려주기
		int mainImage1 = (int) (Math.random()*(120-111+1)) + 111;	// (int) (Math.random()*(max-min+1)) + min
		int mainImage2 = (int) (Math.random()*(120-111+1)) + 111;
		int mainImage3 = (int) (Math.random()*(120-111+1)) + 111;
		int mainImage4 = (int) (Math.random()*(120-111+1)) + 111;
		int mainImage5 = (int) (Math.random()*(120-111+1)) + 111;
		request.setAttribute("mainImage1", mainImage1);
		request.setAttribute("mainImage2", mainImage2);
		request.setAttribute("mainImage3", mainImage3);
		request.setAttribute("mainImage4", mainImage4);
		request.setAttribute("mainImage5", mainImage5);
		
		LoginDAO dao = new LoginDAO();
		
		ArrayList<LoginVO> recentVos = dao.getRecentFiveMember();
		
		request.setAttribute("recentVos", recentVos);
		
		String viewPage = "/main/main.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);;
	}
}
