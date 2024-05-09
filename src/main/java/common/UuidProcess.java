package common;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/UuidProcess")
public class UuidProcess extends HttpServlet {  // 임시비밀번호 생성 // salt키로 쓴다
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UUID uid = UUID.randomUUID();  // 타입은 UUID  // 객체이므로 아래에서 문자로 바꿔서
		
		response.getWriter().write(uid.toString());
	}
}
