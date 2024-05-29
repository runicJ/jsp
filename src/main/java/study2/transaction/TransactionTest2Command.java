package study2.transaction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class TransactionTest2Command implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String bankFlag = request.getParameter("bankFlag")==null ? "" : request.getParameter("bankFlag");
		int money = (request.getParameter("money")==null || request.getParameter("money").equals("")) ? 0 : Integer.parseInt(request.getParameter("money"));
		String content = request.getParameter("content")==null ? "" : request.getParameter("content");
		String err = request.getParameter("error")==null ? "" : request.getParameter("error");
		
		if(bankFlag.equals("출금")) money *= (-1);
		
		try {
			TransactionDAO dao = new TransactionDAO();
			
			// mid를 bankBook테이블에서 검색하여 해당 항목의 idx를 구해온다.
			BankBookVO bVo = dao.getBankBookMidSearch(mid);
			
			BankBookVO vo = new BankBookVO();
			vo.setIdx(bVo.getIdx());
			vo.setMid(mid);
			vo.setBalance(money);
			//vo.setContentIdx(bVo.getIdx());
			vo.setContent(content);
			
			// 입/출금 내역에 기록하기
			dao.setBankBookHistoryInput(vo);
			
			// 실제 잔고에 적용하기
			dao.setBankBookInput(vo, err);
			
//	    if(request.getParameter("error")!=null && !request.getParameter("error").equals("")){  // error가 비어있지 않으면 나를 부른 얘한테 오류 발생!!이라고 던짐(catch로 던짐) // 예외 발생 시킴(오류발생)
//	    	request.setAttribute("message", "BankBookInputNo");
//	      throw new Exception("진행 오류 발생 !!");  // 오류발생시 밑으로 가지 않고 catch의 예외처리로
//	    }
//	    else {
//				request.setAttribute("message", "BankBookInputOk");
//	    }
	    request.setAttribute("message", "BankBookInputOk");
	    request.setAttribute("url", "Transaction.st");
		} catch (Exception e) {
			System.out.println("예외오류 발샐~~ : " + e.getMessage());
		}
	}

}
