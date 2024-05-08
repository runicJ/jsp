package study2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.database.LoginDAO;
import study.database.LoginVO;

public class HoewonSearchCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginIdxSearch(idx);  // return 타입 vo
		
		String str = idx + "/" + vo.getMid() + "/" + vo.getPwd() + "/" + vo.getName() + "/" + vo.getAge() + "/" + vo.getGender() + "/" + vo.getAddress();  // vo.getIdx라고 안써도 넘어온 값
		response.getWriter().write(str);  // vo를 쪼개서 보냈으니까 받는 쪽에서 나눠줌
	}

}
