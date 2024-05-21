package study2.pdstest;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class FileDeleteCheckCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] selectFileArray = request.getParameter("selectFileArray").split("/");  // null 값 처리 안해도됨(배열 값이 없으면 자동으로 null)
		// System.out.println("selectFileArray[i] : " + selectFileArray[0]);
		
		String realPath = request.getServletContext().getRealPath("/images/pdstest/");
		
		String res = "0";
		for(int i=0; i<selectFileArray.length; i++) {
			File file = new File(realPath+selectFileArray[i]);  // 19줄 마지막에 안쓰면 + "/" + 붙여줘야 함
			if(file.exists()) {  // 패일이 존재하면 삭제
				file.delete();
				res = "1";
			}
		}
		
		response.getWriter().write(res);
	}

}
