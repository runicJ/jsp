package study2.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyDAO;
import study2.StudyInterface;

public class AjaxIdCheck1Command implements StudyInterface {

	@Override  // interface 작업지시서 아래 자동으로 나옴
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");  // 세우면 안되니까 바로 차에 싣음
		
		StudyDAO dao = new StudyDAO();
		
		String name = dao.getIdSearch(mid);
		
		if(name.equals("")) {
			name = "찾는 자료가 없습니다.";
		}
		else {
			// PrintWriter out = response.getWriter();
			// out.println(name);  // 이렇게 하면 브라우저 화면에 그냥 출력 => 그러나 demo에 찍어야함
			// out.write(name);  // 문자값만 넘김(header에 writer해서 가져감) == request.setAttribute와 같음
			response.getWriter().write(name);  // id를 같이 안넘겼으므로 pram.으로 받은 것 setAttribute에서 
		}
	}

}
