package study2.pdstest;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class FileDeleteCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName")==null ? "" : request.getParameter("fileName");  // 일반적으로 받아오는 request.getParameter로 처리
		
		String realPath = request.getServletContext().getRealPath("/images/pdstest/");
		
		File file = new File(realPath+fileName);  // 파일을 지울때 항상 객체를 만들어서 지워야 // /fileName 추가("/" + 를 앞에 붙여야 함)
		
		String res = "0";
		if(file.exists()) {  // exists 파일이 존재하니
			file.delete();
			res = "1";
		}
		
		response.getWriter().write(res);
	}

}
