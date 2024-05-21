package study2.pdstest;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import study2.StudyInterface;

public class FileDownloadCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pdstest");  // () 안의 경로를 읽어옴  // 자바명령어
		
		String[] files = new File(realPath).list();  // 모든걸 가져옴(하나가 아니라) 파일명도 가져옴 // list라는 메소드  // 파일명 오름차순(문자로 보기때문에 앞에서부터 비교)
		
		for(String file : files) {
			//System.out.println("file : " + file);
		}
		
		request.setAttribute("files", files);		
	}

}
