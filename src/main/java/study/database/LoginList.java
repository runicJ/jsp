package study.database;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guest.GuestVO;

@SuppressWarnings("serial")
@WebServlet("/study/database/LoginList")
public class LoginList extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginDAO dao = new LoginDAO();
		
//		ArrayList<LoginVO> vos = dao.getLoginList();  // 자료 가져오는 것  // 여러개 vos Array나 ArrayList
//		//System.out.println("vos : " + vos);  // vos의 자료가 다 나옴  // 확인 후에 주석 처리할 것
//		
//			request.setAttribute("sort", sort);
//		request.setAttribute("vos", vos);
		
		String sort = request.getParameter("sort")==null ? "idx" : request.getParameter("sort");  // 첫 페이지만 모르는 것, 누른 페이지는 알 수 있음 // 다음, 이전 누를때 +1 -1 하면 됨
		
		// 아래 값들을 나중엔 vo로 묶을 것
		// 1. 현재 페이지번호를 구해온다.(첫 화면은 무조건 1페이지=>페이징처리x(다른페이지 눌렀을때 페이지처리한 것)
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));  // 첫 페이지만 모르는 것, 누른 페이지는 알 수 있음 // 다음, 이전 누를때 +1 -1 하면 됨
		
		// 2. 한 페이지의 분량을 구한다.
		/* int pageSize = 5; */
		int pageSize = request.getParameter("pageSize")==null ? 3 : Integer.parseInt(request.getParameter("pageSize"));  // 3은 기본페이지
		if(pageSize > 3) pag = 1;  // 무조건 1페이지로 가는걸로
		
		// 3. 총 레코드 건수를 구한다.(sql명령어 중 count함수 이용)
		int totRecCnt = dao.getTotRecCnt();
		
		// 4. 총 페이지 건수를 구한다.
		int totPage = totRecCnt % pageSize==0 ? (totRecCnt/pageSize) : (totRecCnt/pageSize)+1;  // (totRecCnt/pageSize) => 나누고 값을 정수로
		
		// 5. 현재 페이지에서 출력할 '시작 인덱스 번호'를 구한다. => 내가 알 필요 없고, 기계가 필요함(vos에서 써먹음)
		int startIndexNo = (pag - 1) * pageSize;
		
		// 6. 현재 화면에 표시될 '시작 실제 게시글 번호'를 구한다.
		int curScrStartNo = totRecCnt - startIndexNo;

		// 한 페이지에 표시할 건수만(pageSize만큼)을 DAO에서 가져온다.
		ArrayList<LoginVO> vos = dao.getLoginList(sort, startIndexNo, pageSize);  // 기계가 알 수 있는건 인덱스번호  // 조건이 없음 다 가져와야 함  // 값이 여러개니까 ArrayList<제너릭은GuestVO> vos
		
		// 설정(지정)된 페이지의 모든 자료(변수)들을 viewPage로 넘겨줄 준비를 한다.
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totRecCnt", totRecCnt);
		request.setAttribute("totPage", totPage);
		request.setAttribute("curScrStartNo", curScrStartNo);
		request.setAttribute("vos", vos);
		request.setAttribute("sort", sort);
		
		String viewPage = "/study/database/loginMain.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
