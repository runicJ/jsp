package memeber;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberInterface {
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;  // HttpServlet한테 던짐  // 한줄밖에 안씀(매번 프로젝트마다 쓰고)  // 선언부만 있음
}
