package study2.pdstest;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import study2.StudyInterface;

public class FileUpload4OkCommand implements StudyInterface {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = request.getServletContext().getRealPath("/images/pdstest");
		int maxSize = 1024 * 1024 * 10;
		String encoding = "UTF-8";
		
		MultipartRequest multipartRequest = new MultipartRequest(request, realPath, maxSize, encoding, new DefaultFileRenamePolicy());  // 같은 이름이 있으면 DefaultFileRenamePolicy로 +1을 붙임
		
		// 업로드된 파일의 정보를 추출해본다.
		
		String fNames = multipartRequest.getParameter("fNames");
	
		Enumeration fileNames = multipartRequest.getFileNames();  // 제너릭 필요없음 할거면 String으로
		
		String file = "";  // 넘어온 파일을 매번 찍어도 되는데 그냥 변수를 만들어줌
		String oFileName = "";
		String fsName = "";
		
		while(fileNames.hasMoreElements()) {  // Enumeration에 자료가 있느냐 hasMoreElement
			file = (String) fileNames.nextElement();  // 찾으면 파일정보  // 강제 형변환
			oFileName += multipartRequest.getOriginalFileName(file) + "/";  // iterator 열거형 파일 여러개 꺼냄, has로 자료 있는지 확인하고, 하나에 대한 파일name을 뽑아와서 첫번째 파일의 원본이름 가져와라
			fsName += multipartRequest.getFilesystemName(file) + "/";
		}
		oFileName = oFileName.substring(0, oFileName.lastIndexOf("/"));
		fsName = fsName.substring(0, fsName.lastIndexOf("/"));
		
		System.out.println("원본 파일명 : " + oFileName);  // 여러개 전송해도 하나로 봄
		System.out.println("서버에 저장된 파일명 : " + fsName);
		
		System.out.println("클라이언트에서 업로드된 파일명 : " + fNames);
		
		if(oFileName != null && !oFileName.equals("")) {
			request.setAttribute("message", "파일이 업로드 되었습니다.");
		}
		else {
			request.setAttribute("message", "파일이 업로드 실패~~");
		}
		request.setAttribute("url", "FileUpload4.st");
	}

}
