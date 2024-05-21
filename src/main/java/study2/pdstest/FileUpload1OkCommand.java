package study2.pdstest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import study2.StudyInterface;

/*
  자바 파일업로드시...(컨트롤러에 아래 어노테이션에 환경설정처리한다.)
	@MultipartConfig(
			location = "/images/pdstest",
			maxFileSize = 1024 * 1024 * 10,
			maxRequestSize = 1024 * 1024 * 10 * 10
	)
 */


public class FileUpload1OkCommand implements StudyInterface {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String fName = request.getParameter("fName")==null ? "" : request.getParameter("fName");
		// System.out.println("fName : " + fName);
		
		/*
		   COS라이브러리에서 제공해주는 객체 : MultipartRequest() / DefaultFileRenamePolicy()
		   사용법 : MultipartRequest(저장소명(request),'서버에 저장될 파일의 경로','서버에 저장될 파일의 최대용량','코드변환방식',기타옵션(파일명중복방지처리))
		   
		   저장되는 서버 웹사이트의 절대경로 : /webapp  : getRealPath("/")
		   
		   ServletContext application = request.getServletContext();
		   application.getRealPath("/");
		*/
		
		// ServletContext application = request.getServletContext();
		// application.getRealPath("/");			// /webapp을 의미한다.
		
		String realPath = request.getServletContext().getRealPath("/images/pdstest");
		int maxSize = 1024 * 1024 * 10;	// 서버에 저장시킬 파일의 최대용량 : 10MByte로 제한(1회저장용량)
		String encoding = "UTF-8";
		
		// 파일 업로드 처리...(객체 생성시 파일이 자동으로 업로드 된다.)
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());
		
		// 업로드된 파일의 정보를 추출해본다.
		String originalFileName = multipartRequest.getOriginalFileName("fName");
		String filesystemName = multipartRequest.getFilesystemName("fName");
		
		System.out.println("원본 파일명 : " + originalFileName);
		System.out.println("서버에 저장된 파일명 : " + filesystemName);
		System.out.println("서버에 저장된 파일경로 : " + realPath);
		
		// 닉네임 받아서 찍어보기
		// String nickName = request.getParameter("nickName");
		String nickName = multipartRequest.getParameter("nickName");
		System.out.println("nickName : " + nickName);
		
		// BackEnd 파일체크
		if(originalFileName != null && !originalFileName.equals("")) {
			request.setAttribute("message", "파일 전송완료!!");
		}
		else {
			request.setAttribute("message", "파일 전송실패~~~");
		}
		
		request.setAttribute("url", "FileUpload1.st");
	}

}
