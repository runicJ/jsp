package study2.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study.database.LoginDAO;
import study.database.LoginVO;
import study2.StudyDAO;
import study2.StudyInterface;

@SuppressWarnings("serial")
@WebServlet("/AjaxIdCheck2_2")
public class AjaxIdCheck2_2 extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");  // 세우면 안되니까 바로 차에 싣음
		
		LoginDAO dao = new LoginDAO();
		
		LoginVO vo = dao.getLoginIdSearch(mid);
		
		String str = "";
		
		if(vo.getName() == null) str = "찾는 자료가 없습니다.";
		//else str = vo + "";  // vo가 객체니까 문자로 바꿔서  // LoginVO [idx=9, mid=admin, pwd=1234, name=관리자, age=10, gender=여자, address=서울]
		else str = vo.getMid()+"/"+vo.getName()+"/"+vo.getAge()+"/"+vo.getGender()+"/"+vo.getAddress();  // vo 구분, 가져가서 /로 구분
		
		response.getWriter().write(str);
	}
}
