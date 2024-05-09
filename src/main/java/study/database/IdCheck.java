package study.database;
 
import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
@WebServlet("/database/IdCheck")
public class IdCheck extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		 
		LoginDAO dao = new LoginDAO();
		 
		// 아이디 중복체크
		LoginVO vo = dao.getLoginIdSearch(mid);
		if(vo.getName() != null) {
			response.getWriter().write("아이디가 중복되었습니다. 다른 아이디로 가입해주세요.");
		}
		else {
			response.getWriter().write("사용 가능한 아이디 입니다.");
		}
	}
}