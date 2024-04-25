package study.j0425;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/j0425/T06Ok")
public class T06Ok extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		// Front에서 넘어온 값들을 변수에 담아서 처리한다.
		String name = request.getParameter("name")== null ? "" : request.getParameter("name");
		int age = (request.getParameter("age")== null || request.getParameter("age")== "") ? 0 : Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender"); // 무조건 값 하나는 넘어오니까 null값 처리 안함
//		String[] hobby = request.getParameterValues("hobby")==null ? "" : request.getParameterValues("hobby");  // 값이 여러개면 받는 쪽에서 배열로 받아야
		String[] hobbys = request.getParameterValues("hobby");
		String job = request.getParameter("job");  // 아무 것도 안하면 공백 처리되니까 null값 처리 안해도 됨
		String[] mountains = request.getParameterValues("mountain");
		String content = request.getParameter("content")== null ? "" : request.getParameter("content");
		content = content.replace("\n", "<br/>"); /* *** */
		String fileName = request.getParameter("fileName");
		
		System.out.println("성명 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("성별 : " + gender);
		
		String hobby = "";  // 밑에서 출력하려고 선언
			if(hobbys != null) {
			for(String h : hobbys) {
	//			System.out.print(h + "/");
				hobby += h + "/";
			}
			hobby = hobby.substring(0, hobby.length()-1);
		}
			
		String mountain = "";  // 밑에서 출력하려고 선언
		if(mountains != null) {
			for(String m : mountains) {
				mountain += m + "/";
			}
			mountain = mountain.substring(0, mountain.length()-1);
			System.out.println(mountain);
		}
		System.out.println("취미 : " + hobby);
		System.out.println("직업 : " + job);
		System.out.println("가본산 : " + mountain);
		System.out.println("자기소개 : " + content);
		System.out.println("파일명 : " + fileName);
		
		PrintWriter out = response.getWriter();
		
		out.println("<p>성명 : "+name+"</p>");
		out.println("<p>나이 : "+age+"</p>");
		out.println("<p>성별 : "+gender+"</p>");
		out.println("<p>취미 : "+hobby+"</p>");
		out.println("<p>직업 : "+job+"</p>");
		out.println("<p>가본산 : "+mountain+"</p>");
		out.println("<p>자기소개 : "+content+"</p>");
		out.println("<p>파일명 : "+fileName+"</p>");
		out.println("<p><a href='"+request.getContextPath()+"/study/0425/t06_form.jsp'>돌아가기</a></p>");
		
	}
	
}
