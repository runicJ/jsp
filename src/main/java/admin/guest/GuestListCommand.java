package admin.guest;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.AdminInterface;
import guest.GuestDAO;
import guest.GuestVO;

public class GuestListCommand implements AdminInterface {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestDAO dao = new GuestDAO();  // 페이징 처리 전에 DAO를 항상 올리고 시작해야 한다.
		
		// 아래 값들을 나중엔 vo로 묶을 것
		// 1. 현재 페이지번호를 구해온다.(첫 화면은 무조건 1페이지=>페이징처리x(다른페이지 눌렀을때 페이지처리한 것)
		int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));  // 첫 페이지만 모르는 것, 누른 페이지는 알 수 있음 // 다음, 이전 누를때 +1 -1 하면 됨
		
		// 2. 한 페이지의 분량을 구한다.
		/* int pageSize = 5; */
		int pageSize = request.getParameter("pageSize")==null ? 5 : Integer.parseInt(request.getParameter("pageSize"));  // 3은 기본페이지
		
		// 3. 총 레코드 건수를 구한다.(sql명령어 중 count함수 이용)
		int totRecCnt = dao.getTotRecCnt();
		
		// 4. 총 페이지 건수를 구한다.
		int totPage = (totRecCnt % pageSize)==0 ? (totRecCnt / pageSize) : (totRecCnt / pageSize) + 1;  // (totRecCnt/pageSize) => 나누고 값을 정수로
		if(pag > totPage) pag = 1;  // 무조건 1페이지로 가는걸로
		
		// 5. 현재 페이지에서 출력할 '시작 인덱스 번호'를 구한다. => 내가 알 필요 없고, 기계가 필요함(vos에서 써먹음)
		int startIndexNo = (pag - 1) * pageSize;
		
		// 6. 현재 화면에 표시될 '시작 실제 게시글 번호'를 구한다.
		int curScrStartNo = totRecCnt - startIndexNo;
		
		// 블록페이징처리...(시작블록을 0으로 처리했다.)
		// 1. 블록의 크키결정(여기선 3으로 결정했다.)
		int blockSize = 3;
		
		// 2. 현재 페이지가 속한 블록번호를 구한다.(예: 총 레코드 수가 38개일 경우, 1페이지 분량 5개라면, 총 페이지 수는 8개이다.
		// 이때 0블록은 '1/2/3', 1블록은 '4/5/6', 2블록은 '7/8'
		int curBlock = (pag - 1) / blockSize;
		
		// 3. 마지막 블록을 구한다.
		int lastBlock = (totPage - 1) / blockSize;

		// 한 페이지에 표시할 건수만(pageSize만큼)을 DAO에서 가져온다.
		ArrayList<GuestVO> vos = dao.getGuestList(startIndexNo, pageSize);  // 기계가 알 수 있는건 인덱스번호  // 조건이 없음 다 가져와야 함  // 값이 여러개니까 ArrayList<제너릭은GuestVO> vos
		
		// 설정(지정)된 페이지의 모든 자료(변수)들을 viewPage로 넘겨줄 준비를 한다.
		request.setAttribute("pag", pag);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totRecCnt", totRecCnt);
		request.setAttribute("totPage", totPage);
		request.setAttribute("curScrStartNo", curScrStartNo);
		
		request.setAttribute("blockSize", blockSize);
		request.setAttribute("curBlock", curBlock);
		request.setAttribute("lastBlock", lastBlock);
		
		request.setAttribute("vos", vos);
	}

}
