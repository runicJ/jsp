package study.database;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/database/UpdateOk")
public class UpdateOk extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = request.getParameter("idx")==null ? 0 : Integer.parseInt(request.getParameter("idx"));  // view창에서 안넘겼으므로 에러남(hidden으로 넘김)
		String mid = request.getParameter("mid")==null ? "" : request.getParameter("mid");
		String pwd = request.getParameter("pwd")==null ? "" : request.getParameter("pwd");
		String name = request.getParameter("name")==null ? "" : request.getParameter("name");
		int age = request.getParameter("age")==null ? 0 : Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender")==null ? "" : request.getParameter("gender");
		String address = request.getParameter("address")==null ? "" : request.getParameter("address");
		
		LoginVO vo = new LoginVO();
		
		vo.setIdx(idx);
		vo.setMid(mid);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setAge(age);
		vo.setGender(gender);
		vo.setAddress(address);
		
		LoginDAO dao = new LoginDAO();
		
		int res = dao.setLoginUpdate(vo);
		
		PrintWriter out = response.getWriter();
		if(res != 0) {
			out.print("<script>");
			out.print("alert('회원정보가 수정되었습니다.');");
			out.print("location.href='"+request.getContextPath()+"/study/database/LoginView?idx="+idx+"';");  // view.jsp로 보내면 지난 내용이고 이미 응답했기 때문에 내용도 사라져 있음 => 어쨌든 서블릿으로 보내야 // reponse.redirect와 같은 것
			out.print("</script>");
		}
		else {
			out.print("<script>");
			out.print("alert('회원정보 수정 실패~~');");
			out.print("location.href='"+request.getContextPath()+"/study/database/LoginView?idx="+idx+"';");  // 다른걸로 했으면 view.jsp로 보내도 될지도 // 여기선 써야함
			out.print("</script>");
		}
	}
}
